package domain.model;

import java.util.ArrayList;

public interface Subject {
    ArrayList<Observer> observers = new ArrayList<>();

    default void addObserver(Observer o) {
        this.observers.add(o);
    }

    default void updateObservers() {
        this.observers.forEach(Observer::update);
    }

}
