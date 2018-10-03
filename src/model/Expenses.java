package model;

// interface Expenses with method getSubExpense() and addSubExpense()
// REQUIRES: addSubExpense(): num >= 0
// EFFECTS: getSubExpense(): returns the expense sub-balance
//          addSubExpense(): Adds num to the expense sub-balance
public interface Expenses {
    float getSubExpense();
    float addSubExpense(float num);
}





