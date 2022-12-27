package domain.model;

import domain.model.metroGateStates.Inactive;
import domain.model.metroGateStates.MetroGateState;

import java.util.HashMap;

public class MetroStation {
    private int nextId = 1;
    private HashMap<Integer, MetroGate> gates = new HashMap<>();
    public void scanMetroGate(int id, MetroCard card) {
        this.gates.get(id).scanMetroGate(card);
    }

    public void addMetroGate(MetroGate gate) {
        gate.setId(nextId);
        this.gates.put(gate.getId(), gate);
        nextId++;
    }

    public void activateMetroGate(int id) {
        MetroGateState state = new Active();
        this.gates.get(id).setState(state);
    }

    public void deactivateMetroGate(int id) {
        MetroGateState state = new Inactive();
        this.gates.get(id).setState(state);
    }
}
