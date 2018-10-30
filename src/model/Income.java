package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Income {
    private float incomeTotal;
    private List<Person> persons = new ArrayList<>();

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

    // REFLEXIVE WITH INCOME

    // MODIFIES: this
    // EFFECTS: collects people with the same income
    public void addPersons(Person p) {
        if (!persons.contains(p)) {
            persons.add(p);
            p.setIncome(this);
        }
    }

    // EFFECTS: removes person from list of people with the same income; if list empty, do nothing
    public void removePersons(Person p) {
        if (persons.contains(p)) {
            persons.remove(p);
            p.setIncome(new Income());
        }
    }

    //getter
    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Income income = (Income) o;
        return Float.compare(income.incomeTotal, incomeTotal) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(incomeTotal);
    }
}

