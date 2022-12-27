package domain.model;

import java.util.ArrayList;
import java.util.HashMap;

public class MetroStation {
    private int nextId = 1;
    private HashMap<Integer, MetroGate> gates = new HashMap<>();
    public void scanMetroGate(int id) {
        this.gates.get(id).scanMetroGate();
    }

    public void addMetroGate(MetroGate gate) {
        gate.setId(nextId);
        this.gates.put(gate.getId(), gate);
        nextId++;
    }
}
