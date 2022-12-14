package domain.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Subject {
    Map<MetroEventsEnum, List<Observer>> observers = new HashMap<>();

    default void addObserver(MetroEventsEnum event, Observer o) {
        this.observers.get(event).add(o);
        o.setSubject(this);
    }

    default void updateObservers(MetroEventsEnum event) {
        this.observers.get(event).forEach(e -> e.update(event));
    }
    default void close(MetroEventsEnum event) {
        this.observers.get(event).forEach(e -> e.close(event));
    }

    default Map<MetroEventsEnum, List<Observer>> getObservers() {
        return this.observers;
    }

}
