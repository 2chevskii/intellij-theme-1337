# 1337 Theme for JetBrains Rider

1337 Theme is a dark Rider 2026.2 theme and editor color scheme based on Mark
Herpich's original Sublime Text **1337 Scheme**.

## Install from a ZIP

1. Build the plugin with `./gradlew buildPlugin`.
2. In Rider, open **Settings | Plugins | ⚙ | Install Plugin from Disk…**.
3. Select the ZIP created in `build/distributions` and restart Rider.
4. Choose **1337 Theme** in **Settings | Appearance & Behavior | Appearance**.

The theme also registers **1337 Theme** in **Settings | Editor | Color Scheme**.

## Development

The project targets Rider 2026.2 (build 262) and needs a Java 21 JDK. The
Gradle Wrapper downloads Gradle and the target Rider distribution as needed.

```powershell
.\gradlew.bat runIde
.\gradlew.bat verifyPluginProjectConfiguration verifyPluginStructure verifyPlugin buildPlugin
```

See [the visual validation checklist](docs/visual-validation.md) before a
release. The first Marketplace upload must be performed manually to set the
listing's license, source, support, and vendor details.

## License and attribution

This port is MIT licensed. It includes the original 1337 Scheme MIT notice in
the plugin archive; see [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md).
