package test;

import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpenseTest {
    private Food testFoodExpense;
    private Rent testRentExpense;
    private Transportation testTransportationExpense;
    private Other testOtherExpense;

    @BeforeEach
    public void runBefore() {
        testFoodExpense = new Food();
        testRentExpense = new Rent();
        testTransportationExpense = new Transportation();
        testOtherExpense = new Other();
    }

    @Test
    public void testInitialBalances() {
        assertEquals(testFoodExpense.getSubExpense(), 0);
        assertEquals(testRentExpense.getSubExpense(), 0);
        assertEquals(testTransportationExpense.getSubExpense(), 0);
        assertEquals(testOtherExpense.getSubExpense(), 0);
    }

    @Test
    public void testAddFoodSubExpenseZero() {
        testFoodExpense.addSubExpense(0);
        assertTrue(testFoodExpense.getSubExpense() == 0);
    }

    @Test
    public void testAddFoodSubExpenseOne() {
        testFoodExpense.addSubExpense(1);
        assertTrue(testFoodExpense.getSubExpense() == 1);
    }

    @Test
    public void testAddFoodSubExpenseMore() {
        testFoodExpense.addSubExpense(1);
        assertTrue(testFoodExpense.getSubExpense() == 1);
        testFoodExpense.addSubExpense(50);
        assertTrue(testFoodExpense.getSubExpense() == 50 + 1);
    }

    @Test
    public void testAddRentSubExpenseZero() {
        testRentExpense.addSubExpense(0);
        assertTrue(testRentExpense.getSubExpense() == 0);
    }

    @Test
    public void testAddRentSubExpenseOne() {
        testRentExpense.addSubExpense(1);
        assertTrue(testRentExpense.getSubExpense() == 1);
    }

    @Test
    public void testAddRentSubExpenseMore() {
        testRentExpense.addSubExpense(1);
        assertTrue(testRentExpense.getSubExpense() == 1);
        testRentExpense.addSubExpense(50);
        assertTrue(testRentExpense.getSubExpense() == 50 + 1);
    }

    @Test
    public void testAddTransportationSubExpenseZero() {
        testTransportationExpense.addSubExpense(0);
        assertTrue(testTransportationExpense.getSubExpense() == 0);
    }

    @Test
    public void testAddTransportationSubExpenseOne() {
        testTransportationExpense.addSubExpense(1);
        assertTrue(testTransportationExpense.getSubExpense() == 1);
    }

    @Test
    public void testAddTransportationSubExpenseMore() {
        testTransportationExpense.addSubExpense(1);
        assertTrue(testTransportationExpense.getSubExpense() == 1);
        testTransportationExpense.addSubExpense(50);
        assertTrue(testTransportationExpense.getSubExpense() == 50 + 1);
    }

    @Test
    public void testAddOtherSubExpenseZero() {
        testOtherExpense.addSubExpense(0);
        assertTrue(testOtherExpense.getSubExpense() == 0);
    }

    @Test
    public void testAddOtherSubExpenseOne() {
        testOtherExpense.addSubExpense(1);
        assertTrue(testOtherExpense.getSubExpense() == 1);
    }

    @Test
    public void testAddOtherSubExpenseMore() {
        testOtherExpense.addSubExpense(1);
        assertTrue(testOtherExpense.getSubExpense() == 1);
        testOtherExpense.addSubExpense(50);
        assertTrue(testOtherExpense.getSubExpense() == 50 + 1);
    }
}
