package org.thetestingAcademy.Pojos.RequestPojos;

public class Booking {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private Bookingdates bookingdates;
    private String additionalneeds;

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public int getTotalPrice() {
        return totalprice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalprice = totalPrice;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public Bookingdates getBookingDates() {
        return bookingdates;
    }

    public void setBookingDates(Bookingdates bookingDates) {
        this.bookingdates = bookingDates;
    }

    public boolean getDepositPaid() {
        return depositpaid;
    }

    public void setDepositPaid(boolean depositPaid) {
        this.depositpaid = depositPaid;
    }

    public String getAdditionalNeeds() {
        return additionalneeds;
    }

    public void setAdditionalNeeds(String additionalNeeds) {
        this.additionalneeds = additionalNeeds;
    }
}
