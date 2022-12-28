package domain.model.ticketpricedecorator;

import domain.model.MetroCard;

public abstract class TicketPrice {
    private boolean is24Min;
    private boolean is64Plus;
    private boolean isStudent;
    private MetroCard metroCard;

    public TicketPrice() {}

    public void setMetroCard(MetroCard metroCard) {
        this.metroCard = metroCard;
    }

    public MetroCard getMetroCard() {
        return metroCard;
    }

    public boolean isIs24Min() {
        return is24Min;
    }

    public void setIs24Min(boolean is24Min) {
        this.is24Min = is24Min;
    }

    public boolean isIs64Plus() {
        return is64Plus;
    }

    public void setIs64Plus(boolean is64Plus) {
        this.is64Plus = is64Plus;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public abstract String getPriceText();
    public abstract double getPrice();
}
