package com.demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Nested
    @DisplayName("add()")
    class Add {

        @Test
        @DisplayName("returns sum of two positive numbers")
        void positiveNumbers() {
            assertEquals(5.0, calculator.add(2.0, 3.0));
        }

        @Test
        @DisplayName("returns sum of two negative numbers")
        void negativeNumbers() {
            assertEquals(-5.0, calculator.add(-2.0, -3.0));
        }

        @Test
        @DisplayName("returns correct result when adding zero")
        void withZero() {
            assertEquals(5.0, calculator.add(5.0, 0.0));
        }

        @Test
        @DisplayName("handles decimal values correctly")
        void decimalValues() {
            assertEquals(5.5, calculator.add(2.2, 3.3), 0.001);
        }
    }

    @Nested
    @DisplayName("subtract()")
    class Subtract {

        @Test
        @DisplayName("returns difference of two positive numbers")
        void positiveNumbers() {
            assertEquals(2.0, calculator.subtract(5.0, 3.0));
        }

        @Test
        @DisplayName("returns negative result when subtracting larger from smaller")
        void negativeResult() {
            assertEquals(-2.0, calculator.subtract(3.0, 5.0));
        }

        @Test
        @DisplayName("returns same number when subtracting zero")
        void subtractZero() {
            assertEquals(5.0, calculator.subtract(5.0, 0.0));
        }

        @Test
        @DisplayName("handles decimal values correctly")
        void decimalValues() {
            assertEquals(1.1, calculator.subtract(3.3, 2.2), 0.001);
        }
    }

    @Nested
    @DisplayName("multiply()")
    class Multiply {

        @Test
        @DisplayName("returns product of two positive numbers")
        void positiveNumbers() {
            assertEquals(15.0, calculator.multiply(3.0, 5.0));
        }

        @Test
        @DisplayName("returns zero when multiplying by zero")
        void multiplyByZero() {
            assertEquals(0.0, calculator.multiply(5.0, 0.0));
        }

        @Test
        @DisplayName("returns negative result when multiplying by negative number")
        void negativeNumber() {
            assertEquals(-15.0, calculator.multiply(3.0, -5.0));
        }

        @Test
        @DisplayName("returns positive result when multiplying two negative numbers")
        void twoNegativeNumbers() {
            assertEquals(15.0, calculator.multiply(-3.0, -5.0));
        }

        @Test
        @DisplayName("handles decimal values correctly")
        void decimalValues() {
            assertEquals(6.6, calculator.multiply(2.2, 3.0), 0.001);
        }
    }

    @Nested
    @DisplayName("divide()")
    class Divide {

        @Test
        @DisplayName("returns quotient of two positive numbers")
        void positiveNumbers() {
            assertEquals(2.5, calculator.divide(5.0, 2.0));
        }

        @Test
        @DisplayName("returns negative result when dividing by negative number")
        void negativeNumber() {
            assertEquals(-2.5, calculator.divide(5.0, -2.0));
        }

        @Test
        @DisplayName("returns positive result when dividing two negative numbers")
        void twoNegativeNumbers() {
            assertEquals(2.5, calculator.divide(-5.0, -2.0));
        }

        @Test
        @DisplayName("throws IllegalArgumentException when dividing by zero")
        void divideByZero() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.divide(5.0, 0.0)
            );
            assertEquals("Cannot divide by zero", exception.getMessage());
        }

        @Test
        @DisplayName("returns zero when dividing zero by non-zero number")
        void divideZero() {
            assertEquals(0.0, calculator.divide(0.0, 5.0));
        }
    }

    @Nested
    @DisplayName("power()")
    class Power {

        @Test
        @DisplayName("returns correct result for positive base and exponent")
        void positiveBaseAndExponent() {
            assertEquals(8.0, calculator.power(2.0, 3));
        }

        @Test
        @DisplayName("returns 1 when exponent is zero")
        void exponentZero() {
            assertEquals(1.0, calculator.power(5.0, 0));
        }

        @Test
        @DisplayName("returns base when exponent is 1")
        void exponentOne() {
            assertEquals(5.0, calculator.power(5.0, 1));
        }

        @Test
        @DisplayName("handles negative base correctly")
        void negativeBase() {
            assertEquals(-8.0, calculator.power(-2.0, 3));
        }

        @Test
        @DisplayName("handles zero base correctly")
        void zeroBase() {
            assertEquals(0.0, calculator.power(0.0, 5));
        }

        @Test
        @DisplayName("throws IllegalArgumentException for negative exponent")
        void negativeExponent() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.power(2.0, -3)
            );
            assertEquals("Exponent must be non-negative", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("squareRoot()")
    class SquareRoot {

        @Test
        @DisplayName("returns correct square root for positive number")
        void positiveNumber() {
            assertEquals(3.0, calculator.squareRoot(9.0), 0.001);
        }

        @Test
        @DisplayName("returns zero for zero input")
        void zeroInput() {
            assertEquals(0.0, calculator.squareRoot(0.0));
        }

        @Test
        @DisplayName("returns correct result for decimal values")
        void decimalValue() {
            assertEquals(2.0, calculator.squareRoot(4.0), 0.001);
        }

        @Test
        @DisplayName("throws IllegalArgumentException for negative number")
        void negativeNumber() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.squareRoot(-9.0)
            );
            assertEquals("Cannot compute square root of a negative number", exception.getMessage());
        }
    }
}
