package domain.model.metroGateStates;

import domain.model.MetroCard;
import domain.model.MetroGate;

public interface MetroGateState {
    MetroGateState closedState = new Open();
    MetroGateState inactiveState = new Inactive();

    MetroGateState openState = new Open();
    default void activate(MetroGate gate) {

        gate.setState(closedState);
    }

    default void deactivate(MetroGate gate) {
        gate.setState(inactiveState);
    }

    default void scan(MetroGate gate, MetroCard card) {
        if (gate.getState() == inactiveState) {
            throw new IllegalArgumentException("State is inactive");

        }
        if (card.getAvailableRides() == 0) {
            throw new IllegalArgumentException("Card has 0 rides left");
        }
        if (card.getAvailableRides() != 0 && gate.getState() == closedState) {
            gate.setState(openState);
        }
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
