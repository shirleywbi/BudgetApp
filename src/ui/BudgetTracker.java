package ui;

import balance.Expenses;
import balance.MoneyCount;
import reports.Reports;

import java.util.Scanner;

public class BudgetTracker {

    private BudgetTracker() {
        Scanner entry = new Scanner(System.in);
        while (true) {
            System.out.printf("What would you like to do? %n" +
                    "[1] Add income %n" +
                    "[2] Add expense %n" +
                    "[3] Show report %n" +
                    "[4] Exit %n");
            String option = entry.nextLine();
            //[1] Add income - Requests for income and adds to total balance
            if (option.equals("1")) {
                System.out.println("Enter your income:");
                float incomeEntry = (Float.valueOf(entry.nextLine()));
                MoneyCount.addIncome(incomeEntry);
            //[2] Add expense - Requests for name and cost of expense, saves it, and prints it
            } else if (option.equals("2")) {
                System.out.println("Enter name of expense:");
                String expenseNameEntry = entry.nextLine();
                System.out.println("Enter the cost:");
                float expenseCostEntry = (Float.valueOf(entry.nextLine()));
                MoneyCount.addExpense(expenseCostEntry);
                Expenses.addExpenseEntry(expenseNameEntry, expenseCostEntry);
                System.out.printf("Item: " + expenseNameEntry + "%nCost: $ %.2f %n",expenseCostEntry);
            //[3] Show report - Returns total income, total expense, and current getBalance
            } else if (option.equals("3")) {
                Reports.getSummaryReport();
            //[4] Exit - Ends program
            } else if (option.equals("4")) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new BudgetTracker();

    }
}


//overall format of BudgetTracker referenced from LoggingCalculator
//line 24 referenced from https://docs.oracle.com/javase/tutorial/java/data/converting.html