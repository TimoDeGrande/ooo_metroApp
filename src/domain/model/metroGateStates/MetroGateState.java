package domain.model.metroGateStates;

import domain.model.MetroCard;
import domain.model.MetroGate;

public interface MetroGateState {
    default void activate(MetroGate gate) {
        throw new IllegalArgumentException("");
    }

    default void deactivate(MetroGate gate) {
        throw new IllegalArgumentException("");
    }

    default void scan(MetroGate gate, MetroCard card) {
        throw new IllegalArgumentException("");
    }

    default void walkThroughGate(MetroGate gate) {
        throw new IllegalArgumentException("");
    }

    default void createWarning(MetroGate gate) {
        throw new IllegalArgumentException("");
    }

    default void createAlert(MetroGate gate) {
        throw new IllegalArgumentException("");
    }
}
