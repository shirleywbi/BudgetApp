package reports;

import model.Balances;

public class ReportPrinter {
    public static ReportPrinter report = new ReportPrinter();

    // EFFECTS: Displays total subtotal, total income, total expenses, and list of expenses
    public void runReports() {
        IncomeReport incomeReport = new IncomeReport();
        ExpenseReport expenseReport = new ExpenseReport();
        BalanceReport balanceReport = new BalanceReport();

        System.out.printf("*********************************%n");
        balanceReport.getReport("Balance",Balances.bal.getBalance());
        incomeReport.getReport("Income",Balances.bal.getIncome());
        expenseReport.getReport("Expense",Balances.bal.getExpenses());
        expenseReport.getExpenseList();
        expenseReport.getExpenseBreakdown();
        expenseReport.expensePercentile();
        System.out.println("*********************************");
    }

}



