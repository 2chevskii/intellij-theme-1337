# Sublime 1337 to Rider mapping

The palette source is [`1337.sublime-color-scheme`](https://github.com/MarkMichos/1337-Scheme/blob/master/1337.sublime-color-scheme) from the upstream 1337
Scheme. Rider has no TextMate scope model, so source rules are mapped to
IntelliJ Platform Language Defaults first and only need language-specific
overrides when a bundled Rider language does not inherit a default.

| Sublime scope or role | Rider scheme role | Color/effect |
| --- | --- | --- |
| `comment` | line, block, and documentation comments | `#6D6D6D` |
| `string` | strings and text blocks | `#FBE3BF` |
| `constant.numeric`, `constant.other` | numbers and constants | `#FDB082` |
| `constant.language` | predefined symbols | `#FF8942` |
| `variable` | identifiers and locals | `#E9FDAC` |
| `keyword`, `storage`, tag names | keywords and markup tags | `#FF5E5E` |
| metadata / annotations | language-default metadata | `#FBDFB5`, italic |
| class and function names | classes, interfaces, methods, functions | `#8CDAFF`; classes underlined |
| inherited class | class reference | `#8CDAFF`, italic underline |
| `variable.parameter` | parameters | `#FC9354`, italic |
| markup attributes | XML/HTML/Razor attributes | `#97D8EA` |
| library functions | predefined/static functions | `#6699CC` |
| library constants | predefined constants | `#ECFDB9` |
| labels | language-default labels | `#D699FF` |
| punctuation | operators, separators, braces | `#FFFFFF` |
| invalid / deprecated | bad character / deprecated symbols | `#F8F8F0` on `#F92649`; `#191919` on `#FF9664` for readable deprecated text |
| GitGutter and diff | VCS statuses and diff attributes | added `#A6E22E`, deleted `#F92672`, changed `#CC00FF`; diff backgrounds use dark tints to preserve syntax contrast |

The source's PHP and Twig-specific scopes are not explicitly mapped yet. In
IDEs that provide those languages, unmapped roles inherit dark defaults. Their
colors remain available as named palette values for language overrides when
an equivalent semantic role is identified through **Jump to Colors and Fonts**.

Rider semantic aliases (delegates, enums, structs, static classes, type
parameters, extension methods, mutable locals, string escapes, and Razor tag
helpers) explicitly inherit the corresponding language defaults with
`baseAttributes`. These key names were checked against the bundled
`rider-theme-pack.jar` in Rider 2026.2. Defaults use their serialized platform
names (`DEFAULT_PARENTHS`, `DEFAULT_TAG`, `DEFAULT_ATTRIBUTE`, `DEFAULT_ENTITY`),
which differ from the public Java field names.

Identifier-under-caret highlighting uses text attributes with a background;
these are not color keys. Editor scrollbar colors live in the XML scheme as
well as the UI JSON, so they follow editor-scheme switching independently.

The visual review also confirmed that HTML/XML do not consistently inherit the
generic markup defaults. Explicit HTML/XML aliases now bind tags, attributes,
attribute values, entities, and comments to the corresponding 1337 defaults;
this also fixes markup in Razor files.
