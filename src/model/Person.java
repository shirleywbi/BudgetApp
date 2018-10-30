package model;

import java.util.Objects;

// "Person has Income", "Income has a list of people"
public class Person {
    private String name;
    private Income income;

    //EFFECTS: constructs person with given name
    public Person(String name) {
        this.name = name;
        this.income = new Income();
    }

    //MODIFIES: this
    //EFFECTS: sets income for a person
    public void setIncome(Income i) {
        if (!income.equals(i)) {
            this.income.removePersons(this);
            income = i;
            i.addPersons(this);
        }
    }

    //EFFECTS: resets income to null and removes name from persons for said income
    public void removeIncome(Income i) {
        if (income.equals(i)) {
            income = new Income();
            i.removePersons(this);
        }
    }

    //getter
    public Income getIncome() {
        return income;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
