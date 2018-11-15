package observer;

import java.util.ArrayList;
import java.util.List;

public class ExpenseSubject {
    private List<ExpenseObserver> observers = new ArrayList<>();


    public void addObservers(ExpenseObserver expenseObserver) {
        if (!observers.contains(expenseObserver)) {
            observers.add(expenseObserver);
        }
    }

    //EFFECTS: notifies each observer of update
    public void notifyObservers(ExpenseObserver expenseObserver) {
        for (ExpenseObserver observer: observers) {
            observer.update(expenseObserver);
        }
    }
}
