package balance;

public class MoneyCount {
    private static float income = 0;
    private static float expenses = 0;
    private static float balance = 0;

    public static void addIncome(float num) {
        income += num;
    }

    public static void addExpense(float num) {
        expenses += num;
    }

    public static float getIncome() {
        return income;
    }

    public static float getExpenses() {
        return expenses;
    }

    public static float getBalance() {
        balance = income - expenses;
        return balance;
    }

}
