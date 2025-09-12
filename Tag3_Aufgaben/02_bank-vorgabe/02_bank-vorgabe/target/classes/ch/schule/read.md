## JUnit – Zusammenfassung der wichtigsten Features

JUnit ist ein weit verbreitetes Framework zum Testen von Java-Anwendungen. Es unterstützt Entwickler:innen dabei, Unit-Tests einfach zu schreiben, auszuführen und zu organisieren.

Wichtige Features

### 1. Annotationen

#### @Test

Markiert eine Methode als Testmethode.

```
@Test
void addTwoNumbers_shouldReturnSum() {
    assertEquals(4, Calculator.add(2, 2));
}
```

#### @BeforeEach

Führt Code vor jedem Test aus (z. B. Initialisierung).

```
@BeforeEach
void setUp() {
    calculator = new Calculator();
}
```

#### @AfterEach

Führt Code nach jedem Test aus (z. B. Ressourcen freigeben).

#### @BeforeAll / @AfterAll

Werden einmal vor bzw. nach allen Tests ausgeführt (z. B. Datenbankverbindung aufbauen/schliessen).

```
@BeforeAll
static void initAll() {
    System.out.println("Start der Testsuite");
}
```

### 2. Assertions

Assertions prüfen, ob ein erwartetes Ergebnis mit dem tatsächlichen übereinstimmt.

assertEquals(expected, actual)

assertTrue(condition) / assertFalse(condition)

assertThrows(Exception.class, () -> { ... })

```
@Test
void divideByZero_shouldThrowException() {
    assertThrows(ArithmeticException.class, () -> Calculator.divide(10, 0));
}
```

### 3. Test Suites

Mehrere Testklassen können in einer Testsuite zusammengefasst werden.

```
@RunWith(Suite.class)
@Suite.SuiteClasses({
    CalculatorTest.class,
    StringUtilsTest.class
})
public class AllTests {}
```

### 4. Parameterized Tests

Erlauben es, denselben Test mit verschiedenen Eingabewerten auszuführen.

```
@ParameterizedTest
@ValueSource(ints = {1, 2, 3, 5})
void isOdd_shouldReturnTrueForOddNumbers(int number) {
    assertTrue(Calculator.isOdd(number));
}
```

### 5. Assumptions

Ermöglichen das Bedingte Ausführen von Tests.

```
@Test
void runOnlyOnWindows() {
    assumeTrue(System.getProperty("os.name").startsWith("Windows"));
    // Testcode wird nur auf Windows ausgeführt
}
```

### 6. Test Lifecycle

JUnit erlaubt die Strukturierung von Tests durch verschiedene Phasen:

Setup (@BeforeEach, @BeforeAll)

Test (@Test)

Teardown (@AfterEach, @AfterAll)