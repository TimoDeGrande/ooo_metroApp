package domain.model.ticketpricedecorator;

public class ChristmasLeaveDiscount extends TicketPriceDiscountDecorator {


    public ChristmasLeaveDiscount(TicketPrice ticketPrice) {
        super(ticketPrice);
    }

    @Override
    public String getPriceText() {
        return "-€0.10" + this.ticketPrice.getPriceText();
    }

    @Override
    public double getPrice() {
        return -0.10 + this.ticketPrice.getPrice();
    }
}
