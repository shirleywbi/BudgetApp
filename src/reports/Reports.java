package reports;

import balance.Expenses;
import balance.MoneyCount;

public class Reports {

    public static void getSummaryReport() {
        System.out.println("*********************************");
        System.out.println("Report:");
        getIncomeReport();
        getExpenseReport();
        Expenses.getExpenseList();
        getBalance();
        System.out.println("*********************************");
    }

    private static void getIncomeReport() {
        System.out.printf("You have earned $%.2f" + " so far. %n",MoneyCount.getIncome());
    }

    private static void getExpenseReport() {
        System.out.printf("You have spent $%.2f" + " so far. %n",MoneyCount.getExpenses());
    }

    private static void getBalance() {
        System.out.printf("Your current balance is $%.2f" + ". %n",MoneyCount.getBalance());
    }
}

