package balance;

public class MoneyCount {
    private static int income = 0;
    private static int expenses = 0;

    public static void moneyEarned() {
        System.out.println("You have earned $" +income+ " so far.");
    }

    public static void moneySpent() {
        System.out.println("You have spent $" +expenses+ " so far.");

    }
}
