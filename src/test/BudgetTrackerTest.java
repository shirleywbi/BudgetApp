package test;

import model.Balances;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.BudgetTracker;
import ui.Operations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetTrackerTest {
    Operations testOp;
    Balances testBalance;

    @BeforeEach
    public void runBefore() {
        testOp = new Operations();
        testBalance = new Balances();
    }

    @Test
    public void testLoad() throws IOException {
        float income = 1200;
        float expense = 1000;
        testOp.load("testBudgetInput.txt");
        assertEquals(testBalance.getIncome(),income);
        assertEquals(testBalance.getExpenses(), expense);
        assertEquals(testBalance.categoryList.get(0), "Food");
        assertEquals(testBalance.nameList.get(0),"Apple");
        assertEquals(testBalance.costList.get(0),10);
        assertEquals(testBalance.categoryList.get(1), "Transportation");
        assertEquals(testBalance.nameList.get(1),"BusPass");
        assertEquals(testBalance.costList.get(1),93);
        assertEquals(testBalance.categoryList.get(2), "Rent");
        assertEquals(testBalance.nameList.get(2),"Rent");
        assertEquals(testBalance.costList.get(2),800);
        assertEquals(testBalance.categoryList.get(3), "Other");
        assertEquals(testBalance.nameList.get(3),"Pikachu");
        assertEquals(testBalance.costList.get(3),97);
    }

    @Test
    public void testSaveNoChange() throws IOException{
        testLoad();
    }

    @Test
    public void testSaveChanges() throws IOException{
        float income = 1200;
        float expense = 1000;
        testBalance.addIncome(501);
        testBalance.addExpense(351);
        testBalance.addExpenseEntry("Balloon",3,"Other");
        testOp.save();
        testOp.load("testBudgetOutput.txt");
        assertEquals(testBalance.getIncome(),income + 501);
        assertEquals(testBalance.getExpenses(), expense + 351);
        assertEquals(testBalance.categoryList.get(0), "Food");
        assertEquals(testBalance.nameList.get(0),"Apple");
        assertEquals(testBalance.costList.get(0),10);
        assertEquals(testBalance.categoryList.get(1), "Transportation");
        assertEquals(testBalance.nameList.get(1),"BusPass");
        assertEquals(testBalance.costList.get(1),93);
        assertEquals(testBalance.categoryList.get(2), "Rent");
        assertEquals(testBalance.nameList.get(2),"Rent");
        assertEquals(testBalance.costList.get(2),800);
        assertEquals(testBalance.categoryList.get(3), "Other");
        assertEquals(testBalance.nameList.get(3),"Pikachu");
        assertEquals(testBalance.costList.get(3),97);
        assertEquals(testBalance.categoryList.get(4), "Other");
        assertEquals(testBalance.nameList.get(4),"Balloon");
        assertEquals(testBalance.costList.get(4),3);

    }

}
