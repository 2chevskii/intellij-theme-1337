import java.util.List;

public final class ThemePreview {
    private static final int VERSION = 1337;

    // Comments, types, parameters, strings, and function calls.
    public static String describe(String name) {
        return String.format("%s theme, version %d", name, VERSION);
    }

    public static void main(String[] args) {
        List<String> themes = List.of("1337", "Dark");
        themes.forEach(name -> System.out.println(describe(name)));
    }
}
