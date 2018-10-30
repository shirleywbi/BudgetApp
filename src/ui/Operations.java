package ui;

import exceptions.NegativeAmountException;
import model.Income;
import model.Expense;
import model.ExpenseItem;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Operations {
    private Expense expense = BudgetTracker.expense;
    private Income income = BudgetTracker.income;
    private Expense food = Expense.food;
    private Expense entertainment = Expense.entertainment;
    private Expense health = Expense.health;
    private Expense transportation = Expense.transportation;
    private Expense rent = Expense.rent;
    private Expense other = Expense.other;

    // REQUIRES: .txt file in the form of:
    //           Total Income: XXX"
    //           Total Expense: XXX"
    //           List of Expense:
    //           <Category> <Name> <Cost>
    // MODIFIES: this
    // EFFECTS: loads income, expenses, list of expenses from budgetinput file
    public void load(String filename) throws IOException, NegativeAmountException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        for (String line : lines) {
            ArrayList<String> partsOfBalance = splitOnSpace(line, ": ");
            ArrayList<String> partsOfExpense = splitOnSpace(line, " ");
            if (line.contains("Total Income:")) {
                income.setIncome((Float.valueOf(partsOfBalance.get(1))));
            } else if (line.contains("Total Expenses:")) {
                expense.setExpense((Float.valueOf(partsOfBalance.get(1))));
            } else if (line.contains("List of Expenses:")) {
                System.out.println("");
            } else {
                if (!line.isEmpty()) {
                    String category = partsOfExpense.get(0);
                    String name = partsOfExpense.get(1);
                    float cost = Float.valueOf(partsOfExpense.get(2));
                    ExpenseItem loadedExpense = new ExpenseItem(name, category, cost);
                    expense.expenseItems.add(loadedExpense);
                    if (partsOfExpense.get(0).equals("Food")) {
                        food.addExpense(Float.valueOf(partsOfExpense.get(2)));
                    } else if (partsOfExpense.get(0).equals("Entertainment")) {
                        entertainment.addExpense(Float.valueOf(partsOfExpense.get(2)));
                    } else if (partsOfExpense.get(0).equals("Health")) {
                        health.addExpense(Float.valueOf(partsOfExpense.get(2)));
                    } else if (partsOfExpense.get(0).equals("Transportation")) {
                        transportation.addExpense(Float.valueOf(partsOfExpense.get(2)));
                    } else if (partsOfExpense.get(0).equals("Rent")) {
                        rent.addExpense(Float.valueOf(partsOfExpense.get(2)));
                    } else {
                        other.addExpense(Float.valueOf(partsOfExpense.get(2)));
                    }
                }
            }
        }
    }

    //EFFECTS: splits data at every given string
    private static ArrayList<String> splitOnSpace(String line, String splitter) {
        String[] splits = line.split(splitter);
        return new ArrayList<>(Arrays.asList(splits));
    }

    //EFFECTS: saves total income, total expenses, list of expenses and prints out what is saved
    public void save(String filename) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        lines.add("Total Income: " + income.getIncomeTotal());
        lines.add("Total Expenses: " + expense.getExpenseTotal());
        lines.add("List of Expenses:");
        for (Integer i = 0; i < expense.expenseItems.size(); i++) {
            lines.add(expense.expenseItems.get(i).getExpenseItemCategory() + " "
                    + expense.expenseItems.get(i).getExpenseItemName() + " "
                    + String.format("%.2f",expense.expenseItems.get(i).getExpenseItemCost()));
        }
        for (String line : lines) {
            System.out.println(line);
            writer.println(line);
        }
        writer.close();
    }

    //getter for income for testing
    public Income getIncome() {return BudgetTracker.income;}
    //getter for expense for testing
    public Expense getExpense() {return BudgetTracker.expense;}

}
