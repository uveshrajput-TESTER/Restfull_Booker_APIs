package org.thetestingAcademy.Pojos.RequestPojos;

public class WrongBooking {
    private Integer firstname;
    private Integer lastname;
    private int totalprice;
    private boolean depositpaid;

    public Integer getLastname() {
        return lastname;
    }

    public void setLastname(Integer lastname) {
        this.lastname = lastname;
    }

    public Integer getFirstname() {
        return firstname;
    }

    public void setFirstname(Integer firstname) {
        this.firstname = firstname;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }
}
