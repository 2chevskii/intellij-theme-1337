# Publishing 1337 Theme

## Prepare the release

1. Use Java 25 and run `./gradlew clean check verifyPluginProjectConfiguration verifyPluginStructure verifyPlugin buildPlugin`.
   The SDK baseline is IntelliJ IDEA 2026.2 and the default verification target
   is IntelliJ IDEA 2026.3. Run the full product matrix in
   `.github/workflows/verify.yml`; update its stable release pins for each release.
2. Complete [visual validation](visual-validation.md) in a clean sandbox.
   Automated verification cannot establish that every IDE's languages inherit
   the intended syntax colors.
3. Capture real IDE screenshots with the theme active: full IDE, syntax, web,
   and diff. JetBrains requires screenshots for theme plugins and recommends
   at least 1200 × 760 pixels. Use consistent aspect ratios and omit personal data.
4. Check the version in `build.gradle.kts` and change notes in `META-INF/plugin.xml`.
   Keep the plugin ID `com.dvchevskii.theme1337` stable across releases.
5. Inspect `build/distributions/intellij-1337-theme-0.1.0.zip` (the filename follows
   the project version). The inner JAR must contain the descriptor, JSON theme,
   XML scheme, SVG icon, MIT license, and upstream notice.

## First Marketplace upload

Upload the ZIP at [JetBrains Marketplace](https://plugins.jetbrains.com/).
Create the listing under your vendor account, select the **Themes** tag, choose
the **MIT** license, and add the screenshots. Use these listing links:

- Source: https://github.com/2chevskii/intellij-theme-1337
- Support: https://github.com/2chevskii/intellij-theme-1337/issues
- Upstream: https://github.com/MarkMichos/1337-Scheme

The description and initial change notes are packaged in `plugin.xml`. Review
them in the upload preview and submit the release for Marketplace approval.

## Subsequent signed releases

Configure GitHub Actions secrets `PUBLISH_TOKEN`, `CERTIFICATE_CHAIN`,
`PRIVATE_KEY`, and (if encrypted) `PRIVATE_KEY_PASSWORD`. Use a Marketplace token
authorized for this plugin and a matching signing key/certificate chain.

Run **Publish plugin** manually with `publish` enabled, from the reviewed release
commit. The workflow first requires the full IDE verification matrix to pass,
then validates resources, signs the ZIP,
verifies the signature, and then publishes. The default dispatch performs no
publication. The initial listing must already exist.

## References

- [Marketplace listing requirements](https://plugins.jetbrains.com/docs/marketplace/best-practices-for-listing.html)
- [Theme editor schemes and scrollbars](https://plugins.jetbrains.com/docs/intellij/themes-extras.html)
- [Plugin signing](https://plugins.jetbrains.com/docs/intellij/plugin-signing.html)
