package domain.model;

import java.util.ArrayList;
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

    public void updateMetroGatesAmount(ArrayList<MetroGate> gates){
        this.gates.clear();
        for(MetroGate gate: gates){
            gate.setId(nextId);
            this.gates.put(gate.getId(), gate);
            nextId++;
        }
    }

    public int getMetroGateAmount(){
        return this.gates.size();
    }
}
