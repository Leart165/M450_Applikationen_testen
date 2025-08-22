using System;

public class PriceCalculatorTest
{
    public static bool TestCalculatePrice()
    {
        PriceCalculator pc = new PriceCalculator();
        bool testOk = true;

        double result1 = pc.CalculatePrice(20000, 1000, 3000, 2, 5);
        if (Math.Abs(result1 - 23000.0) > 0.01) // korrekt berechnet!
        {
            Console.WriteLine($"Test 1 fehlgeschlagen! Erwartet: 23000.0, erhalten: {result1}");
            testOk = false;
        }

        double result2 = pc.CalculatePrice(20000, 1000, 3000, 3, 5);
        if (Math.Abs(result2 - 22700.0) > 0.01) // 10 % auf Extras
        {
            Console.WriteLine($"Test 2 fehlgeschlagen! Erwartet: 22700.0, erhalten: {result2}");
            testOk = false;
        }

        double result3 = pc.CalculatePrice(20000, 1000, 3000, 5, 5);
        if (Math.Abs(result3 - 22550.0) > 0.01) // 15 % auf Extras
        {
            Console.WriteLine($"Test 3 fehlgeschlagen! Erwartet: 22550.0, erhalten: {result3}");
            testOk = false;
        }

        double result4 = pc.CalculatePrice(20000, 1000, 3000, 6, 0);
        if (Math.Abs(result4 - 23550.0) > 0.01) // kein Händler-, aber 15 % Extras-Rabatt
        {
            Console.WriteLine($"Test 4 fehlgeschlagen! Erwartet: 23550.0, erhalten: {result4}");
            testOk = false;
        }

        return testOk;
    }

    public static void Main(string[] args)
    {
        if (TestCalculatePrice())
        {
            Console.WriteLine("Alle Tests erfolgreich!");
        }
        else
        {
            Console.WriteLine("Ein oder mehrere Tests sind fehlgeschlagen.");
        }
    }
}
