package model;

public class SingletonIncome {
    private static SingletonIncome single_instance=null;
    private float incomeTotal;

    // EFFECTS: constructs income with 0 income
    private SingletonIncome() {
        this.incomeTotal = 0;
    }

    public static SingletonIncome SingletonIncome() {
        if (single_instance == null) {
            single_instance = new SingletonIncome();
        }
        return single_instance;
    }

    // EFFECTS: returns the total subtotal of the income
    public float getIncomeTotal() {
        return incomeTotal;
    }

    // MODIFIES: this
    // EFFECTS: sets income to given num
    public void setIncome(float num) {
        this.incomeTotal = num;
    }

    // REQUIRES: num >= 0
    // MODIFIES: this
    // EFFECTS: adds num to income
    public float addIncome(float num) {
        incomeTotal += num;
        return incomeTotal;
    }
}
