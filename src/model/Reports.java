package model;

public class Reports {
    private Balances bal = Balances.bal;
    public static Reports reports = new Reports();

    // EFFECTS: Displays total income, total expenses, list of expenses and total balance
    public void getSummaryReport() {
        System.out.printf("********************************* %nReport:%n");
        getIncomeReport();
        getExpenseReport();
        getExpenseList();
        getBalanceReport();
        System.out.println("*********************************");
    }

    // EFFECTS: Displays total income
    private void getIncomeReport() {
        System.out.printf("You have earned $%.2f" + " so far. %n", bal.getIncome());
    }

    // EFFECTS: Displays total expenses
    private void getExpenseReport() {
        System.out.printf("You have spent $%.2f" + " so far. %n", bal.getExpenses());
    }

    // EFFECTS: Displays list of expenses; if no expenses, shows nothing
    private void getExpenseList() {
        for (Integer i = 0; i < bal.nameList.size(); i++) {
            System.out.printf("     " + bal.nameList.get(i) + " $%.2f" + "%n", bal.costList.get(i));
        }
    }

    // EFFECTS: Displays total balance
    private void getBalanceReport() {
        System.out.printf("Your current model is $%.2f" + ". %n", bal.getBalance());
    }


}