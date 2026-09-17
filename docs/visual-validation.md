# Visual validation checklist

Run `./gradlew runIde` and select **1337 Theme**. Complete this checklist in a
clean Rider 2026.2 sandbox with no Marketplace language plugins installed.

## Theme registration and chrome

- [ ] **Appearance** lists and activates `1337 Theme`.
- [ ] **Editor | Color Scheme** lists `1337 Theme`; switching the IDE theme
      activates the paired scheme.
- [ ] Tool windows, menus, dialogs, lists, trees, inputs, tabs, status bar,
      notifications, and scrollbars use the dark surface, readable foreground,
      blue accent, and source-derived state colors.
- [ ] The C# editor has `#191919` background, `#F8F8F2` text, `#515151`
      selection, and a translucent `#3D3D3D` current line.

## Language audit

Open every language page offered by **Settings | Editor | Color Scheme** in
the Rider 2026.2 sandbox, then open each matching fixture or a minimal file of
that language. Verify no bundled editor has unreadable text or a light default
background. Use **Jump to Colors and Fonts** for any failing token and add the
narrowest Rider attribute override needed.

At minimum, capture screenshots for C#, F#, Razor, HTML/XML, CSS,
JavaScript/TypeScript, JSON, SQL, YAML, Markdown, and a unified diff. Check
comments, strings, numbers, keywords, types, functions, parameters,
attributes, punctuation, invalid text, VCS statuses, and diff lines.

## Release evidence

- [ ] Attach the four Marketplace screenshots: full IDE, C#, Razor/web, diff.
- [ ] Run `verifyPluginProjectConfiguration`, `verifyPluginStructure`,
      `verifyPlugin`, and `buildPlugin` successfully.
- [ ] Inspect the ZIP for the theme descriptor, XML scheme, icon, and notices.
