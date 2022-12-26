package domain.model.ticketpricedecorator;

public class BasisTicketPrice extends TicketPrice {
    @Override
    public String getPriceText() {
        return "+€2,10";
    }

    @Override
    public double getPrice() {
        return 2.10;
    }
}
