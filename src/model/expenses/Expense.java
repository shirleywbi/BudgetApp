package model.expenses;

import exceptions.NegativeAmountException;

import java.util.*;

public class Expense {
    protected String expenseName;
    protected float expenseTotal;
    public List<String> expenseCategories = new ArrayList<>(Arrays.asList
        ("Food", "Entertainment", "Health", "Rent", "Transportation", "Other"));
    private List<ExpenseItem> expenses = new ArrayList<>();

    public static Expense food = new Food("Food",0);
    public static Expense entertainment = new Entertainment("Entertainment",0);
    public static Expense health = new Health("Health",0);
    public static Expense transportation = new Transportation("Transportation",0);
    public static Expense rent = new Rent("Rent",0);
    public static Expense other = new Other("Other",0);

    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<Float> costList = new ArrayList<>();
    private ArrayList<String> categoryList = new ArrayList<>();

    // EFFECTS: constructs expense with 0 expenses
    public Expense() {
        this.expenseTotal = 0;
    }

    // EFFECTS: constructs expense for subExpenses
    public Expense(String expenseName,float expenseTotal) {
        this.expenseName = expenseName;
        this.expenseTotal = expenseTotal;
    }

    // EFFECTS: prints and returns report name and total expense
    public String getReport(String reportName, float expenseTotal) {
        String report = "         " + reportName + ": $" + String.format("%.2f",expenseTotal);
        System.out.println(report);
        return report;
    }

    // EFFECTS: Returns the total expense
    public float getExpense() {
        return expenseTotal;
    }

    // EFFECTS: returns expense category name
    public String getExpenseName() {
        return expenseName;
    }

    // EFFECTS: Displays list of expenses; if no expenses, shows nothing
    public void getExpenseList() {
        for (Integer i = 0; i < nameList.size(); i++) {
            System.out.printf("         " + categoryList.get(i) + " "
                    + nameList.get(i) + " $%.2f" + "%n", costList.get(i));
        }
    }

    // EFFECTS: returns list of expense categories
    public ArrayList<String> getExpenseCategoryList() {
        return categoryList;
    }

    // EFFECTS: returns list of expense names
    public ArrayList<String> getExpenseNameList() {
        return nameList;
    }

    // EFFECTS: returns list of expense costs
    public ArrayList<Float> getExpenseCostList() {
        return costList;
    }

    // MODIFIES: this
    // EFFECTS: sets expense to given num
    public void setExpense(float num) {
        this.expenseTotal = num;
    }

    // REQUIRES: num >= 0
    // MODIFIES: this
    // EFFECTS: Adds num to the total expense
    public void addExpense(float num) throws NegativeAmountException {
        if (num < 0) {
            throw new NegativeAmountException();
        }
        expenseTotal += num;
    }

    // MODIFIES: this
    // EFFECTS: adds name of expense, its cost, and its type into three separate lists
    public void addExpenseItem(String name, float cost, String category) {
        nameList.add(name);
        costList.add(cost);
        categoryList.add(category);
    }

    // MODIFIES: this
    // EFFECTS: sorts expense into expense category and adds it to the category expenseTotal
    public float sortSubExpense(String category, float cost) throws NegativeAmountException{
        switch (category) {
            case "Food":
                food.addExpense(cost);
                return food.getExpense();
            case "Entertainment":
                entertainment.addExpense(cost);
                return entertainment.getExpense();
            case "Health":
                health.addExpense(cost);
                return health.getExpense();
            case "Rent":
                rent.addExpense(cost);
                return rent.getExpense();
            case "Transportation":
                transportation.addExpense(cost);
                return transportation.getExpense();
            default:
                other.addExpense(cost);
                return other.getExpense();
        }
    }
}
