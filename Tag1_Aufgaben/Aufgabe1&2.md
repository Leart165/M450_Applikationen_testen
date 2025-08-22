# Lösungen zu den Test-Aufgaben

## Aufgabe 1: Welche Testarten gibt es in der Informatik?

Es gibt verschiedene Arten von Softwaretests, um sicherzustellen, dass Programme korrekt funktionieren. Hier sind drei Beispiele, die wir aus der Praxis kennen:

### 1. Unit-Test (Modultest)
- **Was wird getestet?** Einzelne Funktionen oder Methoden.
- **Beispiel**: Eine Funktion, die die Mehrwertsteuer berechnet, wird getestet, ob sie bei verschiedenen Preisen das richtige Resultat liefert, dies kann man dan mit einem Assert oder auch verify prüfen.
- **Wie wird getestet?** Meist automatisch mit Test-Tools wie JUnit (Java), Jest (JavaScript) oder xUnit (C#).

### 2. Integrationstest
- **Was wird getestet?** Ob verschiedene Teile der Software gut zusammenspielen.
- **Beispiel**: Ob ein Login-System richtig mit der Datenbank kommuniziert.
- **Wie wird getestet?** Teilweise automatisch, z. B. mit Tools wie Postman oder direkt im Code.

### 3. End-to-End-Test (Systemtest)
- **Was wird getestet?** Die ganze Anwendung vom Anfang bis zum Ende aus Sicht der Benutzerin / des Benutzers (EIne Art Demo durchführung).
- **Beispiel**: In einer Online-Banking-App wird geprüft, ob eine Überweisung vom Login bis zur Bestätigung funktioniert.
- **Wie wird getestet?** Oft automatisiert mit Tools wie Selenium oder Cypress, manchmal auch manuell.

---

## Aufgabe 2: Fehler, Mangel und Schaden – Beispiele

### Beispiel für einen Softwarefehler:
Ein Taschenrechner zeigt beim Teilen durch null einfach "0" an, anstatt eine Fehlermeldung. Der Code behandelt diesen Fall nicht korrekt.

### Beispiel für einen Softwaremangel:
Eine Website funktioniert technisch einwandfrei, ist aber nur auf Deutsch verfügbar – obwohl viele Nutzerinnen und Nutzer nur Englisch verstehen. Der Wunsch oder Bedarf wird also nicht erfüllt.

### Beispiel für einen hohen Schaden durch einen Softwarefehler:
1996 stürzte die Ariane-5-Rakete ab, weil ein Softwarefehler bei der Umrechnung von Zahlen zu einem Absturz führte. Der Schaden betrug etwa 370 Millionen US-Dollar.


