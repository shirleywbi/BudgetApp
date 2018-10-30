//package test;
//
//import exceptions.NegativeAmountException;
//import model.expenses.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class ExpenseTest {
//    private Expense testFoodExpense;
//    private Expense testRentExpense;
//    private Expense testTransportationExpense;
//    private Expense testEntertainmentExpense;
//    private Expense testHealthExpense;
//    private Expense testOtherExpense;
//    private Expense testExpense;
//
//    @BeforeEach
//    public void runBefore() {
//        testExpense = new Expense();
//        testFoodExpense = new Food("Food", 0);
//        testRentExpense = new Rent("Rent", 0);
//        testTransportationExpense = new Transportation("Transportation", 0);
//        testEntertainmentExpense = new Entertainment("Entertainment", 0);
//        testHealthExpense = new Health("Health", 0);
//        testOtherExpense = new Other("Other", 0);
//    }
//
//    @Test
//    public void testInitialBalances() {
//        assertEquals(testExpense.getExpenseTotal(), 0);
//        assertEquals(testFoodExpense.getExpenseTotal(), 0);
//        assertEquals(testRentExpense.getExpenseTotal(), 0);
//        assertEquals(testTransportationExpense.getExpenseTotal(), 0);
//        assertEquals(testEntertainmentExpense.getExpenseTotal(), 0);
//        assertEquals(testHealthExpense.getExpenseTotal(), 0);
//        assertEquals(testOtherExpense.getExpenseTotal(), 0);
//    }
//
//    //Tests for Expense
//    @Test
//    public void testAddExpenseZero() throws NegativeAmountException {
//        testExpense.addExpense(0);
//        assertTrue(testExpense.getExpenseTotal() == 0);
//    }
//
//    @Test
//    public void testAddExpenseOne() throws NegativeAmountException {
//        testExpense.addExpense(1);
//        assertTrue(testExpense.getExpenseTotal() == 1);
//    }
//
//    @Test
//    public void testAddExpenseMore() throws NegativeAmountException {
//        testExpense.addExpense(1);
//        assertTrue(testExpense.getExpenseTotal() == 1);
//        testExpense.addExpense(50);
//        assertTrue(testExpense.getExpenseTotal() == 50 + 1);
//    }
//
//    //Tests for Food
//    @Test
//    public void testAddFoodExpenseZero() throws NegativeAmountException {
//        testFoodExpense.addExpense(0);
//        assertTrue(testFoodExpense.getExpenseTotal() == 0);
//    }
//
//    @Test
//    public void testAddFoodExpenseOne() throws NegativeAmountException {
//        testFoodExpense.addExpense(1);
//        assertTrue(testFoodExpense.getExpenseTotal() == 1);
//    }
//
//    @Test
//    public void testAddFoodExpenseMore() throws NegativeAmountException {
//        testFoodExpense.addExpense(1);
//        assertTrue(testFoodExpense.getExpenseTotal() == 1);
//        testFoodExpense.addExpense(50);
//        assertTrue(testFoodExpense.getExpenseTotal() == 50 + 1);
//    }
//
//    @Test
//    public void testAddFoodExpenseToLists() {
//        testExpense.addExpenseItem("Pie", 5, "Food");
//        assertTrue(testExpense.getExpenseNameList().get(0).equals("Pie"));
//        assertTrue(testExpense.getExpenseCostList().get(0).floatValue() == 5);
//        assertTrue(testExpense.getExpenseCategoryList().get(0).equals("Food"));
//    }
//
//    @Test
//    public void testAddFoodExpenseToListsMore() {
//        testExpense.addExpenseItem("Pie", 5, "Food");
//        assertTrue(testExpense.getExpenseNameList().get(0).equals("Pie"));
//        assertTrue(testExpense.getExpenseCostList().get(0).floatValue() == 5);
//        assertTrue(testExpense.getExpenseCategoryList().get(0).equals("Food"));
//        testExpense.addExpenseItem("Apple", 3, "Other");
//        assertTrue(testExpense.getExpenseNameList().get(1).equals("Apple"));
//        assertTrue(testExpense.getExpenseCostList().get(1).floatValue() == 3);
//        assertTrue(testExpense.getExpenseCategoryList().get(1).equals("Other"));
//    }
//
//    @Test
//    public void testSortSubExpenseFood() throws NegativeAmountException {
//        testExpense.sortSubExpense("Food", 30);
//        assertEquals(testExpense.food.getExpenseTotal(), 30);
//    }
//
//
//    //Tests for Rent
//    @Test
//    public void testAddRentExpenseZero() throws NegativeAmountException {
//        testRentExpense.addExpense(0);
//        assertTrue(testRentExpense.getExpenseTotal() == 0);
//    }
//
//    @Test
//    public void testAddRentExpenseOne() throws NegativeAmountException {
//        testRentExpense.addExpense(1);
//        assertTrue(testRentExpense.getExpenseTotal() == 1);
//    }
//
//    @Test
//    public void testAddRentExpenseMore() throws NegativeAmountException {
//        testRentExpense.addExpense(1);
//        assertTrue(testRentExpense.getExpenseTotal() == 1);
//        testRentExpense.addExpense(50);
//        assertTrue(testRentExpense.getExpenseTotal() == 50 + 1);
//    }
//
//    @Test
//    public void testAddRentExpenseToLists() {
//        testExpense.addExpenseItem("Test", 5, "Rent");
//        assertTrue(testExpense.getExpenseNameList().get(0).equals("Test"));
//        assertTrue(testExpense.getExpenseCostList().get(0).floatValue() == 5);
//        assertTrue(testExpense.getExpenseCategoryList().get(0).equals("Rent"));
//    }
//
//    @Test
//    public void testAddRentExpenseToListsMore() {
//        testExpense.addExpenseItem("Test", 5, "Rent");
//        assertTrue(testExpense.getExpenseNameList().get(0).equals("Test"));
//        assertTrue(testExpense.getExpenseCostList().get(0).floatValue() == 5);
//        assertTrue(testExpense.getExpenseCategoryList().get(0).equals("Rent"));
//        testExpense.addExpenseItem("Apple", 3, "Other");
//        assertTrue(testExpense.getExpenseNameList().get(1).equals("Apple"));
//        assertTrue(testExpense.getExpenseCostList().get(1).floatValue() == 3);
//        assertTrue(testExpense.getExpenseCategoryList().get(1).equals("Other"));
//    }
//
//    @Test
//    public void testSortSubExpenseRent() throws NegativeAmountException {
//        testExpense.sortSubExpense("Rent", 30);
//        assertEquals(testExpense.rent.getExpenseTotal(), 30);
//    }
//
//    //Tests for Transportation
//    @Test
//    public void testAddTransportationExpenseZero() throws NegativeAmountException {
//        testTransportationExpense.addExpense(0);
//        assertTrue(testTransportationExpense.getExpenseTotal() == 0);
//    }
//
//    @Test
//    public void testAddTransportationExpenseOne() throws NegativeAmountException {
//        testTransportationExpense.addExpense(1);
//        assertTrue(testTransportationExpense.getExpenseTotal() == 1);
//    }
//
//    @Test
//    public void testAddTransportationExpenseMore() throws NegativeAmountException {
//        testTransportationExpense.addExpense(1);
//        assertTrue(testTransportationExpense.getExpenseTotal() == 1);
//        testTransportationExpense.addExpense(50);
//        assertTrue(testTransportationExpense.getExpenseTotal() == 50 + 1);
//    }
//
//    @Test
//    public void testAddTransportationExpenseToLists() {
//        testExpense.addExpenseItem("Car", 5, "Transportation");
//        assertTrue(testExpense.getExpenseNameList().get(0).equals("Car"));
//        assertTrue(testExpense.getExpenseCostList().get(0).floatValue() == 5);
//        assertTrue(testExpense.getExpenseCategoryList().get(0).equals("Transportation"));
//    }
//
//    @Test
//    public void testAddTransportationExpenseToListsMore() {
//        testExpense.addExpenseItem("Car", 5, "Transportation");
//        assertTrue(testExpense.getExpenseNameList().get(0).equals("Car"));
//        assertTrue(testExpense.getExpenseCostList().get(0).floatValue() == 5);
//        assertTrue(testExpense.getExpenseCategoryList().get(0).equals("Transportation"));
//        testExpense.addExpenseItem("Apple", 3, "Other");
//        assertTrue(testExpense.getExpenseNameList().get(1).equals("Apple"));
//        assertTrue(testExpense.getExpenseCostList().get(1).floatValue() == 3);
//        assertTrue(testExpense.getExpenseCategoryList().get(1).equals("Other"));
//    }
//
//    @Test
//    public void testSortSubExpenseTransportation() throws NegativeAmountException {
//        testExpense.sortSubExpense("Transportation", 30);
//        assertEquals(testExpense.transportation.getExpenseTotal(), 30);
//    }
//
//    //Tests for Entertainment
//    @Test
//    public void testAddEntertainmentExpenseZero() throws NegativeAmountException {
//        testEntertainmentExpense.addExpense(0);
//        assertTrue(testEntertainmentExpense.getExpenseTotal() == 0);
//    }
//
//    @Test
//    public void testAddEntertainmentExpenseOne() throws NegativeAmountException {
//        testEntertainmentExpense.addExpense(1);
//        assertTrue(testEntertainmentExpense.getExpenseTotal() == 1);
//    }
//
//    @Test
//    public void testAddEntertainmentExpenseMore() throws NegativeAmountException {
//        testEntertainmentExpense.addExpense(1);
//        assertTrue(testEntertainmentExpense.getExpenseTotal() == 1);
//        testEntertainmentExpense.addExpense(50);
//        assertTrue(testEntertainmentExpense.getExpenseTotal() == 50 + 1);
//    }
//
//    @Test
//    public void testAddEntertainmentExpenseToLists() {
//        testExpense.addExpenseItem("Pie", 5, "Entertainment");
//        assertTrue(testExpense.getExpenseNameList().get(0).equals("Pie"));
//        assertTrue(testExpense.getExpenseCostList().get(0).floatValue() == 5);
//        assertTrue(testExpense.getExpenseCategoryList().get(0).equals("Entertainment"));
//    }
//
//    @Test
//    public void testAddEntertainmentExpenseToListsMore() {
//        testExpense.addExpenseItem("Pie", 5, "Entertainment");
//        assertTrue(testExpense.getExpenseNameList().get(0).equals("Pie"));
//        assertTrue(testExpense.getExpenseCostList().get(0).floatValue() == 5);
//        assertTrue(testExpense.getExpenseCategoryList().get(0).equals("Entertainment"));
//        testExpense.addExpenseItem("Apple", 3, "Other");
//        assertTrue(testExpense.getExpenseNameList().get(1).equals("Apple"));
//        assertTrue(testExpense.getExpenseCostList().get(1).floatValue() == 3);
//        assertTrue(testExpense.getExpenseCategoryList().get(1).equals("Other"));
//    }
//
//    @Test
//    public void testSortSubExpenseEntertainment() throws NegativeAmountException {
//        testExpense.sortSubExpense("Entertainment", 30);
//        assertEquals(testExpense.entertainment.getExpenseTotal(), 30);
//    }
//
//    //Tests for Health
//    @Test
//    public void testAddHealthExpenseZero() throws NegativeAmountException {
//        testHealthExpense.addExpense(0);
//        assertTrue(testHealthExpense.getExpenseTotal() == 0);
//    }
//
//    @Test
//    public void testAddHealthExpenseOne() throws NegativeAmountException {
//        testHealthExpense.addExpense(1);
//        assertTrue(testHealthExpense.getExpenseTotal() == 1);
//    }
//
//    @Test
//    public void testAddHealthExpenseMore() throws NegativeAmountException {
//        testHealthExpense.addExpense(1);
//        assertTrue(testHealthExpense.getExpenseTotal() == 1);
//        testHealthExpense.addExpense(50);
//        assertTrue(testHealthExpense.getExpenseTotal() == 50 + 1);
//    }
//
//    @Test
//    public void testAddHealthExpenseToLists() {
//        testExpense.addExpenseItem("Pie", 5, "Health");
//        assertTrue(testExpense.getExpenseNameList().get(0).equals("Pie"));
//        assertTrue(testExpense.getExpenseCostList().get(0).floatValue() == 5);
//        assertTrue(testExpense.getExpenseCategoryList().get(0).equals("Health"));
//    }
//
//    @Test
//    public void testAddHealthExpenseToListsMore() {
//        testExpense.addExpenseItem("Pie", 5, "Health");
//        assertTrue(testExpense.getExpenseNameList().get(0).equals("Pie"));
//        assertTrue(testExpense.getExpenseCostList().get(0).floatValue() == 5);
//        assertTrue(testExpense.getExpenseCategoryList().get(0).equals("Health"));
//        testExpense.addExpenseItem("Apple", 3, "Other");
//        assertTrue(testExpense.getExpenseNameList().get(1).equals("Apple"));
//        assertTrue(testExpense.getExpenseCostList().get(1).floatValue() == 3);
//        assertTrue(testExpense.getExpenseCategoryList().get(1).equals("Other"));
//    }
//
//    @Test
//    public void testSortSubExpenseHealth() throws NegativeAmountException {
//        testExpense.sortSubExpense("Health", 30);
//        assertEquals(testExpense.health.getExpenseTotal(), 30);
//    }
//
//    //Tests for Other
//    @Test
//    public void testAddOtherExpenseZero() throws NegativeAmountException {
//        testOtherExpense.addExpense(0);
//        assertTrue(testOtherExpense.getExpenseTotal() == 0);
//    }
//
//    @Test
//    public void testAddOtherExpenseOne() throws NegativeAmountException {
//        testOtherExpense.addExpense(1);
//        assertTrue(testOtherExpense.getExpenseTotal() == 1);
//    }
//
//    @Test
//    public void testAddOtherExpenseMore() throws NegativeAmountException {
//        testOtherExpense.addExpense(1);
//        assertTrue(testOtherExpense.getExpenseTotal() == 1);
//        testOtherExpense.addExpense(50);
//        assertTrue(testOtherExpense.getExpenseTotal() == 50 + 1);
//    }
//
//    @Test
//    public void testAddOtherExpenseToLists() {
//        testExpense.addExpenseItem("Pie", 5, "Other");
//        assertTrue(testExpense.getExpenseNameList().get(0).equals("Pie"));
//        assertTrue(testExpense.getExpenseCostList().get(0).floatValue() == 5);
//        assertTrue(testExpense.getExpenseCategoryList().get(0).equals("Other"));
//    }
//
//    @Test
//    public void testAddOtherExpenseToListsMore() {
//        testExpense.addExpenseItem("Pie", 5, "Other");
//        assertTrue(testExpense.getExpenseNameList().get(0).equals("Pie"));
//        assertTrue(testExpense.getExpenseCostList().get(0).floatValue() == 5);
//        assertTrue(testExpense.getExpenseCategoryList().get(0).equals("Other"));
//        testExpense.addExpenseItem("Apple", 3, "Other");
//        assertTrue(testExpense.getExpenseNameList().get(1).equals("Apple"));
//        assertTrue(testExpense.getExpenseCostList().get(1).floatValue() == 3);
//        assertTrue(testExpense.getExpenseCategoryList().get(1).equals("Other"));
//    }
//
//    @Test
//    public void testSortSubExpenseOther() throws NegativeAmountException {
//        testExpense.sortSubExpense("Other", 30);
//        assertEquals(testExpense.other.getExpenseTotal(), 30);
//    }
//}