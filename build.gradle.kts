import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import groovy.json.JsonSlurper
import javax.xml.parsers.DocumentBuilderFactory

plugins {
    id("org.jetbrains.intellij.platform")
}

// Theme resources have no compiled code, so validate their cross-file links explicitly.
val validateTheme = tasks.register("validateTheme") {
    group = "verification"
    description = "Checks theme colors, scheme options, and packaged resource references."
    val resources = layout.projectDirectory.dir("src/main/resources").asFile
    inputs.dir(resources)
    doLast {
        fun resource(path: String) = resources.resolve(path.removePrefix("/")).also {
            check(it.isFile) { "Missing theme resource: $path" }
        }
        val xmlFactory = DocumentBuilderFactory.newInstance().apply {
            setFeature("http://apache.org/xml/features/disallow-doctype-decl", true)
        }
        val plugin = xmlFactory.newDocumentBuilder().parse(resource("META-INF/plugin.xml"))
        val dependencies = plugin.getElementsByTagName("depends")
        check((0 until dependencies.length).map { dependencies.item(it).textContent.trim() } ==
            listOf("com.intellij.modules.platform")) {
            "The theme must depend only on shared platform functionality to support all IDEs"
        }
        val provider = plugin.getElementsByTagName("themeProvider").item(0) as org.w3c.dom.Element
        val theme = JsonSlurper().parse(resource(provider.getAttribute("path"))) as Map<*, *>
        check(theme["dark"] == true) { "1337 must inherit dark UI defaults" }
        val palette = theme["colors"] as Map<*, *>
        val hex = Regex("#[0-9a-fA-F]{6}([0-9a-fA-F]{2})?")
        palette.forEach { (name, value) -> check(value is String && hex.matches(value)) { "Invalid palette color $name: $value" } }
        fun checkColors(value: Any?) {
            if (value is Map<*, *>) value.values.forEach { checkColors(it) }
            else check(value is String && (palette.containsKey(value) || hex.matches(value))) { "Unknown color: $value" }
        }
        checkColors(theme["ui"])
        checkColors((theme["icons"] as Map<*, *>)["ColorPalette"])
        val schemeFile = resource(theme["editorScheme"] as String)
        val bundled = plugin.getElementsByTagName("bundledColorScheme").item(0) as org.w3c.dom.Element
        check(resource(bundled.getAttribute("path") + ".xml") == schemeFile) { "Editor scheme registrations disagree" }
        val scheme = xmlFactory.newDocumentBuilder().parse(schemeFile).documentElement
        check(scheme.getAttribute("name") == theme["name"]) { "Theme and scheme names disagree" }
        for (sectionName in listOf("colors", "attributes")) {
            val section = scheme.getElementsByTagName(sectionName).item(0)
            val names = mutableSetOf<String>()
            for (i in 0 until section.childNodes.length) {
                val option = section.childNodes.item(i) as? org.w3c.dom.Element ?: continue
                check(names.add(option.getAttribute("name"))) { "Duplicate $sectionName option: ${option.getAttribute("name")}" }
            }
        }
        val attributes = scheme.getElementsByTagName("attributes").item(0).childNodes
        val attributeNames = (0 until attributes.length).mapNotNull {
            (attributes.item(it) as? org.w3c.dom.Element)?.getAttribute("name")
        }.toSet()
        for (i in 0 until attributes.length) {
            val option = attributes.item(i) as? org.w3c.dom.Element ?: continue
            val base = option.getAttribute("baseAttributes")
            check(base.isEmpty() || base in attributeNames) { "Missing base attribute: $base" }
        }
        val options = scheme.getElementsByTagName("option")
        val colorOptions = setOf("FOREGROUND", "BACKGROUND", "EFFECT_COLOR", "ERROR_STRIPE_COLOR")
        for (i in 0 until options.length) {
            val option = options.item(i) as org.w3c.dom.Element
            if (option.parentNode.nodeName == "colors" || option.getAttribute("name") in colorOptions) {
                check(hex.matches("#" + option.getAttribute("value"))) { "Invalid scheme color: ${option.getAttribute("name")}" }
            }
        }
        listOf("META-INF/pluginIcon.svg", "META-INF/LICENSE", "META-INF/NOTICE").forEach { resource(it) }
        logger.lifecycle("Theme resources and registrations are valid.")
    }
}

tasks.check { dependsOn(validateTheme) }
tasks.buildPlugin { dependsOn(validateTheme) }

group = "com.dvchevskii"
version = "0.1.0"

dependencies {
    intellijPlatform {
        create(providers.gradleProperty("platformType").get(), providers.gradleProperty("platformVersion").get()) {
            useInstaller = false
        }
        pluginVerifier()
        zipSigner()
    }
}

intellijPlatform {
    pluginConfiguration {
        ideaVersion {
            sinceBuild = "262"
        }
    }

    signing {
        certificateChain = providers.environmentVariable("CERTIFICATE_CHAIN")
        privateKey = providers.environmentVariable("PRIVATE_KEY")
        password = providers.environmentVariable("PRIVATE_KEY_PASSWORD")
    }

    publishing {
        token = providers.environmentVariable("PUBLISH_TOKEN")
        channels = listOf("default")
    }

    pluginVerification {
        ides {
            val localIde = providers.gradleProperty("verificationIdePath")
            if (localIde.isPresent) {
                local(localIde.get())
            } else {
                create(
                    IntelliJPlatformType.valueOf(providers.gradleProperty("verificationType").get()),
                    providers.gradleProperty("verificationVersion").get(),
                )
            }
        }
    }
}
