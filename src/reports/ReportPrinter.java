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
            float balanceAmount = balanceReport.balance;
            balanceReport.getReport("Balance", balanceAmount);
        } catch (SavingsBeingUsedException e) {
            System.out.println("WARNING: Your expenses have exceeded your income.");
        } finally {
            float incomeAmount = incomeReport.balance;
            incomeReport.getReport("Income", incomeAmount);
            float expenseAmount = expenseReport.expense;
            expenseReport.getReport("Expense", expenseAmount);
            expense.getExpenseList();
            expenseReport.getExpenseBreakdown();
            expenseReport.expensePercentile();
            System.out.println("*********************************");
        }
    }
}



