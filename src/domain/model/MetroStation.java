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
        this.nextId = 1;
        HashMap<Integer, MetroGate> newGates = new HashMap<>();
        for(int i = 0; i < gates; i++){
            MetroGate metroGate = new MetroGate();
            metroGate.setId(nextId);
            newGates.put(metroGate.getId(), metroGate);
            nextId++;
        }
        this.gates = newGates;
    }

    public int getMetroGateAmount(){
        return this.gates.size();
    }
    public HashMap<Integer, MetroGate> getGates(){
        return this.gates;
    }
}
