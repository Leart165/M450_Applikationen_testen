public class PriceCalculator
{
    /// <summary>
    /// Berechnet den Gesamtpreis eines Autos.
    /// </summary>
    /// <param name="baseprice">Grundpreis</param>
    /// <param name="specialprice">Sondermodellaufschlag</param>
    /// <param name="extraprice">Preis für Zusatzausstattungen</param>
    /// <param name="extras">Anzahl Extras</param>
    /// <param name="discount">Händlerrabatt (in %)</param>
    /// <returns>Berechneter Gesamtpreis</returns>
    public double CalculatePrice(double baseprice, double specialprice, double extraprice, int extras, double discount)
    {
        double addonDiscount;

        if (extras >= 5)
            addonDiscount = 15;
        else if (extras >= 3)
            addonDiscount = 10;
        else
            addonDiscount = 0;

        double baseWithDiscount = baseprice * (1 - discount / 100.0);
        double extrasWithDiscount = extraprice * (1 - addonDiscount / 100.0);

        return baseWithDiscount + specialprice + extrasWithDiscount;
    }
}