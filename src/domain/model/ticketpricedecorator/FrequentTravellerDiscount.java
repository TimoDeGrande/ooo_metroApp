package domain.model.ticketpricedecorator;

public class FrequentTravellerDiscount extends TicketPriceDiscountDecorator {
    public FrequentTravellerDiscount(TicketPrice ticketPrice) {
        super(ticketPrice);
    }

    @Override
    public String getPriceText() {
        return "-€0.20" + this.ticketPrice.getPriceText();
    }

    @Override
    public double getPrice() {
        return -0.20 + this.ticketPrice.getPrice();
    }
}
