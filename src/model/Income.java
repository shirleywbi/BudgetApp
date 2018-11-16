package model;

import java.util.Observable;

public class Income extends Observable{
    private static Income instance=null;
    private float incomeTotal;

    // EFFECTS: constructs income with 0 income
    private Income() {
        this.incomeTotal = 0;
    }

    public static Income getInstance() {
        if (instance == null) {
            instance = new Income();
        }
        return instance;
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
