package ui;

import model.Balances;
import model.Expenses;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Operations {
    private Balances balances = Balances.bal;
    private Expenses foodSubtotal = Balances.foodTotal;
    private Expenses rentSubtotal = Balances.rentTotal;
    private Expenses transportationSubtotal = Balances.transportationTotal;
    private Expenses otherSubtotal = Balances.otherTotal;

    // REQUIRES: .txt file in the form of:
    //           Total Income: XXX"
    //           Total Expenses: XXX"
    //           List of Expenses:
    //           <Category> <Name> <Cost>
    // MODIFIES: this
    // EFFECTS: loads income, expenses, list of expenses from budgetinput file
    public void load(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        for (String line : lines) {
            ArrayList<String> partsOfBalance = splitOnSpace(line, ": ");
            ArrayList<String> partsOfExpense = splitOnSpace(line, " ");
            if (line.contains("Total Income:")) {
                Balances.income = (Float.valueOf(partsOfBalance.get(1)));
            } else if (line.contains("Total Expenses:")) {
                Balances.expenses = (Float.valueOf(partsOfBalance.get(1)));
            } else if (line.contains("List of Expenses:")) {
                System.out.println("");
            } else {
                if (!line.isEmpty()) {
                    balances.categoryList.add(partsOfExpense.get(0));
                    balances.nameList.add(partsOfExpense.get(1));
                    balances.costList.add(Float.valueOf(partsOfExpense.get(2)));
                    if (partsOfExpense.get(0).equals("Food")) {
                        foodSubtotal.addSubExpense(Float.valueOf(partsOfExpense.get(2)));
                    } else if (partsOfExpense.get(0).equals("Rent")) {
                        rentSubtotal.addSubExpense(Float.valueOf(partsOfExpense.get(2)));
                    } else if (partsOfExpense.get(0).equals("Transportation")) {
                        transportationSubtotal.addSubExpense(Float.valueOf(partsOfExpense.get(2)));
                    } else {
                        otherSubtotal.addSubExpense(Float.valueOf(partsOfExpense.get(2)));
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
        lines.add("Total Income: " + balances.getIncome());
        lines.add("Total Expense: " + balances.getExpenses());
        lines.add("List of Expenses:");
        for (Integer i = 0; i < balances.nameList.size(); i++) {
            lines.add(balances.categoryList.get(i) + " " + balances.nameList.get(i) + " " + balances.costList.get(i));
        }
        for (String line : lines) {
            System.out.println(line);
            writer.println(line);
        }
        writer.close();
    }

}
