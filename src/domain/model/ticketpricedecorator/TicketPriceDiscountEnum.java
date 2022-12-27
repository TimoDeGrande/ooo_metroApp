package domain.model.ticketpricedecorator;

public enum TicketPriceDiscountEnum {
    AGE64PLUSDISCOUNT("Age64PlusDiscount"),
    CHRISTMASLEAVEDISCOUNT("ChristmasLeaveDiscount"),
    STUDENTDISCOUNT("StudentDiscount"),
    FREQUENTTRAVELLERDISCOUNT("FrequentTravellerDiscount");

    private String className;
    public final static String path = "domain.model.ticketpricedecorator.";
    TicketPriceDiscountEnum(String className) {
        setClassName(className);
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public String getPath() {
        return TicketPriceDiscountEnum.path + this.getClassName();
    }
}
