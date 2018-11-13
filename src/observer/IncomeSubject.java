package observer;

import java.util.ArrayList;
import java.util.List;

public class IncomeSubject {
    private List<IncomeObserver> observers = new ArrayList<>();

    public void addObservers(IncomeObserver incomeObserver) {
        if (!observers.contains(incomeObserver)) {
            observers.add(incomeObserver);
        }
    }

    public void notifyObservers(IncomeObserver incomeObserver) {
        for (IncomeObserver observer: observers) {
            observer.update(incomeObserver);
        }
    }
}
