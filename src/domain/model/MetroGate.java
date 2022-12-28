package domain.model;

import domain.model.metroGateStates.Inactive;
import domain.model.metroGateStates.MetroGateState;

public class MetroGate {
    private int id;
    private MetroGateState currentState;

    public MetroGate() {
        this.currentState = new Inactive();
    }

    public void scanMetroGate(MetroCard c) {
        this.currentState.scan(this, c);
    }
    public void walkThroughGate(){
        this.currentState.walkThroughGate(this);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setState(MetroGateState state) {
        this.currentState = state;
    }

    public MetroGateState getState() {
        return currentState;
    }
}
