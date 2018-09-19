package balance;

import java.util.ArrayList;

public class Expenses {
    private static ArrayList<String> nameList = new ArrayList<>();
    private static ArrayList<Number> costList = new ArrayList<>();


    public static void addExpenseEntry(String name, float cost) {
        nameList.add(name);
        costList.add(cost);
    }

    public static void getExpenseList() {
        for (int i = 0; i < nameList.size(); i++) {
            System.out.printf("     " + nameList.get(i) + " $%.2f" + "%n", costList.get(i));
            }
    }
}
