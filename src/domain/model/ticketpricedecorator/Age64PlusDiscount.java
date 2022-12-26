package domain.model.ticketpricedecorator;

public class Age64PlusDiscount extends TicketPriceDiscountDecorator {
    public Age64PlusDiscount(TicketPrice ticketPrice) {
        super(ticketPrice);
    }

    @Override
    public String getPriceText() {
        return "-€0.15" + this.ticketPrice.getPriceText();
    }

    @Override
    public double getPrice() {
        return -0.15 + this.ticketPrice.getPrice();
    }
}
