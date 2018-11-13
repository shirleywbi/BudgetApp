package reports;

import model.Expense;
import observer.ExpenseObserver;
import ui.BudgetTracker;

public class ExpenseReport extends Report implements ExpenseObserver{
    public static Expense expense = BudgetTracker.expense;

    // EFFECTS: Constructs expense report
    public ExpenseReport() {
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
        getReport(expense.getFood().getExpenseCategoryName(), expense.getFood().getExpenseAmount());
        getReport(expense.getEntertainment().getExpenseCategoryName(), expense.getEntertainment().getExpenseAmount());
        getReport(expense.getHealth().getExpenseCategoryName(), expense.getHealth().getExpenseAmount());
        getReport(expense.getTransportation().getExpenseCategoryName(), expense.getTransportation().getExpenseAmount());
        getReport(expense.getRent().getExpenseCategoryName(), expense.getRent().getExpenseAmount());
        getReport(expense.getOther().getExpenseCategoryName(), expense.getOther().getExpenseAmount());
    }

    @Override
    // EFFECTS: prints and returns report name and total expense
    public void getReport(String reportName, float expenseTotal) {
        String report = reportName + ": $" + String.format("%.2f", expenseTotal);
        System.out.println(report);
    }

    //EFFECTS: calculates and prints percentage of money spent in each expense category
    public void calculateAllCategoryPercent() {
        float foodPercent = calculateOneCategoryPercent(expense.getFood().getExpenseAmount());
        float entertainmentPercent = calculateOneCategoryPercent(expense.getEntertainment().getExpenseAmount());
        float healthPercent = calculateOneCategoryPercent(expense.getHealth().getExpenseAmount());
        float transportPercent = calculateOneCategoryPercent(expense.getTransportation().getExpenseAmount());
        float rentPercent = calculateOneCategoryPercent(expense.getRent().getExpenseAmount());
        float otherPercent = calculateOneCategoryPercent(expense.getOther().getExpenseAmount());
        System.out.println("PERCENT BREAKDOWN:");
        System.out.printf(expense.getFood().getExpenseCategoryName() + ": %.2f %% %n", foodPercent);
        System.out.printf(expense.getEntertainment().getExpenseCategoryName() + ": %.2f %% %n", entertainmentPercent);
        System.out.printf(expense.getHealth().getExpenseCategoryName() + ": %.2f %% %n", healthPercent);
        System.out.printf(expense.getTransportation().getExpenseCategoryName() + ": %.2f %% %n", transportPercent);
        System.out.printf(expense.getRent().getExpenseCategoryName() + ": %.2f %% %n", rentPercent);
        System.out.printf(expense.getOther().getExpenseCategoryName() + ": %.2f %% %n", otherPercent);
    }

    // EFFECTS: returns percentage of cost to total expense
    private float calculateOneCategoryPercent(float cost) {
        return cost / super.expense * 100;
    }

    @Override
    public void update(ExpenseObserver expenseObserver) {
        System.out.println("Expenses have been updated. New report can be generated.");
    }
}

//Pie chart from https://stackoverflow.com/questions/13662984/creating-pie-charts-programmatically
