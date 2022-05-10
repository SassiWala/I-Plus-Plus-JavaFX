/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

/**
 *
 * @author karim
 */
public class Reservation {
      private int idReservation;
     private int idEvent;
     private int idUser;

    public Reservation() {
    }

    public Reservation(int idReservation, int idEvent, int idUser) {
        this.idReservation = idReservation;
        this.idEvent = idEvent;
        this.idUser = idUser;
    }

    public Reservation(int idEvent, int idUser) {
        this.idEvent = idEvent;
        this.idUser = idUser;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "ReservationCrud{" + "idReservation=" + idReservation + ", idEvent=" + idEvent + ", idUser=" + idUser + '}';
    }
 
}


