package model;

public class Income {
    private float incomeTotal;

    // EFFECTS: constructs income with 0 income
    public Income() {
        this.incomeTotal = 0;
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

