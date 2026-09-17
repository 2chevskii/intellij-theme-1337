# Sublime 1337 to Rider mapping

The source of truth is `1337.sublime-color-scheme` from the upstream 1337
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
| `storage.type` | type keywords | `#FBDFB5`, italic |
| class and function names | classes, interfaces, methods, functions | `#8CDAFF`; classes underlined |
| inherited class | class reference | `#8CDAFF`, italic underline |
| `variable.parameter` | parameters | `#FC9354`, italic |
| markup attributes | XML/HTML/Razor attributes | `#97D8EA` |
| library functions | predefined/static functions | `#6699CC` |
| library constants | predefined constants | `#ECFDB9` |
| `this`, `base`, `super`, `self` | instance/language literals | `#D699FF` |
| punctuation | operators, separators, braces | `#FFFFFF` |
| invalid / deprecated | bad character / deprecated symbols | `#F8F8F0` on `#F92649` / `#FF9664` |
| GitGutter and diff | VCS statuses and diff attributes | added `#A6E22E`, deleted `#F92672`, changed `#CC00FF` |

The source's PHP and Twig-specific scopes do not have Rider equivalents. Their
colors remain available as named palette values for bundled-language overrides
when an equivalent semantic role is discovered through **Jump to Colors and
Fonts**.
