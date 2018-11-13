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

    public void notifyObservers(ExpenseObserver expenseObserver) {
        for (ExpenseObserver observer: observers) {
            observer.update(expenseObserver);
        }
    }
}
