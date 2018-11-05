package reports;

import model.Expense;
import ui.BudgetTracker;

public class ExpenseReport extends Report {
    public static Expense expense = BudgetTracker.expense;
    private Expense food = expense.food;
    private Expense entertainment = expense.entertainment;
    private Expense health = expense.health;
    private Expense transportation = expense.transportation;
    private Expense rent = expense.rent;
    private Expense other = expense.other;

    // EFFECTS: Constructs expense report
    protected ExpenseReport() {
        super("EXPENSE");
        balance = super.expense;
    }


    // EFFECTS: Displays list of expenses; if no expenses, shows nothing
    public void getExpenseList() {
        System.out.println("EXPENSE LIST:");
        for (Integer i = 0; i < expense.getExpenseItems().size(); i++) {
            System.out.printf(expense.getExpenseItems().get(i).getExpenseItemCategory() + " "
                    + expense.getExpenseItems().get(i).getExpenseItemName()
                    + " $%.2f" + "%n", expense.getExpenseItems().get(i).getExpenseItemCost());
        }
    }

    // EFFECTS: Displays subtotals of each expense category
    public void getExpenseBreakdown() {
        System.out.println("EXPENSE CATEGORIES:");
        getReport(food.getExpenseCategoryName(), food.getExpenseAmount());
        getReport(entertainment.getExpenseCategoryName(), entertainment.getExpenseAmount());
        getReport(health.getExpenseCategoryName(), health.getExpenseAmount());
        getReport(transportation.getExpenseCategoryName(), transportation.getExpenseAmount());
        getReport(rent.getExpenseCategoryName(), rent.getExpenseAmount());
        getReport(other.getExpenseCategoryName(), other.getExpenseAmount());
    }

    @Override
    // EFFECTS: prints and returns report name and total expense
    public void getReport(String reportName, float expenseTotal) {
        String report = reportName + ": $" + String.format("%.2f", expenseTotal);
        System.out.println(report);
    }

    //EFFECTS: calculates and prints percentage of money spent in each expense category
    public void calculateAllCategoryPercent() {
        float foodPercent = calculateOneCategoryPercent(food.getExpenseAmount());
        float entertainmentPercent = calculateOneCategoryPercent(entertainment.getExpenseAmount());
        float healthPercent = calculateOneCategoryPercent(health.getExpenseAmount());
        float transportPercent = calculateOneCategoryPercent(transportation.getExpenseAmount());
        float rentPercent = calculateOneCategoryPercent(rent.getExpenseAmount());
        float otherPercent = calculateOneCategoryPercent(other.getExpenseAmount());
        System.out.println("*********************************");
        System.out.println("PERCENT BREAKDOWN:");
        System.out.printf(food.getExpenseCategoryName() + ": %.2f %% %n", foodPercent);
        System.out.printf(entertainment.getExpenseCategoryName() + ": %.2f %% %n", entertainmentPercent);
        System.out.printf(health.getExpenseCategoryName() + ": %.2f %% %n", healthPercent);
        System.out.printf(transportation.getExpenseCategoryName() + ": %.2f %% %n", transportPercent);
        System.out.printf(rent.getExpenseCategoryName() + ": %.2f %% %n", rentPercent);
        System.out.printf(other.getExpenseCategoryName() + ": %.2f %% %n", otherPercent);
    }

    // EFFECTS: returns percentage of cost to total expense
    private float calculateOneCategoryPercent(float cost) {
        return cost / super.expense * 100;
    }

}

//Pie chart from https://stackoverflow.com/questions/13662984/creating-pie-charts-programmatically
