//package test;
//
//import exceptions.InvalidEntryException;
//import exceptions.NegativeAmountException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ui.BudgetTracker;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.util.Scanner;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//public class InvalidEntryExceptionTest {
//    BudgetTracker budgetTracker;
//
//    @BeforeEach
//    public void setup() {
//        budgetTracker = new BudgetTracker();
//        budgetTracker.income.setIncome(0);
//        budgetTracker.expense.setExpense(0);
//    }
//
//    @Test
//    public void testLogIncomeNegativeAmountExceptionPositiveInputFloat() {
//        assertEquals(budgetTracker.income.getIncomeTotal(), 0);
//        String input = "1";
//        InputStream is = new ByteArrayInputStream(input.getBytes());
//        budgetTracker.entry = new Scanner(is);
//        try {
//            budgetTracker.logIncome();
//        } catch (NegativeAmountException e) {
//            fail("NegativeAmountException thrown for positive number");
//        } catch (NumberFormatException e) {
//            fail("NumberFormatException thrown for float number");
//        }
//        assertEquals(budgetTracker.income.getIncomeTotal(), 1);
//    }
//
//    @Test
//    public void testLogIncomeNegativeAmountExceptionZeroInputFloat() {
//        assertEquals(budgetTracker.income.getIncomeTotal(), 0);
//        String input = "0";
//        InputStream is = new ByteArrayInputStream(input.getBytes());
//        budgetTracker.entry = new Scanner(is);
//        try {
//            budgetTracker.logIncome();
//        } catch (NegativeAmountException e) {
//            fail("NegativeAmountException thrown for zero");
//        } catch (NumberFormatException e) {
//            fail("NumberFormatException thrown for float number");
//        }
//        assertEquals(budgetTracker.income.getIncomeTotal(), 0);
//    }
//
//    @Test
//    public void testLogIncomeNegativeAmountExceptionNegativeInputFloat() {
//        assertEquals(budgetTracker.income.getIncomeTotal(), 0);
//        String input = "-1";
//        InputStream is = new ByteArrayInputStream(input.getBytes());
//        budgetTracker.entry = new Scanner(is);
//        try {
//            budgetTracker.logIncome();
//        } catch (NegativeAmountException e) {
//            //do nothing
//        } catch (NumberFormatException e) {
//            fail("NumberFormatException thrown for float number");
//        }
//        assertEquals(budgetTracker.income.getIncomeTotal(), 0);
//    }
//
//    @Test
//    public void testLogIncomeNumberFormatExceptionNonNumeric() {
//        assertEquals(budgetTracker.income.getIncomeTotal(), 0);
//        String input = "test";
//        InputStream is = new ByteArrayInputStream(input.getBytes());
//        budgetTracker.entry = new Scanner(is);
//        try {
//            budgetTracker.logIncome();
//        } catch (NumberFormatException e) {
//            //do nothing
//        } catch (NegativeAmountException e) {
//            fail("NegativeAmountException thrown for non-negative");
//        }
//        assertEquals(budgetTracker.income.getIncomeTotal(), 0);
//    }
//
//    @Test
//    public void testAddExpenseNegativeAmountExceptionPositiveInputFloat() {
//        assertEquals(budgetTracker.expense.getExpenseTotal(), 0);
//        try {
//            budgetTracker.expense.addExpense(1);
//        } catch (NegativeAmountException e) {
//            fail("NegativeAmountException thrown for positive number");
//        }
//        assertEquals(budgetTracker.expense.getExpenseTotal(), 1);
//    }
//
//    @Test
//    public void testAddExpenseNegativeAmountExceptionZeroInputFloat() {
//        assertEquals(budgetTracker.expense.getExpenseTotal(), 0);
//        try {
//            budgetTracker.expense.addExpense(0);
//        } catch (NegativeAmountException e) {
//            fail("NegativeAmountException thrown for zero");
//        }
//        assertEquals(budgetTracker.expense.getExpenseTotal(), 0);
//    }
//
//    @Test
//    public void testAddExpenseNegativeAmountExceptionNegativeInputFloat() {
//        assertEquals(budgetTracker.expense.getExpenseTotal(), 0);
//        try {
//            budgetTracker.expense.addExpense(-1);
//            fail("No exception was thrown");
//        } catch (NegativeAmountException e) {
//            //do nothing
//        }
//        assertEquals(budgetTracker.expense.getExpenseTotal(), 0);
//    }
//
//    @Test
//    public void testCategorizeExpenseValidEntry() {
//        String input = "1";
//        InputStream is = new ByteArrayInputStream(input.getBytes());
//        budgetTracker.entry = new Scanner(is);
//        try {
//            String category = budgetTracker.categorizeExpense();
//            assertEquals(category, "Food");
//        } catch (InvalidEntryException e) {
//            fail("NegativeAmountException thrown for non-negative");
//        }
//    }
//
//    @Test
//    public void testCategorizeExpenseInvalidNumberEntry() {
//        String input = "1.0";
//        InputStream is = new ByteArrayInputStream(input.getBytes());
//        budgetTracker.entry = new Scanner(is);
//        try {
//            String category = budgetTracker.categorizeExpense();
//            assertEquals(category, null);
//            fail("No exception was thrown");
//        } catch (InvalidEntryException e) {
//            //do nothing
//        }
//    }
//
//    @Test
//    public void testCategorizeExpenseInvalidAlphaEntry() {
//        String input = "test";
//        InputStream is = new ByteArrayInputStream(input.getBytes());
//        budgetTracker.entry = new Scanner(is);
//        try {
//            String category = budgetTracker.categorizeExpense();
//            assertEquals(category, null);
//            fail("No exception was thrown");
//        } catch (InvalidEntryException e) {
//            //do nothing
//        }
//    }
//}
//
//
////Scanner test based on https://www.cs.utexas.edu/users/ndale/Scanner.html and
//// https://stackoverflow.com/questions/247161/how-do-i-turn-a-string-into-a-inputstreamreader-in-java