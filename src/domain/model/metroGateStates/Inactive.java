package domain.model.metroGateStates;

import domain.model.MetroGate;

public class Inactive implements MetroGateState {
        @Override
        public void activate(MetroGate gate) {
            gate.setState(new Closed());
        }
    @Override
    public String toString(){
        return "Inactive";
    }




}
