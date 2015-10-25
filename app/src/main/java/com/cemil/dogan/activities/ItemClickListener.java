package com.cemil.dogan.activities;

/**
 * Created by Dogan on 25.10.15.
 */


 import domain.Gadget;
 import domain.Loan;
 import domain.Reservation;

public interface ItemClickListener {
    void onItemClicked(Gadget currentGadget);
    void setTitle (String title);
    void onItemClicked(Loan currentReservation);
}