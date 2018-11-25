package test;

import exceptions.InvalidEntryException;
import exceptions.NegativeAmountException;
import model.Expense;
import model.ExpenseItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ui.panel.ExpensePanel.expense;

public class ExpenseTest {
    private Expense testFoodExpense;
    private Expense testRentExpense;
    private Expense testTransportationExpense;
    private Expense testEntertainmentExpense;
    private Expense testHealthExpense;
    private Expense testOtherExpense;
    private Expense testExpense;
    private ExpenseItem expenseItem1;
    private ExpenseItem expenseItem2;

    @BeforeEach
    public void runBefore() {
        testExpense = new Expense();
        testFoodExpense = new Expense("Food", 0);
        testRentExpense = new Expense("Rent", 0);
        testTransportationExpense = new Expense("Transportation", 0);
        testEntertainmentExpense = new Expense("Entertainment", 0);
        testHealthExpense = new Expense("Health", 0);
        testOtherExpense = new Expense("Other", 0);

        expenseItem1 = new ExpenseItem("Pie","Food",5);
        expenseItem2 = new ExpenseItem("Item","Other",10);
    }

    @Test
    public void testInitialBalances() {
        assertEquals(testExpense.getExpenseTotal(), 0);
        assertEquals(testFoodExpense.getExpenseTotal(), 0);
        assertEquals(testRentExpense.getExpenseTotal(), 0);
        assertEquals(testTransportationExpense.getExpenseTotal(), 0);
        assertEquals(testEntertainmentExpense.getExpenseTotal(), 0);
        assertEquals(testHealthExpense.getExpenseTotal(), 0);
        assertEquals(testOtherExpense.getExpenseTotal(), 0);
        assertEquals(testExpense.getExpenseItems().size(), 0);
    }

    //Tests for Expense
    @Test
    public void testAddExpenseZero() throws NegativeAmountException {
        testExpense.addExpenseAmount(0);
        assertTrue(testExpense.getExpenseTotal() == 0);
    }

    @Test
    public void testAddExpenseOne() throws NegativeAmountException {
        testExpense.addExpenseAmount(1);
        assertTrue(testExpense.getExpenseTotal() == 1);
    }

    @Test
    public void testAddExpenseMore() throws NegativeAmountException {
        testExpense.addExpenseAmount(1);
        assertTrue(testExpense.getExpenseTotal() == 1);
        testExpense.addExpenseAmount(50);
        assertTrue(testExpense.getExpenseTotal() == 50 + 1);
    }

    //Tests for Categories
    @Test
    public void testAddExpenseCategoryZero() throws NegativeAmountException {
        testFoodExpense.addExpenseAmount(0);
        assertTrue(testFoodExpense.getExpenseTotal() == 0);
    }

    @Test
    public void testAddExpenseCategoryOne() throws NegativeAmountException {
        testFoodExpense.addExpenseAmount(1);
        assertTrue(testFoodExpense.getExpenseTotal() == 1);
    }

    @Test
    public void testAddExpenseCategoryMore() throws NegativeAmountException {
        testFoodExpense.addExpenseAmount(1);
        assertTrue(testFoodExpense.getExpenseTotal() == 1);
        testFoodExpense.addExpenseAmount(50);
        assertTrue(testFoodExpense.getExpenseTotal() == 50 + 1);
    }

    //TODO: Bug - expenseItems is not resetting after this test
    @Test
    public void testAddExpenseItemsToLists() {
        testExpense.addExpenseItem(expenseItem1);
        testExpense.getExpenseItems().contains(expenseItem1);
    }

    @Test
    public void testAddFoodExpenseToListsMultiple() {
        assertTrue(testExpense.getExpenseItems().size() == 0);
        testExpense.addExpenseItem(expenseItem1);
        testExpense.addExpenseItem(expenseItem2);
        assertTrue(testExpense.getExpenseItems().size() == 2);
        assertTrue(testExpense.getExpenseItems().get(0).equals(expenseItem1));
        assertTrue(testExpense.getExpenseItems().get(1).equals(expenseItem2));
    }

    @Test
    public void testSortExpense() throws InvalidEntryException {
        assertEquals(expense.getOther().getExpenseTotal(),0);
        expense.sortExpense(expenseItem1);
        assertEquals(expense.getOther().getExpenseTotal(),5);
    }

}