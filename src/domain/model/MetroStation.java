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

    public void updateMetroGatesAmount(int gates){
        this.gates.clear();
        this.nextId = 1;
        for(int i = 0; i < gates; i++){
            MetroGate metroGate = new MetroGate();
            metroGate.setId(nextId);
            this.gates.put(metroGate.getId(), metroGate);
            nextId++;
        }
    }

    public int getMetroGateAmount(){
        return this.gates.size();
    }
    public HashMap<Integer, MetroGate> getGates(){
        return this.gates;
    }
}
