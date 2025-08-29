## Black-Box-Testfälle (Benutzerfluss, ohne Codekenntnis)

**Legende:**  
Aktion = Benutzereingaben/Schritte in der Konsole  
Erwartung = sichtbares Verhalten/Output, Statusänderung  
Vorbed. = Ausgangslage

```md
| ID | Bereich        | Aktion                                                                 | Vorbed.                                   | Erwartung                                                                                  |
|----|----------------|------------------------------------------------------------------------|-------------------------------------------|--------------------------------------------------------------------------------------------|
| BB1| App-Start      | Start; Hinweis auf Konten (1–N)                                        | Werkszustand (Main)                       | Ausgabe der Anzahl Konten, keine Fehler                                                    |
| BB2| Menü-Navigation| Eingabe: 'a'                                                            | –                                         | Liste aller Konten mit ID, Name, Währung                                                   |
| BB3| Menü-Navigation| Eingabe: 'q'                                                            | –                                         | "Auf Wiedersehen!" und Programmende                                                        |
| BB4| Fehlereingabe  | Eingabe: leer / 'x' / '1x'                                             | –                                         | Meldung „Ungültige Eingabe …“, keine Exceptions                                            |
| BB5| Konto wählen   | Eingabe: gültige Kontonummer (z. B. 1)                                 | Konto existiert                           | Kontodetails werden angezeigt                                                              |
| BB6| Konto wählen   | Eingabe: nicht existierende Nummer (9999)                              | –                                         | Fehlermeldung „Konto … nicht vorhanden“                                                    |
| BB7| Konto erstellen| 'e'; Name=„Miller“; Währung=„CHF“                                      | –                                         | Neues Konto mit ID, Kontostand 0.00 CHF                                                    |
| BB8| Einzahlen      | Konto gewählt; Aktion 'e'; Betrag=100                                  | Saldo 0.00                                | Neuer Saldo 100.00 (Währung des Kontos)                                                    |
| BB9| Abheben OK     | Konto gewählt; Aktion 'a'; Betrag=50                                   | Saldo 100.00                              | Neuer Saldo 50.00                                                                          |
| BB10| Abheben zu viel| Konto gewählt; Aktion 'a'; Betrag=51                                   | Saldo 50.00                               | Meldung „Kontostand zu niedrig …“, Saldo unverändert                                       |
| BB11| Überweisen same curr | Aktion 'ü'; Zielkonto wählen; Betrag=25                         | Beide Konten gleiche Währung, Deckung OK  | Quellkonto −25, Zielkonto +25                                                              |
| BB12| Überweisen diff curr | wie BB11 aber unterschiedliche Währungen                         | Deckung OK                                | Quellkonto reduziert Betrag(Q), Zielkonto erhöht um konvertierten Betrag                   |
| BB13| Überweisen auf sich selbst | Aktion 'ü'; eigene Nummer eingeben                        | –                                         | Fehlermeldung „Bitte ein anderes Konto …“, kein Transfer                                   |
| BB14| Konto löschen  | Aktion 'l'; Bestätigung 'j'                                           | Konto existiert                           | Konto wird gelöscht; erneutes Aufrufen per ID → „existiert nicht mehr“                     |
| BB15| Konto löschen Abbruch | Aktion 'l'; Bestätigung 'n'                                    | –                                         | Abbruchmeldung, Konto bleibt vorhanden                                                     |
| BB16| Wechselkurs-UI | 'w'; Eingabe „CHF USD“                                                 | Netz verfügbar, API erreichbar            | Ausgabe „1 CHF = X USD“                                                                    |
| BB17| Wechselkurs-Fehler | 'w'; Eingabe mit unbekannter Währung / falsches Format            | –                                         | Meldung „Ungültige Eingabe oder unbekannte Währung“                                        |
| BB18| Wechselkurs-API down | 'w'; API nicht erreichbar                                       | –                                         | Fehlermeldung „Error bei der Abfrage …“, kein Crash                                        |
| BB19| Nichtnumerischer Betrag | bei Ein-/Auszahlung „abc“                                    | –                                         | „Ungültige Eingabe, bitte nochmals!“, keine Änderung                                       |
| BB20| Grenzwerte Betrag | 0; sehr kleine Werte; sehr große Werte                             | –                                         | System reagiert konsistent (siehe fachliche Regeln unten)                                  |



## White Box Test
| ID  | Klasse/ Methode                        | Ziel der Prüfung (kontrollfluss-/zweigbasiert)                            |
|-----|----------------------------------------|----------------------------------------------------------------------------|
| WB1 | Account.deposit(double)                | Betrag >0 erhöht Saldo; Betrag ≤0 → Exception/Reject (nach Fix)           |
| WB2 | Account.withdraw(double)               | Pfade: amount>balance → false; amount==balance → true und 0; amount≤0 → false |
| WB3 | Bank.createAccount(...)                | Anlage mit korrekten Feldern; eindeutige IDs                               |
| WB4 | Bank.getAccount(int)                   | Treffer/Nichttreffer; Iterationspfad                                       |
| WB5 | Bank.deleteAccount(Account)            | Entfernt aus Liste; späterer Zugriff liefert null                          |
| WB6 | Counter.convertCurrency(...)           | Alle umgesetzten Paare; Standardfall „keine Umrechnung“                    |
| WB7 | Counter.transferAmount(...)            | Pfade: withdraw false; conversion ja/nein; deposit-Zweig                   |
| WB8 | Counter.transfer(...)                  | Ziel==Quelle → Exceptionpfad; Ziel null; happy path                        |
| WB9 | Counter.chooseAccount()                | Regex-Zweige: Zahl/‘a’/‘e’/‘w’/‘q’; invalid input                          |
| WB10| ExchangeRateOkhttp.getExchangeRate(...)| Erfolgreiche Deserialisierung; Fehlerpfad (IOException/JSON/HTTP !=200)    |



## Verbesserungen
| Thema                 | Empfehlung                                                                                           | Nutzen                                    |
|-----------------------|------------------------------------------------------------------------------------------------------|-------------------------------------------|
| Geldtyp               | `BigDecimal` + `RoundingMode` + Währungsspezifische Skalierung                                      | Exakte Beträge, keine IEEE-Rundungsfehler |
| Validierung Beträge   | Zentral: Betrag > 0 in `deposit`/`withdraw`; Transfer validiert amount und Konten                   | Schließt Logiklücken                      |
| Architektur           | I/O trennen: Business-Service (Transfer/Conversion) + Ports (Console/UI)                             | Testbarkeit, klare Verantwortungen        |
| Dependency Injection  | `Scanner`/Streams/Converter/RateClient injizieren (Interfaces)                                       | Mocking im Test                           |
| Wechselkurs           | Vollständige Matrix oder Map<Pair,Rate>; Fehler bei unbekanntem Paar; externer Provider via Interface| Vorhersagbares Verhalten                  |
| OkHttp                | Timeouts setzen; `try (Response r = client.newCall(...).execute()) {}`; API-Key via Config/ENV       | Stabilität, Sicherheit                    |
| Fehlerbehandlung      | API-Fehler explizit differenzieren; kein `0.0` als Fehlerwert                                        | Klare Orakel für Tests                    |
| Regex/Parsing         | `matches("^(\\d+|[aewq])$")` statt `find()`; Fehlermeldung korrigieren („w“ statt „u“)               | Robustere Eingaben                        |
| Beenden               | Kein `System.exit(0)` in Logik; Rückgabewerte/States verwenden                                       | Testbarkeit                               |
| Logging               | `System.out` → Logger (SLF4J/Java Logging)                                                           | Diagnose                                  |
| Internationalisierung | Dezimaltrennzeichen/Locale beachten; formatiertes Ausgeben                                           | Schweiz-Konformität                       |
| Thread-Sicherheit     | `Account.counter` thread-safe machen oder dokumentieren (falls später erweitert)                     | Korrekte IDs                              |


Auch sollten API keys nicht im Code gespeichert werden!