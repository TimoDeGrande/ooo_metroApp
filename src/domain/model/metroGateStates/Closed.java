package domain.model.metroGateStates;

import domain.model.MetroCard;
import domain.model.MetroGate;
import java.time.Instant;
import java.time.LocalDate;

public class Closed implements MetroGateState {
    @Override
    public void scan(MetroGate gate, MetroCard card) {
        if (card.scan()) {
            LocalDate vervaldag = LocalDate.of(card.getYear() + 1, card.getMonth(), 1);
            if(LocalDate.now().isAfter(vervaldag)){
                throw new Alert(String.format("%s Scanned expired card at gate %s", Instant.now().toString().substring(11,19), gate.getId()));
            }
            gate.setState(new Open());
        } else {
            gate.setState(new Closed());
            throw new Alert(String.format("%s Scanned empty card at gate %s", Instant.now().toString().substring(11,19), gate.getId()));
        }
    }

    @Override
    public void createAlert(MetroGate gate) {
        System.out.println("je hebt niet gescand");
        throw new Alert(String.format("%s Unauthorized access in gate %s", Instant.now().toString().substring(11,19), gate.getId()));
    }

    @Override
    public void walkThroughGate(MetroGate gate) {
        this.createAlert(gate);
    }
    @Override
    public String toString(){
        return "Closed";
    }
}
