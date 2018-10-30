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
        printLine();
        System.out.println("EXPENSE LIST:");
        for (Integer i = 0; i < expense.expenseItems.size(); i++) {
            System.out.printf(expense.expenseItems.get(i).getExpenseItemCategory() + " "
                    + expense.expenseItems.get(i).getExpenseItemName()
                    + " $%.2f" + "%n", expense.expenseItems.get(i).getExpenseItemCost());
        }
    }

    // EFFECTS: Displays subtotals of each expense category
    public void getExpenseBreakdown() {
        printLine();
        System.out.println("EXPENSE CATEGORIES:");
        getReport(food.getExpenseCategory(), food.getExpenseTotal());
        getReport(entertainment.getExpenseCategory(), entertainment.getExpenseTotal());
        getReport(health.getExpenseCategory(), health.getExpenseTotal());
        getReport(transportation.getExpenseCategory(), transportation.getExpenseTotal());
        getReport(rent.getExpenseCategory(), rent.getExpenseTotal());
        getReport(other.getExpenseCategory(), other.getExpenseTotal());
    }

    @Override
    // EFFECTS: prints and returns report name and total expense
    public String getReport(String reportName, float expenseTotal) {
        String report = reportName + ": $" + String.format("%.2f", expenseTotal);
        System.out.println(report);
        return report;
    }

    //EFFECTS: calculates and prints percentage of money spent in each expense category
    public void expensePercentile() {
        float foodPercent = calculatePercent(food.getExpenseTotal());
        float entertainmentPercent = calculatePercent(entertainment.getExpenseTotal());
        float healthPercent = calculatePercent(health.getExpenseTotal());
        float transportPercent = calculatePercent(transportation.getExpenseTotal());
        float rentPercent = calculatePercent(rent.getExpenseTotal());
        float otherPercent = calculatePercent(other.getExpenseTotal());
        System.out.println("*********************************");
        System.out.println("PERCENT BREAKDOWN:");
        System.out.printf(food.getExpenseCategory() + ": %.2f %% %n", foodPercent);
        System.out.printf(entertainment.getExpenseCategory() + ": %.2f %% %n", entertainmentPercent);
        System.out.printf(health.getExpenseCategory() + ": %.2f %% %n", healthPercent);
        System.out.printf(transportation.getExpenseCategory() + ": %.2f %% %n", transportPercent);
        System.out.printf(rent.getExpenseCategory() + ": %.2f %% %n", rentPercent);
        System.out.printf(other.getExpenseCategory() + ": %.2f %% %n", otherPercent);
    }

    // EFFECTS: returns percentage of cost to total expense
    private float calculatePercent(float cost) {
        return cost / super.expense * 100;
    }

}

//Pie chart from https://stackoverflow.com/questions/13662984/creating-pie-charts-programmatically
