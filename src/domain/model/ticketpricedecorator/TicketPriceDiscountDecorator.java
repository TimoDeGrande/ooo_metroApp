package domain.model.ticketpricedecorator;

public abstract class TicketPriceDiscountDecorator extends TicketPrice {
    TicketPrice ticketPrice;

    public TicketPriceDiscountDecorator(TicketPrice ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
