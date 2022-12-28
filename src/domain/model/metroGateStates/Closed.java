package domain.model.metroGateStates;

import domain.model.MetroCard;
import domain.model.MetroGate;

public class Closed implements MetroGateState {
    @Override
    public void scan(MetroGate gate, MetroCard card) {
        if (card.scan()) {
            gate.setState(new Open());
        } else {
            gate.setState(new Closed());
        }
    }

    @Override
    public void createAlert(MetroGate gate) {
        System.out.println("je hebt niet gescand");
    }

    @Override
    public void walkThroughGate(MetroGate gate) {
        this.createAlert(gate);
    }
}
