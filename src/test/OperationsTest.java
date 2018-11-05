package test;

import exceptions.NegativeAmountException;
import model.ExpenseItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.BudgetTracker;
import ui.Operations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OperationsTest {
    BudgetTracker testBudgetTracker;
    Operations testOp;
    ExpenseItem item0, item1, item2, item3, item4;

    @BeforeEach
    public void runBefore() {
        testBudgetTracker = new BudgetTracker();
        testBudgetTracker.setupBudgetTracker();
        testOp = new Operations();
        loadExpenseItems();
    }

    public void loadExpenseItems() {
        item0 = new ExpenseItem("Apple", "Food", 10);
        item1 = new ExpenseItem("BusPass", "Transportation", 93);
        item2 = new ExpenseItem("Rent", "Rent", 800);
        item3 = new ExpenseItem("Pikachu", "Other", 97);
        item4 = new ExpenseItem("Balloon", "Other", 3);
    }

    @Test
    public void testLoad() throws IOException, NegativeAmountException {
        float income = 1200;
        float expense = 1000;
        testOp.load("testinput.txt");
        assertEquals(testOp.getIncome().getIncomeTotal(), income);
        assertEquals(testOp.getExpense().getExpenseAmount(), expense);
        assertEquals(testOp.getExpense().getExpenseItems().get(0), item0);
        assertEquals(testOp.getExpense().getExpenseItems().get(1), item1);
        assertEquals(testOp.getExpense().getExpenseItems().get(2), item2);
        assertEquals(testOp.getExpense().getExpenseItems().get(3), item3);
    }

    @Test
    public void testSaveChanges() throws IOException, NegativeAmountException {
        assertEquals(testOp.getExpense().getExpenseItems().size(),0);
        testOp.load("testinput.txt");
        assertEquals(testOp.getExpense().getExpenseItems().size(),4);
        testOp.getIncome().addIncome(501);
        testOp.getExpense().addExpenseAmount(351);
        testOp.getExpense().addExpenseItem(item4);
        assertTrue(testOp.getExpense().getExpenseItems().size() == 5);
        testOp.save("testoutput.txt");
        assertEquals(testOp.getIncome().getIncomeTotal(), 1200 + 501);
        assertEquals(testOp.getExpense().getExpenseAmount(), 1000 + 351);
        assertEquals(testOp.getExpense().getExpenseItems().get(0), item0);
        assertEquals(testOp.getExpense().getExpenseItems().get(1), item1);
        assertEquals(testOp.getExpense().getExpenseItems().get(2), item2);
        assertEquals(testOp.getExpense().getExpenseItems().get(3), item3);
        assertEquals(testOp.getExpense().getExpenseItems().get(4), item4);
        assertEquals(testOp.getExpense().getExpenseItems().size(),5);
    }

}
