package domain.model.metroGateStates;

import domain.model.MetroCard;
import domain.model.MetroGate;

public class Open implements MetroGateState {
    @Override
    public void walkThroughGate(MetroGate gate) {
        gate.setState(new Closed());
    }

    @Override
    public void scan(MetroGate gate, MetroCard card) {
        this.createWarning(gate);
    }

    @Override
    public void createWarning(MetroGate gate) {
        //todo bericht sturen dat dit niet kan.
    }
}
