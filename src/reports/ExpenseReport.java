package reports;

import model.expenses.*;

public class ExpenseReport extends Report {

    private Expense food = Expense.food;
    private Expense entertainment = Expense.entertainment;
    private Expense health = Expense.health;
    private Expense transportation = Expense.transportation;
    private Expense rent = Expense.rent;
    private Expense other = Expense.other;

    // EFFECTS: Constructs expense report
    protected ExpenseReport() {
        super("Expense");
        balance = expense;
    }


    // EFFECTS: Displays subtotals of each expense category
    public void getExpenseBreakdown() {
        System.out.println("Expense Categories:");
        food.getReport(food.getExpenseName(), food.getExpense());
        entertainment.getReport(entertainment.getExpenseName(), entertainment.getExpense());
        health.getReport(health.getExpenseName(), health.getExpense());
        transportation.getReport(transportation.getExpenseName(), transportation.getExpense());
        rent.getReport(rent.getExpenseName(), rent.getExpense());
        other.getReport(other.getExpenseName(), other.getExpense());
    }

    //EFFECTS: calculates and prints percentage of money spent in each expense category
    public void expensePercentile() {
        float foodPercent = getPercent(food.getExpense());
        float entertainmentPercent = getPercent(entertainment.getExpense());
        float healthPercent = getPercent(health.getExpense());
        float transportPercent = getPercent(transportation.getExpense());
        float rentPercent = getPercent(rent.getExpense());
        float otherPercent = getPercent(other.getExpense());
        System.out.println("Percent Breakdown:");
        System.out.print("         ");
        System.out.printf(food.getExpenseName() + ": %.2f %% %n", foodPercent);
        System.out.print("         ");
        System.out.printf(entertainment.getExpenseName() + ": %.2f %% %n", entertainmentPercent);
        System.out.print("         ");
        System.out.printf(health.getExpenseName() + ": %.2f %% %n", healthPercent);
        System.out.print("         ");
        System.out.printf(transportation.getExpenseName() + ": %.2f %% %n", transportPercent);
        System.out.print("         ");
        System.out.printf(rent.getExpenseName() + ": %.2f %% %n", rentPercent);
        System.out.print("         ");
        System.out.printf(other.getExpenseName() + ": %.2f %% %n", otherPercent);
    }

    // EFFECTS: returns percentage of cost to total expense
    private float getPercent(float cost) {
        return cost / expense * 100;
    }

}

//Pie chart from https://stackoverflow.com/questions/13662984/creating-pie-charts-programmatically
