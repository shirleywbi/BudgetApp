//package test;
//
//import exceptions.NegativeAmountException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ui.Operations;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class OperationsTest {
//    Operations testOp;
//
//    @BeforeEach
//    public void runBefore() {
//        testOp = new Operations();
//    }
//
//    @Test
//    public void testLoad() throws IOException, NegativeAmountException {
//        float income = 1200;
//        float expense = 1000;
//        testOp.load("testinput.txt");
//        assertEquals(testOp.getIncomeTotal().getIncomeTotal(),income);
//        assertEquals(testOp.getExpenseTotal().getExpenseTotal(), expense);
//        assertEquals(testOp.getExpenseTotal().getExpenseCategoryList().get(0), "Food");
//        assertEquals(testOp.getExpenseTotal().getExpenseNameList().get(0),"Apple");
//        assertEquals((Float) testOp.getExpenseTotal().getExpenseCostList().get(0),10, 0.01);
//        assertEquals(testOp.getExpenseTotal().getExpenseCategoryList().get(1), "Transportation");
//        assertEquals(testOp.getExpenseTotal().getExpenseNameList().get(1),"BusPass");
//        assertEquals((Float) testOp.getExpenseTotal().getExpenseCostList().get(1),93, 0.01);
//        assertEquals(testOp.getExpenseTotal().getExpenseCategoryList().get(2), "Rent");
//        assertEquals(testOp.getExpenseTotal().getExpenseNameList().get(2),"Rent");
//        assertEquals((Float) testOp.getExpenseTotal().getExpenseCostList().get(2),800, 0.01);
//        assertEquals(testOp.getExpenseTotal().getExpenseCategoryList().get(3), "Other");
//        assertEquals(testOp.getExpenseTotal().getExpenseNameList().get(3),"Pikachu");
//        assertEquals((Float) testOp.getExpenseTotal().getExpenseCostList().get(3),97, 0.01);
//        assertEquals(testOp.getExpenseTotal().getExpenseCategoryList().size(),4);
//    }
//
//    //TODO: FIX BUG - For some reason, test is not clearing, program works fine because no further action after save
//    @Test
//    public void testSaveChanges() throws IOException, NegativeAmountException{
//        assertEquals(testOp.getExpenseTotal().getExpenseCategoryList().size(),4);
////        testOp.load("testinput.txt");
//        testOp.getIncomeTotal().addIncome(501);
//        testOp.getExpenseTotal().addExpense(351);
//        testOp.getExpenseTotal().addExpenseItem("Balloon",3,"Other");
//        testOp.save("testoutput.txt");
//        testOp.load("testoutput.txt");
//        assertEquals(testOp.getIncomeTotal().getIncomeTotal(),1200 + 501);
//        assertEquals(testOp.getExpenseTotal().getExpenseTotal(), 1000 + 351);
//        assertEquals(testOp.getExpenseTotal().getExpenseCategoryList().get(0), "Food");
//        assertEquals(testOp.getExpenseTotal().getExpenseNameList().get(0),"Apple");
//        assertEquals((Float) testOp.getExpenseTotal().getExpenseCostList().get(0),10, 0.01);
//        assertEquals(testOp.getExpenseTotal().getExpenseCategoryList().get(1), "Transportation");
//        assertEquals(testOp.getExpenseTotal().getExpenseNameList().get(1),"BusPass");
//        assertEquals((Float) testOp.getExpenseTotal().getExpenseCostList().get(1),93, 0.01);
//        assertEquals(testOp.getExpenseTotal().getExpenseCategoryList().get(2), "Rent");
//        assertEquals(testOp.getExpenseTotal().getExpenseNameList().get(2),"Rent");
//        assertEquals((Float) testOp.getExpenseTotal().getExpenseCostList().get(2),800, 0.01);
//        assertEquals(testOp.getExpenseTotal().getExpenseCategoryList().get(3), "Other");
//        assertEquals(testOp.getExpenseTotal().getExpenseNameList().get(3),"Pikachu");
//        assertEquals((Float) testOp.getExpenseTotal().getExpenseCostList().get(3),97, 0.01);
////        assertEquals(testOp.getExpenseTotal().getExpenseCategoryList().size(),5);
//        assertEquals(testOp.getExpenseTotal().getExpenseCategoryList().get(4), "Other");
//        assertEquals(testOp.getExpenseTotal().getExpenseNameList().get(4),"Balloon");
//        assertEquals((Float) testOp.getExpenseTotal().getExpenseCostList().get(4),3, 0.01);
//
//    }
//
//}
