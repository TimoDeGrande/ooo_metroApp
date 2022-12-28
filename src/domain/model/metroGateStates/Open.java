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
        //als je twee keer na elkaar scant
        this.createWarning(gate);
    }

    @Override
    public void createWarning(MetroGate gate) {
        System.out.println("je hebt twee keer na elkaar gescand eikel");
    }
}
