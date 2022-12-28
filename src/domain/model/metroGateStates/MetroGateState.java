package domain.model.metroGateStates;

import domain.model.MetroCard;
import domain.model.MetroGate;

public interface MetroGateState {
    default void activate(MetroGate gate) {
        gate.setState(new Open());
    }

    default void deactivate(MetroGate gate) {
        gate.setState(new Inactive());
    }

    default void scan(MetroGate gate, MetroCard card) {
        gate.setState(new Open());
    }

    default void walkThroughGate(MetroGate gate) {
        gate.setState(new Open());
    }

    default void createWarning(MetroGate gate) {
        throw new IllegalArgumentException("");
    }

    default void createAlert(MetroGate gate) {
        throw new IllegalArgumentException("");
    }
}
