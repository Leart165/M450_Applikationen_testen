# Übung 1 – Testfälle für Verkaufssoftware

## Erklärung

Man unterscheidet zwischen:  
- **Abstrakten Testfällen**: allgemeine Regeln mit logischen Operatoren (<, ≤, >, …).  
- **Konkreten Testfällen**: ganz konkrete Eingabewerte und die dazugehörigen erwarteten Ergebnisse.  

---

## Abstrakte Testfälle

| Preis-Bedingung | Rabatt | Berechnung Rabatt in CHF | Total |
|-----------------|--------|--------------------------|-------|
| < 15’000        | 0 %    | Preis × 0 %              | Preis |
| ≤ 20’000        | 5 %    | Preis × 5 %              | Preis – Rabatt |
| ≤ 25’000        | 7 %    | Preis × 7 %              | Preis – Rabatt |
| > 25’000        | 9 %    | Preis × 9 %              | Preis – Rabatt |

---

## Konkrete Testfälle

| Preis (CHF) | Bedingung | Rabatt | Rabatt in CHF | Total |
|-------------|-----------|--------|---------------|-------|
| 14’000.00   | < 15’000  | 0 %    | 0.00          | 14’000.00 |
| 19’000.00   | ≤ 20’000  | 5 %    | 950.00        | 18’050.00 |
| 23’000.00   | ≤ 25’000  | 7 %    | 1’610.00      | 21’390.00 |
| 26’000.00   | > 25’000  | 9 %    | 2’210.00      | 23’790.00 |

---

## Fazit
- **Abstrakt**: deckt die Regeln mit Bedingungen ab, ohne konkrete Zahlen.  
- **Konkret**: verwendet echte Testwerte, um die Regeln praktisch zu überprüfen.   