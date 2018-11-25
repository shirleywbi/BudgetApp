package ui;

import model.Income;
import model.Expense;
import model.ExpenseItem;
import ui.panel.ExpensePanel;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import static model.Expense.*;
import static model.ExpenseType.*;

public class Operations extends Observable{
    public static Expense expense = ExpensePanel.expense;
    private Income income = Income.getInstance();

    // REQUIRES: .txt file in the form of:
    //           Total getInstance: XXX"
    //           Total Expense: XXX"
    //           List of Expense:
    //           <Category> <Name> <Cost>
    // MODIFIES: this
    // EFFECTS: loads income, expenses, list of expenses from budgetinput file
    public void load(String filename) throws IOException {
        expense.clearExpense();
        List<String> lines = Files.readAllLines(Paths.get(filename));
        for (String line : lines) {
            ArrayList<String> partsOfBalance = splitOnSpace(line, ": ");
            ArrayList<String> partsOfExpense = splitOnSpace(line, " ");
            try {
                if (line.contains("Total Income:")) {
                    income.setIncome((Double.valueOf(partsOfBalance.get(1))));
                } else if (line.contains("Total Expenses:")) {
                    expense.setExpense((Double.valueOf(partsOfBalance.get(1))));
                } else if (line.contains("List of Expenses:")) {
                } else {
                    if (!line.isEmpty()) {
                        loadExpensesFromFile(partsOfExpense);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Please check numbers in file. Formatting required.");
            }
        }
        setChanged();
        notifyObservers("new expense");
    }

    // MODIFIES: this
    // EFFECTS: loads list of expenses from budgetinput file
    private void loadExpensesFromFile(ArrayList<String> partsOfExpense) {
        try {
            String category = partsOfExpense.get(0);
            String name = partsOfExpense.get(1);
            double cost = Double.valueOf(partsOfExpense.get(2));
            ExpenseItem loadedExpense = new ExpenseItem(name, category, cost);
            expense.addExpenseItem(loadedExpense);
            if (partsOfExpense.get(0).equals(FOOD.getExpenseType())) {
                food.addExpenseAmount(Double.valueOf(partsOfExpense.get(2)));
            } else if (partsOfExpense.get(0).equals(ENTERTAINMENT.getExpenseType())) {
                entertainment.addExpenseAmount(Double.valueOf(partsOfExpense.get(2)));
            } else if (partsOfExpense.get(0).equals(HEALTH.getExpenseType())) {
                health.addExpenseAmount(Double.valueOf(partsOfExpense.get(2)));
            } else if (partsOfExpense.get(0).equals(TRANSPORTATION.getExpenseType())) {
                transportation.addExpenseAmount(Double.valueOf(partsOfExpense.get(2)));
            } else if (partsOfExpense.get(0).equals(RENT.getExpenseType())) {
                rent.addExpenseAmount(Double.valueOf(partsOfExpense.get(2)));
            } else {
                other.addExpenseAmount(Double.valueOf(partsOfExpense.get(2)));
            }
        } catch (NumberFormatException e) {
            System.out.println("There is an error in the file. Please check your file.");
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
        lines.add("Total Income: " + decimalFormat(income.getIncomeTotal()));
        lines.add("Total Expenses: " + decimalFormat(expense.getExpenseTotal()));
        lines.add("List of Expenses:");
        for (Integer i = 0; i < expense.getExpenseItems().size(); i++) {
            lines.add(expense.getExpenseItems().get(i).getExpenseItemCategory() + " "
                    + expense.getExpenseItems().get(i).getExpenseItemName() + " "
                    + decimalFormat(expense.getExpenseItems().get(i).getExpenseItemCost()));
        }
        for (String line : lines) {
            writer.println(line);
        }
        writer.close();
    }

    // EFFECTS: converts double to string in currency format X.XX
    private String decimalFormat(double num) {
        return String.format("%.2f", num);
    }
}
