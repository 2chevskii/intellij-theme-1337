# Visual validation checklist

Run `./gradlew runIde` and select **1337 Theme**. Complete this checklist in a
clean IntelliJ IDEA 2026.2 sandbox with no Marketplace language plugins installed.
Also smoke-test current IDEs from the verification matrix, using Rider for
C#/F#/Razor and the corresponding product for Java, Python, Go, Rust, and PHP.

## Theme registration and chrome

- [x] **Appearance** lists and activates `1337 Theme`.
- [x] **Editor | Color Scheme** lists `1337 Theme`; switching the IDE theme
      activates the paired scheme.
- [ ] Tool windows, menus, dialogs, lists, trees, inputs, tabs, status bar,
      notifications, and scrollbars use the dark surface, readable foreground,
      blue accent, and source-derived state colors.
- [ ] The C# editor has `#191919` background, `#F8F8F2` text, `#515151`
      selection, and a translucent `#3D3D3D` current line.

## Language audit

Open every language page offered by **Settings | Editor | Color Scheme** in
each tested IDE, then open each matching fixture or a minimal file of
that language. Verify no bundled editor has unreadable text or a light default
background. Use **Jump to Colors and Fonts** for any failing token and add the
narrowest language attribute override needed.

At minimum, capture screenshots for C#, F#, Razor, HTML/XML, CSS,
JavaScript/TypeScript, JSON, SQL, YAML, Markdown, and a unified diff. Check
comments, strings, numbers, keywords, types, functions, parameters,
attributes, punctuation, invalid text, VCS statuses, and diff lines.

## Release evidence

- [ ] Attach the four Marketplace screenshots: full IDE, C#, Razor/web, diff.
- [ ] Run `check`, `verifyPluginProjectConfiguration`, `verifyPluginStructure`,
      `verifyPlugin`, and `buildPlugin` successfully.
- [x] Inspect the ZIP for the theme descriptor, XML scheme, icon, and notices.

Unchecked items are release gates, not evidence of completed validation.

## Automated validation — November 6, 2026

- Passed `check`, `verifyPluginProjectConfiguration`, `verifyPluginStructure`,
  and `buildPlugin` on Gradle 9.8.2 / Java 25.
- Confirmed that `validateTheme` rejects an undefined named color; restored
  the valid resource and reran the build successfully.
- IntelliJ Plugin Verifier 1.411 reported **Compatible** for both Rider
  2026.2 (`RD-262.8665.329`) and 2026.2.4.1 (`RD-262.11879.119`). The CLI was
  run with an isolated verifier cache to avoid contention with other projects.
  Reports are in `build/reports/pluginVerifier`.
- Inspected the distribution's inner JAR for the descriptor, icon, theme,
  scheme, license, and third-party notice. ZIP SHA-256:
  `4cc015fafcbdb0d888c1489e42242358706b649ca22832a96e83326edcfb3c57`
  after the visual-review markup corrections.
- The .NET 10 C# fixture builds with zero warnings and errors.

## Visual review — November 6, 2026

Reviewed the installed, activated Rider 2026.2.4.1 at the user's request. This
installation includes additional plugins (including an editor minimap), so it
does not replace the clean-sandbox audit of every bundled language settings page.

- Theme activation selects the paired editor scheme correctly.
- C# semantic colors, comments, strings, punctuation, parameters, selection,
  caret row, editor tabs, solution tree, settings, and diff controls are readable.
- Reviewed F#, Razor, HTML, XML, CSS, JavaScript, TypeScript, JSON, SQL, YAML,
  and Markdown source fixtures. Files outside the C# project do not all receive
  full semantic analysis; this checks their lexical colors and editor surfaces.
- Found HTML/XML attributes and tags inheriting Darcula colors. Added explicit
  `baseAttributes` aliases and verified the red tags, blue attributes, and warm
  strings in HTML, XML, and Razor after restarting with the rebuilt plugin.
- Reviewed both diff layouts. Unified diff shows distinct dark red and green
  backgrounds, readable syntax, and the intended status accents.
- Markdown source passes. Rendered Markdown preview could not be checked:
  the installation's internal HTTP server fails to start and localhost refuses
  the preview connection. This issue was present before theme activation.

Screenshots are in [screenshots](screenshots/): [C#](screenshots/csharp.jpg),
[HTML](screenshots/html.jpg), [Razor](screenshots/razor.jpg),
[unified diff](screenshots/diff.jpg), and one per additional reviewed language.
The settings screenshots document registration; use editor screenshots for the
Marketplace listing. The diff and settings captures are smaller than JetBrains'
recommended 1200 × 760 size; final listing media selection remains a release gate.

The previous installed theme JAR is backed up under the ignored
`.intellijPlatform/installed-theme-backup` directory. The corrected candidate is
installed and active in the user's Rider. Signing and Marketplace upload have
not been performed.

## IntelliJ Platform support expansion — November 6, 2026

The release candidate now declares only `com.intellij.modules.platform` and
retains `since-build="262"` with no upper build bound. Rider semantic colors
are optional string keys; the plugin contains no code or Rider class references.
The stable plugin ID remains `com.dvchevskii.theme1337`. The archive is now
`build/distributions/intellij-1337-theme-0.1.0.zip`; previous archives are kept
under the ignored `.intellijPlatform/previous-distributions` directory.

- Passed the resource checks, project configuration, structure verification,
  and packaging against the IntelliJ IDEA 2026.2 SDK.
- `verifyPlugin` passed on IntelliJ IDEA 2026.3 (`IU-263.7307.200`).
- The same archive also passed Plugin Verifier on IntelliJ IDEA 2026.2
  (`IU-262.8665.258`) and Rider 2026.2.4.1 (`RD-262.11879.119`). Reports are
  in `build/reports/platformBaseline`; the 2026.3 report is in
  `build/reports/pluginVerifier`.
- In a separate IntelliJ IDEA 2026.2 sandbox with no Rider module, activated
  1337 Theme and confirmed that it selects the matching editor scheme. Opened
  the Java fixture in LightEdit and checked its lexical colors and editor UI.
  This is not a full Java semantic-highlighting audit.
- [IDEA activation](screenshots/intellij-appearance.jpg) and
  [Java LightEdit](screenshots/intellij-java.jpg) screenshots record that smoke test.
- The Java fixture compiles with Java 25. `actionlint` passes both workflows.
- CI now includes IntelliJ IDEA (baseline and current), PyCharm, WebStorm,
  PhpStorm, GoLand, CLion, RubyMine, DataGrip, RustRover, and Rider. Those matrix
  jobs are configured but have not been executed remotely during this review.
  Visual checks in the other product families remain outstanding.

Current ZIP SHA-256:
`2b3f01638df722510476d3832794fa8219a0d5e323295b69757a0257fa49584f`.
The earlier Rider evidence above refers to the previous archive; use this
section for the current package and platform-wide coverage.
