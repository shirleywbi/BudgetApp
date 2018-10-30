package reports;

import exceptions.SavingsBeingUsedException;

public class ReportPrinter{

    // EFFECTS: Displays total subtotal, total income, total expenses, and list of expenses
    public void runReports() {
        Report incomeReport = new IncomeReport();
        ExpenseReport expenseReport = new ExpenseReport();
        Report balanceReport = new BalanceReport();

        System.out.printf("*********************************%n");
        try {
            float balanceAmount = balanceReport.getBalance();
            balanceReport.getReport(balanceReport.getReportName(), balanceAmount);
        } catch (SavingsBeingUsedException e) {
            System.out.println("WARNING: Your expenses have exceeded your income.");
        } finally {
            float incomeAmount = incomeReport.getBalance();
            incomeReport.getReport(incomeReport.getReportName(), incomeAmount);
            float expenseAmount = expenseReport.expense.getExpenseTotal();
            expenseReport.getReport(expenseReport.getReportName(), expenseAmount);
            expenseReport.getExpenseList();
            expenseReport.getExpenseBreakdown();
            expenseReport.expensePercentile();
            System.out.println("*********************************");
        }
    }
}



