# Banken-Simulation – Dokumentation

## Überblick
Die Software simuliert eine kleine Bank.  
Man kann Konten erstellen, Geld einzahlen und wieder abheben.  
Alle Bewegungen werden als Buchungen gespeichert, sodass man am Ende den Verlauf eines Kontos nachvollziehen kann.  
Das Klassendiagramm zeigt, wie die Klassen zusammenhängen, und im Code wird dieses Verhalten umgesetzt.

---

## Klassen und Zusammenhänge

### Bank
Die Bank ist die Hauptklasse. Sie verwaltet alle Konten und bietet Methoden an wie:
- `createAccount()` – erstellt ein neues Konto
- `deposit()` – Geld einzahlen
- `withdraw()` – Geld abheben
- `getBalance()` – Kontostand abfragen
- `print()` – Informationen ausgeben  

Die Bank hat also eine Liste mit allen Accounts und ist der Einstiegspunkt in die Simulation.

---

### Account
Das Account ist die Basisklasse für alle Konten.  
Jedes Konto hat eine ID und einen Kontostand. Ausserdem speichert es alle Buchungen, die auf diesem Konto passiert sind.  
Wichtige Methoden sind `deposit()`, `withdraw()`, `canTransact()` und verschiedene `print()`-Varianten.  

---

### Spezielle Kontoarten
Es gibt verschiedene Unterklassen von `Account`, die eigene Regeln haben:
- **SavingsAccount (Sparkonto):** spezielles Verhalten beim Abheben.
- **SalaryAccount (Lohnkonto):** eigenes Verhalten bei Abhebungen und Ausgaben.
- **PromoYouthSavingsAccount (Jugendkonto):** erbt vom Sparkonto, hat aber besondere Regeln beim Einzahlen (z. B. Bonuszahlungen).

---

### Booking
Eine Booking ist eine einzelne Transaktion.  
Sie speichert den Betrag und das Datum der Bewegung und kann sich selbst ausgeben.  
Jedes Konto hat eine Liste von solchen Buchungen.

---

### BankUtils
BankUtils ist eine kleine Hilfsklasse.  
Sie hat Konstanten und Methoden, um Beträge und Daten schön zu formatieren.  
Zum Beispiel werden Geldbeträge auf zwei Nachkommastellen gebracht oder das Datum im Bankformat angezeigt.

---

## Testszenarien

Grundsätzlich sollte man möglichst alles testen, damit man sicher sein kann, dass die Anwendung richtig funktioniert und keine Fehler (Bugs) enthält.  
Man könnte aber auch nur die wichtigsten Funktionen prüfen, wenn man weniger Zeit hat.  

Wichtige Punkte sind zum Beispiel:  

1. **Konten erstellen**  
   – wird ein Konto wirklich mit der richtigen ID angelegt?  

2. **Einzahlungen**  
   – wird der Betrag dem Guthaben korrekt hinzugefügt?  

3. **Abhebungen**  
   – funktioniert das Abheben und greifen die Sonderregeln, zum Beispiel beim Sparkonto?  

4. **Kontostand**  
   – liefert `getBalance()` immer den aktuellen und richtigen Wert zurück?  

5. **Buchungen**  
   – wird jede Einzahlung und Abhebung als Booking gespeichert, sodass man den Verlauf nachvollziehen kann?  

6. **Unterklassen**  
   – verhalten sich die speziellen Kontotypen (Jugendkonto, Lohnkonto) so, wie es vorgesehen ist?  

7. **Ausgabe**  
   – werden Beträge und Daten durch `BankUtils` ordentlich formatiert und angezeigt?  


---

## Fazit
Die Bankensimulation ist ein einfaches, aber gutes Beispiel für Objektorientierung.  
Die Bank verwaltet die Konten, jedes Konto verwaltet seine eigenen Buchungen.  
Durch die Vererbung entstehen verschiedene Kontoarten, die eigene Regeln haben.  
Mit der Hilfsklasse BankUtils wird dafür gesorgt, dass Beträge und Daten lesbar formatiert sind.  
Insgesamt ergibt das eine kleine, nachvollziehbare Simulation einer Bank.
