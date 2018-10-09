package reports;

import model.*;
import model.expenses.*;

// class ExpenseReport with method getReport() and getExpenseList()
public class ExpenseReport extends Report {
    private Balances expenseList = Balances.bal;
    private Expense food = Balances.food;
    private Expense entertainment = Balances.entertainment;
    private Expense health = Balances.health;
    private Expense transportation = Balances.transportation;
    private Expense rent = Balances.rent;
    private Expense other = Balances.other;

    // EFFECTS: Constructs expense report
    public ExpenseReport() {
        reportName = "Expense";
        balance = String.format("%.2f", Balances.bal.getExpenses());
    }

    // EFFECTS: Displays list of expenses; if no expenses, shows nothing
    public void getExpenseList() {
        for (Integer i = 0; i < expenseList.nameList.size(); i++) {
            System.out.printf("         " + expenseList.categoryList.get(i) + " "
                    + expenseList.nameList.get(i) + " $%.2f" + "%n", expenseList.costList.get(i));
        }
    }

    // EFFECTS: Displays subtotals of each expense category
    public void getExpenseBreakdown() {
        System.out.println("Expense Categories:");
        System.out.print("         ");
        food.getReport(food.reportName, food.subtotal);
        System.out.print("         ");
        entertainment.getReport(entertainment.reportName, entertainment.subtotal);
        System.out.print("         ");
        health.getReport(health.reportName, health.subtotal);
        System.out.print("         ");
        transportation.getReport(transportation.reportName, transportation.subtotal);
        System.out.print("         ");
        rent.getReport(rent.reportName, rent.subtotal);
        System.out.print("         ");
        other.getReport(other.reportName, other.subtotal);
    }

    //EFFECTS: calculates and prints percentage of money spent in each expense category
    public void expensePercentile() {
        float foodPercent = getPercent(food.subtotal);
        float entertainmentPercent = getPercent(entertainment.subtotal);
        float healthPercent = getPercent(health.subtotal);
        float transportPercent = getPercent(transportation.subtotal);
        float rentPercent = getPercent(rent.subtotal);
        float otherPercent = getPercent(other.subtotal);
        System.out.println("Percent Breakdown:");
        System.out.print("         ");
        System.out.printf(food.reportName + ": %.2f %% %n", foodPercent);
        System.out.print("         ");
        System.out.printf(entertainment.reportName + ": %.2f %% %n", entertainmentPercent);
        System.out.print("         ");
        System.out.printf(health.reportName + ": %.2f %% %n", healthPercent);
        System.out.print("         ");
        System.out.printf(transportation.reportName + ": %.2f %% %n", transportPercent);
        System.out.print("         ");
        System.out.printf(rent.reportName + ": %.2f %% %n", rentPercent);
        System.out.print("         ");
        System.out.printf(other.reportName + ": %.2f %% %n", otherPercent);
    }

    // EFFECTS: returns percentage of cost to total expense
    public float getPercent(float cost) {
        return cost / Balances.bal.getExpenses() * 100;
    }

}

//Pie chart from https://stackoverflow.com/questions/13662984/creating-pie-charts-programmatically
