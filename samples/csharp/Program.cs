using System;

namespace ThemeFixture;

[Obsolete("Fixture only")]
public sealed class Example<T> where T : class
{
    private const int Answer = 42;
    private readonly string _message = "Hello, 1337!";

    public void Print(T value)
    {
        // Comment with a number: 7
        Console.WriteLine($"{_message}: {value ?? throw new ArgumentNullException(nameof(value))}");
    }
}
