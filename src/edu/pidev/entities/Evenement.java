/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.entities;

import java.sql.Date;

/**
 *
 * @author karim
 */
public class Evenement {
  private int idEvent;
    private String nomEvent;
    private Date date;
    private String lieu;
    private String descEvent;
    private String type;

    public Evenement() {
    }

    public Evenement(int idEvent) {
        this.idEvent = idEvent;
    }

    public Evenement(int idEvent, String nomEvent, Date date) {
        this.idEvent = idEvent;
        this.nomEvent = nomEvent;
        this.date = date;
    }

    public Evenement(int idEvent, String nomEvent, Date date, String lieu, String descEvent, String type) {
        this.idEvent = idEvent;
        this.nomEvent = nomEvent;
        this.date = date;
        this.lieu = lieu;
        this.descEvent = descEvent;
        this.type = type;
    }

    public Evenement(String nomEvent, Date date, String lieu, String descEvent, String type) {
        this.nomEvent = nomEvent;
        this.date = date;
        this.lieu = lieu;
        this.descEvent = descEvent;
        this.type = type;
    }
    

    public int getIdEvent() {
        return idEvent;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public Date getDate() {
        return date;
    }

    public String getLieu() {
        return lieu;
    }

    public String getDescEvent() {
        return descEvent;
    }

    public String getType() {
        return type;
    }


   

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDescEvent(String descEvent) {
        this.descEvent = descEvent;
    }
        public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEvent=" + idEvent + ", nomEvent=" + nomEvent + ", date=" + date + ", lieu=" + lieu + ", descEvent=" + descEvent + ", type=" + type + '}';
    }
    
}
