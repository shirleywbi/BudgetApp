package reports;

import ui.BudgetTracker;

// abstract class Report
public abstract class Report {
    protected String reportName;
    protected float balance;

    public float expense = BudgetTracker.expense.getExpense();
    public float income = BudgetTracker.income.getIncome();

    public Report(String reportName) {
        this.reportName = reportName;
    }

    // EFFECTS: Prints and returns the subtotal
    public String getReport(String reportName, float balance) {
        String report = reportName + ": $" + String.format("%.2f",balance);
        System.out.println(report);
        return report;
    }
}