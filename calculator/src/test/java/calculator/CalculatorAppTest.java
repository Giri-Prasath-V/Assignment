package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import calculator.Calculator;

class CalculatorAppTest {

    static Calculator c1;

    @BeforeAll
    static void createCalculator() {
        c1 = new Calculator();
    }

    @AfterAll
    static void removeCalculator() {
        c1 = null;
    }

    @Test
    void testAdd() {
        assertEquals(30, c1.add(10, 20), "Error in addition result");
        assertEquals(50, c1.add(30, 20), "Error in addition result");
        assertEquals(-10, c1.add(10, -20), "Error in addition result");
    }
    
    @Test
    void testSub() {
        assertEquals(0, c1.sub(20, 20), "Error in subraction result");
        assertEquals(50, c1.sub(70, 20), "Error in subraction result");
        assertEquals(-10, c1.sub(10, 20), "Error in subraction result");
    }

    @Test
    void testMul() {
        assertAll(
            () -> assertEquals(50, c1.mul(10, 5)),
            () -> assertEquals(100, c1.mul(20, 5)),
            () -> assertEquals(60, c1.mul(10, 6)),
            () -> assertEquals(90, c1.mul(10, 9)),
            () -> assertEquals(100, c1.mul(20, 5))
        );
    }
}
