package test;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BalancesTest {
    private static float INCOME = 1000;
    private static float EXPENSE = 50;
    private static float TOTAL_INCOME = 0;
    private static float TOTAL_EXPENSES = 0;
    private Balances testBalance;
    private Food testFood;
    private Rent testRent;
    private Transportation testTransport;
    private Other testOther;

    @BeforeEach
    public void runBefore() {
        testBalance = new Balances();
        testFood = new Food();
        testRent = new Rent();
        testTransport = new Transportation();
        testOther = new Other();
    }

    @Test
    public void testInitialBalances() {
        assertEquals(testBalance.getIncome(), 0);
        assertEquals(testBalance.getExpenses(), 0);
        assertEquals(testBalance.getBalance(), 0);
    }

    @Test
    public void testAddZeroIncome() {
        float amount = 0;
        assertEquals(testBalance.addIncome(0),TOTAL_INCOME + 0, 0.01);
    }

    @Test
    public void testAddIncome() {
        float amount = INCOME;
        assertEquals(testBalance.addIncome(amount), TOTAL_INCOME + amount, 0.01);
        assertEquals(testBalance.getBalance(),TOTAL_INCOME + amount,0.01);
    }

    @Test
    public void testAddMoreIncome() {
        float amount = INCOME;
        assertEquals(testBalance.addIncome(amount), TOTAL_INCOME + amount, 0.01);
        assertEquals(testBalance.getBalance(),TOTAL_INCOME + amount,0.01);
        assertEquals(testBalance.addIncome(500),TOTAL_INCOME + amount + 500, 0.01);
        assertEquals(testBalance.getBalance(),TOTAL_INCOME + amount + 500,0.01);
    }

    @Test
    public void testAddZeroExpense() {
        float amount = 0;
        assertEquals(testBalance.addExpense(0),TOTAL_EXPENSES + 0, 0.01);
    }

    @Test
    public void testAddExpense() {
        float amount = EXPENSE;
        assertEquals(testBalance.addExpense(amount), TOTAL_EXPENSES + amount, 0.01);
    }

    @Test
    public void testAddMoreExpense() {
        float amount = EXPENSE;
        assertEquals(testBalance.addExpense(amount), TOTAL_EXPENSES + amount, 0.01);
        assertEquals(testBalance.addExpense(50), TOTAL_EXPENSES + amount + 50, 0.01);
    }

    @Test
    public void testBalanceTotal() {
        float totalExpenses = TOTAL_EXPENSES;
        float totalIncome = TOTAL_INCOME;
        assertEquals(testBalance.getBalance(),totalIncome - totalExpenses);
    }

    @Test
    public void testBalanceTotalMultipleIncomeAndExpenses() {
        float totalExpenses = TOTAL_EXPENSES;
        float totalIncome = TOTAL_INCOME;
        assertEquals(testBalance.getBalance(),totalIncome - totalExpenses);
        testBalance.addIncome(25);
        assertEquals(testBalance.getIncome(),totalIncome + 25);
        assertEquals(testBalance.getExpenses(),totalExpenses);
        assertEquals(testBalance.getBalance(),(totalIncome + 25) - totalExpenses);
        testBalance.addExpense(20);
        assertEquals(testBalance.getIncome(),totalIncome + 25);
        assertEquals(testBalance.getExpenses(),totalExpenses + 20);
        assertEquals(testBalance.getBalance(),(totalIncome + 25) - (totalExpenses + 20));

    }

    @Test
    public void testAddExpenseEntryToLists() {
        testBalance.addExpenseEntry("Pie",5, "Food");
        assertTrue(testBalance.nameList.get(0).equals("Pie"));
        assertTrue(testBalance.costList.get(0).floatValue() == 5);
        assertTrue(testBalance.categoryList.get(0).equals("Food"));
    }

    @Test
    public void testAddExpenseEntriesToLists() {
        testBalance.addExpenseEntry("Pie",5, "Food");
        assertTrue(testBalance.nameList.get(0).equals("Pie"));
        assertTrue(testBalance.costList.get(0).floatValue() == 5);
        assertTrue(testBalance.categoryList.get(0).equals("Food"));
        testBalance.addExpenseEntry("Apple",3, "Other");
        assertTrue(testBalance.nameList.get(1).equals("Apple"));
        assertTrue(testBalance.costList.get(1).floatValue() == 3);
        assertTrue(testBalance.categoryList.get(1).equals("Other"));
    }

    @Test
    public void testAddSubExpenseIfFood() {
        String category = "Food";
        float cost = 5;
        assertEquals(testBalance.addSubExpense(category,cost), cost);
    }

    @Test
    public void testAddSubExpenseIfRent() {
        String category = "Rent";
        float cost = 5;
        assertEquals(testBalance.addSubExpense(category,cost), cost);
    }

    @Test
    public void testAddSubExpenseIfTransport() {
        String category = "Transport";
        float cost = 5;
        assertEquals(testBalance.addSubExpense(category,cost), cost);
    }

    @Test
    public void testAddSubExpenseIfOther() {
        String category = "Other";
        float cost = 5;
        assertEquals(testBalance.addSubExpense(category,cost), cost);
    }
}

//tests based from FoodServicesCard Lecture Lab