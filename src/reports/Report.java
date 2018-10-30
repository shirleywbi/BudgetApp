package reports;

import ui.BudgetTracker;

// abstract class Report
public abstract class Report {
    protected String reportName;
    protected float balance;

    public float expense = BudgetTracker.expense.getExpenseTotal();
    public float income = BudgetTracker.income.getIncomeTotal();

    public Report(String reportName) {
        this.reportName = reportName;
    }

    // EFFECTS: Prints and returns the subtotal
    public String getReport(String reportName, float balance) {
        String report = reportName + ": $" + String.format("%.2f",balance);
        System.out.println(report);
        return report;
    }

    public String getReportName() {
        return reportName;
    }

    public float getBalance() {
        return balance;
    }

    public void printLine() {
        System.out.println("*********************************");
    }


}