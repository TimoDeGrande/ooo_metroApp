package domain.model;

import java.time.LocalDate;

public class MetroCard {
    private int cardID;
    private int month, year;
    private int availableRides, totalUsedRides;

    public MetroCard(int cardID, int month, int year, int availableRides, int totalUsedRides) {
        setCardID(cardID);
        setMonth(month);
        setYear(year);
        setAvailableRides(availableRides);
        setTotalUsedRides(totalUsedRides);
    }

    public void setCardID(int cardID) {
        if (cardID < 0) {
            throw new DomainException("cardID has to be larger than 0");
        }
        this.cardID = cardID;
    }

    public void setMonth(int month) {
        if (month < 1 || month > 12) {
            throw new DomainException("month is not valid");
        }
        this.month = month;
    }

    public void setYear(int year) {
        if (year > LocalDate.now().getYear()) {
            throw new DomainException("year is in future");
        }
        if (year == LocalDate.now().getYear() && this.month > LocalDate.now().getMonth().getValue()) {
            throw new DomainException("combination month year is in future");
        }
        this.year = year;
    }

    public void setAvailableRides(int availableRides) {
        if (availableRides < 0) {
            throw new DomainException("available rides have to be larger than 0");
        }
        this.availableRides = availableRides;
    }

    public void setTotalUsedRides(int totalUsedRides) {
        if (totalUsedRides < 0) {
            throw new DomainException("total used rides have to be larger than 0");
        }
        this.totalUsedRides = totalUsedRides;
    }

    public int getCardID() {
        return cardID;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getAvailableRides() {
        return availableRides;
    }

    public int getTotalUsedRides() {
        return totalUsedRides;
    }

