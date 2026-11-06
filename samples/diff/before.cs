namespace ThemeFixture;

public class Preview
{
    private const string Name = "Default";

    public string Describe()
    {
        return $"Theme: {Name}";
    }

    // This obsolete helper is removed in the next version.
    public bool IsLegacy() => true;
}
