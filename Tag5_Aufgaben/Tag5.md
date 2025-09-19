
# Übung 1 – REST API Testen (Postman & Unit Tests)

## Ziel der Übung

In dieser Übung haben wir die Spring Boot Backend-Applikation gestartet und eine Möglichkeit gesucht, die REST-Schnittstelle automatisiert zu testen. Zwei unterschiedliche Herangehensweisen wurden umgesetzt:

1. **Testen mit Postman und Test-Skripten**
2. **Testen mit Java Unit Tests direkt im Code (JUnit)**

---

## Vorbereitung

1. **Projekt entpackt**: Die Datei `spring-boot-angular-basic-lw.zip` wurde entpackt.
2. **Backend gestartet**:
   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```


3. Die Applikation war danach unter:

   ```
   http://localhost:8081/students
   ```

   erreichbar und lieferte JSON-Daten zurück:

   ```json
   [
     {
       "id": 1,
       "name": "Jonas",
       "email": "jonas@tbz.ch"
     },
     ...
   ]
   ```

---

## Lösung 1: Test mit Postman

Wir haben die REST-API mit **Postman** getestet.

### Anfrage

* **Methode**: `GET`
* **URL**: `http://localhost:8081/students`

### Tests (Postman-Script im Tab "Tests")

```javascript
pm.test("Status ist 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Antwort ist JSON", function () {
    pm.response.to.be.withBody;
    pm.response.to.be.json;
});

pm.test("Es gibt mindestens 1 Student", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.length).to.be.above(0);
});
```

### Ergebnis

✅ Alle Tests bestanden:

* Status: `200 OK`
* Format: JSON

![alt text](image.png)
---

## Lösung 2: Test mit JUnit (Kollege)

Mein Kollege hat die REST-API direkt über **JUnit Unit Tests** im Code getestet.

### Beispiel-Testklasse: `StudentControllerTest.java`

```java
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllStudents() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }
}
```

### Erklärung

* `MockMvc` wird verwendet, um HTTP-Requests im Testkontext zu simulieren.
* `@SpringBootTest` startet die Spring Boot-Anwendung für den Test.
* `jsonPath("$", hasSize(greaterThan(0)))` prüft, ob mindestens ein Student zurückkommt.

---

## Vergleich der Methoden

| Methode         | Vorteile                              | Nachteile                          |
| --------------- | ------------------------------------- | ---------------------------------- |
| **Postman**     | Schnell, einfach zu bedienen, visuell | Nicht im CI/CD automatisch nutzbar |
| **JUnit Tests** | Automatisierbar, Teil des Codes       | Etwas mehr Setup nötig             |

---

## Fazit

Beide Varianten haben erfolgreich gezeigt, wie man REST-APIs testen kann.
Für manuelles Testen und schnelles Feedback eignet sich **Postman** hervorragend.
Für automatisiertes Testen im Projekt eignet sich **JUnit mit MockMvc** besser.

