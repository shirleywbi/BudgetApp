package test;

import model.ExpenseItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

//no tests for getters
public class ExpenseItemTest {

    @Test
    public void testInitializeExpenseItem() {
        ExpenseItem testItem = new ExpenseItem("NAME","FOOD",50.55);
        assertTrue(testItem.getExpenseItemName().equals("NAME"));
        assertTrue(testItem.getExpenseItemCategory().equals("FOOD"));
        assertTrue(testItem.getExpenseItemCost() == 50.55);
    }

}
