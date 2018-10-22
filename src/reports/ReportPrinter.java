package reports;

import exceptions.SavingsBeingUsedException;

import static ui.BudgetTracker.expense;

public class ReportPrinter{

    // EFFECTS: Displays total subtotal, total income, total expenses, and list of expenses
    public void runReports() {
        Report incomeReport = new IncomeReport();
        ExpenseReport expenseReport = new ExpenseReport();
        Report balanceReport = new BalanceReport();

        System.out.printf("*********************************%n");
        try {
            balanceReport.getReport("Balance", balanceReport.balance);
        } catch (SavingsBeingUsedException e) {
            System.out.println("WARNING: Your expenses have exceeded your income.");
        } finally {
            incomeReport.getReport("Income", incomeReport.balance);
            expenseReport.getReport("Expense", expenseReport.balance);
            expense.getExpenseList();
            expenseReport.getExpenseBreakdown();
            expenseReport.expensePercentile();
            System.out.println("*********************************");
        }
    }
}



