package ui;

import model.Balances;
import model.Reports;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BudgetTracker {
    private Balances balances = Balances.bal;
    private Scanner entry = new Scanner(System.in);

    // REQUIRES: initial prompt entry between 1-4 (inclusive) and monetary values as numbers
    // MODIFIES: this, balances, reports
    // EFFECTS: prompts user to select one of 4 options:
    //              - if 1 is entered, requests for user's income and adds income to total income
    //              - if 2 is entered, requests for user's expense and add expense to total expense,
    //                stores and displays expense name and cost
    //              - if 3 is entered, show report of total income, total expense, expense list of names and costs,
    //                and balance
    //              - if 4 is entered, exit
    public BudgetTracker() throws IOException {
        Reports reports = Reports.reports;
        while (true) {
            System.out.printf("What would you like to do? %n" +
                    "[1] Add income %n" +
                    "[2] Add expense %n" +
                    "[3] Show report %n" +
                    "[4] Exit %n");
            String option = entry.nextLine();
            if (option.equals("1")) {
                logIncome();
            } else if (option.equals("2")) {
                logExpense();
            } else if (option.equals("3")) {
                reports.getSummaryReport();
            } else if (option.equals("4")) {
                System.out.println("Saving...");
                save();
                System.out.println("Saved.");
                break;
            }
        }
    }

    // MODIFIES: this, balances
    // EFFECTS: prompts and adds user inputted income to total income
    private void logIncome() {
        System.out.println("Enter your income:");
        balances.addIncome(Float.valueOf(entry.nextLine()));
    }

    // MODIFIES: this, balances
    // EFFECTS: prompts and saves user input for expense name and cost, and displays it back to the user
    private void logExpense() {
        System.out.println("Enter name of expense:");
        String expenseNameEntry = entry.nextLine();
        System.out.println("Enter the cost:");
        float expenseCostEntry = Float.valueOf(entry.nextLine());
        balances.addExpense(expenseCostEntry);
        balances.addExpenseEntry(expenseNameEntry, expenseCostEntry);
        System.out.printf("Item: " + expenseNameEntry + "%nCost: $ %.2f %n",expenseCostEntry);
    }

    //EFFECTS: saves total income, total expenses, list of expenses and prints out what is saved
    public void save() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("budgetinput.txt"));
        PrintWriter writer = new PrintWriter("budgetoutput.txt", "UTF-8");
        for (Integer i = 0; i < balances.nameList.size(); i++) {
            lines.add(balances.nameList.get(i) + " " + balances.costList.get(i));}
        for (String line : lines) {
            ArrayList<String> partsOfBalance = splitOnSpace(line,": ");
            ArrayList<String> partsofExpense = splitOnSpace(line, " ");
            if (line.contains("Total Income:")) {
                float newIncome = Float.valueOf(partsOfBalance.get(1)) + balances.getIncome();
                line = "Total Income: " + newIncome;
            } else if (line.contains("Total Expenses:")) {
                float newExpense = Float.valueOf(partsOfBalance.get(1)) + balances.getExpenses();
                line = "Total Expenses: " + newExpense;
            } else if (line.contains("List of Expenses:")) {
                line = "List of Expenses:";
            } else {
                if (!line.isEmpty())
                    line = partsofExpense.get(0) + " " + partsofExpense.get(1);
            }
            System.out.println(line);
            writer.println(line);
        }
        writer.close();
    }

    //EFFECTS: splits data
    private static ArrayList<String> splitOnSpace(String line, String splitter) {
        String[] splits = line.split(splitter);
        return new ArrayList<>(Arrays.asList(splits));
    }



}


//overall format of BudgetTracker referenced from LoggingCalculator
//converting numbers to string referenced from https://docs.oracle.com/javase/tutorial/java/data/converting.html
//save() and splitOnSpace() methods referenced from FileReaderWriter