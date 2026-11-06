namespace ThemeFixture;

public class Preview
{
    private const string Name = "1337";

    public string Describe()
    {
        return $"Theme: {Name}";
    }

    // A coordinated dark palette for the editor and UI.
    public string Background => "#191919";

    public bool IsEnabled() => true;
}
