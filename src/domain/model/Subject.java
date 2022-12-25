package domain.model;

import java.util.ArrayList;

public interface Subject {
    ArrayList<Observer> observers = new ArrayList<>();

    default void addObserver(Observer o) {
        this.observers.add(o);
        o.setSubject(this);
    }

    default void updateObservers() {
        this.observers.forEach(Observer::update);
    }

}
