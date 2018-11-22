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

import static model.Expense.*;

public class Operations {
    private Expense expense = BudgetTracker.expense;
    private Income income = BudgetTracker.income;

    // REQUIRES: .txt file in the form of:
    //           Total getInstance: XXX"
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
                income.setIncome((Double.valueOf(partsOfBalance.get(1))));
            } else if (line.contains("Total Expenses:")) {
                expense.setExpense((Double.valueOf(partsOfBalance.get(1))));
            } else if (line.contains("List of Expenses:")) {
                System.out.println("");
            } else {
                if (!line.isEmpty()) {
                    loadExpensesFromFile(partsOfExpense);
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads list of expenses from budgetinput file
    private void loadExpensesFromFile(ArrayList<String> partsOfExpense) throws NegativeAmountException {
        String category = partsOfExpense.get(0);
        String name = partsOfExpense.get(1);
        double cost = Double.valueOf(partsOfExpense.get(2));
        ExpenseItem loadedExpense = new ExpenseItem(name, category, cost);
        expense.addExpenseItem(loadedExpense);
        if (partsOfExpense.get(0).equals("Food")) {
            food.addExpenseAmount(Double.valueOf(partsOfExpense.get(2)));
        } else if (partsOfExpense.get(0).equals("Entertainment")) {
            entertainment.addExpenseAmount(Double.valueOf(partsOfExpense.get(2)));
        } else if (partsOfExpense.get(0).equals("Health")) {
            health.addExpenseAmount(Double.valueOf(partsOfExpense.get(2)));
        } else if (partsOfExpense.get(0).equals("Transportation")) {
            transportation.addExpenseAmount(Double.valueOf(partsOfExpense.get(2)));
        } else if (partsOfExpense.get(0).equals("Rent")) {
            rent.addExpenseAmount(Double.valueOf(partsOfExpense.get(2)));
        } else {
            other.addExpenseAmount(Double.valueOf(partsOfExpense.get(2)));
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
        for (Integer i = 0; i < expense.getExpenseItems().size(); i++) {
            lines.add(expense.getExpenseItems().get(i).getExpenseItemCategory() + " "
                    + expense.getExpenseItems().get(i).getExpenseItemName() + " "
                    + String.format("%.2f",expense.getExpenseItems().get(i).getExpenseItemCost()));
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
