package domain.model;

import java.time.LocalDate;

public class MetroCard {
    private int cardID;
    private int month, year;
    private int availableRides, totalUsedRides;

    public MetroCard(int cardID, int month, int year, int availableRides, int totalUsedRides){
        setCardID(cardID);
        setMonth(month);
        setYear(year);
        setAvailableRides(availableRides);
        setTotalUsedRides(totalUsedRides);
    }


    public int getCardID(){
        return cardID;
    }


    public void setCardID(int cardID) {
        if(cardID < 0){
            throw new DomainException("cardID has to be positive");
        }
        else{
            this.cardID = cardID;
        }
    }

    public void setMonth(int month) {
        if(month < 1 || month > 12){
            throw new DomainException("month is not valid");
        }
        else{
            this.month = month;
        }
    }

    public int getMonth(){return month;}


    public void setYear(int year) {
        if(year > LocalDate.now().getYear()){
            throw new DomainException("year is in future");
        }
        else if(year == LocalDate.now().getYear() && this.month > LocalDate.now().getMonth().getValue()){
            throw new DomainException("combination month year is in future");
        }
        else{
            this.year = year;
        }
    }

    public int getYear(){return year;}

    public void setAvailableRides(int availableRides) {
        if(availableRides < 0){
            throw new DomainException("available rides have to be positive");
        }
        else{
            this.availableRides = availableRides;
        }
    }

    public int getAvailableRides(){return availableRides;}

    public void setTotalUsedRides(int totalUsedRides) {
        if(totalUsedRides < 0){
            throw new DomainException("total used rides have to be positive");
        }
        else{
            this.totalUsedRides = totalUsedRides;
        }
    }

    public int getTotalUsedRides(){return totalUsedRides;}



    public String toString(){
        return getCardID()+"-"+getMonth()+"-"+getYear()+"-"+getAvailableRides()+"-"+getTotalUsedRides();
    }
}
