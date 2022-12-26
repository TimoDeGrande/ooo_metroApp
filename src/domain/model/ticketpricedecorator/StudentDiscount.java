package domain.model.ticketpricedecorator;

public class StudentDiscount extends TicketPriceDiscountDecorator {
    public StudentDiscount(TicketPrice ticketPrice) {
        super(ticketPrice);
    }

    @Override
    public String getPriceText() {
        return "-€0.25" + this.ticketPrice.getPriceText();
    }

    @Override
    public double getPrice() {
        return -0.25 + this.ticketPrice.getPrice();
    }
}
