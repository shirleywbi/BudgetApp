package model;

import exceptions.NegativeAmountException;

import java.util.Observable;

public class Income extends Observable {
    private static Income instance = null;
    private double incomeTotal;

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
    public double getIncomeTotal() {
        return incomeTotal;
    }

    // MODIFIES: this
    // EFFECTS: sets income to given num
    public void setIncome(double num) {
        this.incomeTotal = num;
        setChanged();
        notifyObservers("incomeTotal");
    }

    // REQUIRES: num >= 0
    // MODIFIES: this
    // EFFECTS: adds num to income
    public double addIncome(double num) {
        try {
            if (num < 0) {
                throw new NegativeAmountException();
            }
            incomeTotal += num;
            setChanged();
            notifyObservers("incomeTotal");
        } catch (NegativeAmountException ex) {
        }
        return incomeTotal;
    }
}
