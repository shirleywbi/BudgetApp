package reports;

import exceptions.SavingsBeingUsedException;
import observer.ExpenseObserver;

public class ReportPrinter implements ExpenseObserver{

    // EFFECTS: Displays total subtotal, total income, total expenses, and list of expenses
    public void runReports() {
        Report incomeReport = new IncomeReport();
        ExpenseReport expenseReport = new ExpenseReport();
        Report balanceReport = new BalanceReport();

        printLine();
        try {
            float balanceAmount = balanceReport.getBalance();
            balanceReport.getReport(balanceReport.getReportName(), balanceAmount);
        } catch (SavingsBeingUsedException e) {
            System.out.println("WARNING: Your expenses have exceeded your income.");
        } finally {
            float incomeAmount = incomeReport.getBalance();
            incomeReport.getReport(incomeReport.getReportName(), incomeAmount);
            float expenseAmount = expenseReport.expense.getExpenseAmount();
            expenseReport.getReport(expenseReport.getReportName(), expenseAmount);
            printLine();
            expenseReport.getExpenseList();
            printLine();
            expenseReport.getExpenseBreakdown();
            printLine();
            expenseReport.calculateAllCategoryPercent();
            printLine();
        }
    }

    private void printLine() {
        System.out.println("*********************************");
    }

    @Override
    public void update(ExpenseObserver expenseObserver) {
        System.out.println("Expenses have been updated. New report available.");
    }

}



