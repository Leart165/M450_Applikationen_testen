package test;

import main.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calc;
    private static final double EPS = 1e-9;

    @BeforeEach
    void setUp() { calc = new Calculator(); }

    // Addition
    @Test @DisplayName("add: positive Zahlen")
    void add_positive() { assertEquals(7.0, calc.add(3.0, 4.0), EPS); }
    @Test @DisplayName("add: mit Negativzahl")
    void add_withNegative() { assertEquals(-1.0, calc.add(3.0, -4.0), EPS); }
    @Test @DisplayName("add: mit 0")
    void add_withZero() { assertEquals(5.0, calc.add(5.0, 0.0), EPS); }

    // Subtraktion
    @Test @DisplayName("subtract: Standardfall")
    void subtract_basic() { assertEquals(-1.0, calc.subtract(3.0, 4.0), EPS); }
    @Test @DisplayName("subtract: minus Negativzahl")
    void subtract_minusNegative() { assertEquals(7.0, calc.subtract(3.0, -4.0), EPS); }
    @Test @DisplayName("subtract: mit 0")
    void subtract_withZero() { assertEquals(5.0, calc.subtract(5.0, 0.0), EPS); }

    // Multiplikation
    @Test @DisplayName("multiply: Standardfall")
    void multiply_basic() { assertEquals(12.0, calc.multiply(3.0, 4.0), EPS); }
    @Test @DisplayName("multiply: mit 0")
    void multiply_withZero() { assertEquals(0.0, calc.multiply(5.0, 0.0), EPS); }
    @Test @DisplayName("multiply: mit Negativzahl")
    void multiply_withNegative() { assertEquals(-12.0, calc.multiply(-3.0, 4.0), EPS); }

    // Division
    @Test @DisplayName("divide: Standardfall")
    void divide_basic() { assertEquals(2.0, calc.divide(8.0, 4.0), EPS); }
    @Test @DisplayName("divide: negatives Ergebnis")
    void divide_negative() { assertEquals(-2.5, calc.divide(5.0, -2.0), EPS); }
    @Test @DisplayName("divide: nicht exakt (Rundungstoleranz)")
    void divide_notExact() { assertEquals(1.0/3.0, calc.divide(1.0, 3.0), EPS); }
    @Test @DisplayName("divide: Division durch 0 wirft Exception (inkl. -0.0)")
    void divide_byZero_throws() {
        assertThrows(ArithmeticException.class, () -> calc.divide(1.0, 0.0));
        assertThrows(ArithmeticException.class, () -> calc.divide(1.0, -0.0));
    }
}