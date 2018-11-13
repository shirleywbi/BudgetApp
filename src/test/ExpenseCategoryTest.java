package test;

import exceptions.InvalidEntryException;
import model.Expense;
import model.ExpenseCategory;
import model.ExpenseItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseCategoryTest {
    Expense expense;
    ExpenseItem expenseItem1;
    ExpenseCategory expenseCategory;

    @BeforeEach
    public void setup() {
        expense = new Expense();
        expenseCategory = new ExpenseCategory();
        expenseCategory.setupExpenseCategories();
        expenseItem1 = new ExpenseItem("testItem","Other",5);
    }

    @Test
    public void testSortSubExpenseFood() throws InvalidEntryException {
        assertEquals(expense.getOther().getExpenseAmount(),0);
        expenseCategory.sortToExpenseCategory(expenseItem1);
        assertEquals(expense.getOther().getExpenseAmount(),5);
    }
}
