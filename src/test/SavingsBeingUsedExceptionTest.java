//package test;
//
//import exceptions.SavingsBeingUsedException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.fail;
//
//public class SavingsBeingUsedExceptionTest {
//    BalanceReport balanceReport;
//
//    @BeforeEach
//    public void setup() {
//        balanceReport = new BalanceReport();
//    }
//
//    @Test
//    public void testSavingsBeingUsedMoreIncomeThanExpense() {
//        balanceReport.income = 500;
//        balanceReport.expense = 300;
//        assertEquals(balanceReport.income, 500);
//        assertEquals(balanceReport.expense, 300);
//        try {
//            balanceReport.getReport("Balance", 200);
//            assertTrue(balanceReport.getBalance() == 200);
//        } catch (SavingsBeingUsedException e) {
//            fail("SavingsBeingUsedException thrown when not supposed to");
//        }
//    }
//
//    @Test
//    public void testSavingsBeingUsedEqualIncomeAndExpense() {
//        balanceReport.income = 500;
//        balanceReport.expense = 500;
//        assertEquals(balanceReport.income, 500);
//        assertEquals(balanceReport.expense, 500);
//        try {
//            balanceReport.getReport("Balance", 0);
//            assertTrue(balanceReport.getBalance() == 0);
//        } catch (SavingsBeingUsedException e) {
//            fail("SavingsBeingUsedException thrown when not supposed to");
//        }
//    }
//
//    @Test
//    public void testSavingsBeingUsedLessIncomeThanExpense() {
//        balanceReport.income = 500;
//        balanceReport.expense = 1000;
//        assertEquals(balanceReport.income, 500);
//        assertEquals(balanceReport.expense, 1000);
//        try {
//            balanceReport.getReport("Balance", -500);
//            assertTrue(balanceReport.getBalance() == -500);
//            fail("No exceptions was thrown");
//        } catch (SavingsBeingUsedException e) {
//            //do nothing
//        }
//    }
//}
//
