/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

/**
 *
 * @author msi
 */
public class Promo {
    private int Id ;
    private int pourcent_promo;
    private String date_exp ;

    public Promo() {
    }

    public Promo(int pourcent_promo, String date_exp) {
        this.pourcent_promo = pourcent_promo;
        this.date_exp = date_exp;
    }

    public Promo(int Id, int pourcent_promo, String date_exp) {
        this.Id = Id;
        this.pourcent_promo = pourcent_promo;
        this.date_exp = date_exp;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getPourcent_promo() {
        return pourcent_promo;
    }

    public void setPourcent_promo(int pourcent_promo) {
        this.pourcent_promo = pourcent_promo;
    }

    public String getDate_exp() {
        return date_exp;
    }

    public void setDate_exp(String date_exp) {
        this.date_exp = date_exp;
    }

    @Override
    public String toString() {
        return "Promo{" + "Id=" + Id + ", pourcent_promo=" + pourcent_promo + ", date_exp=" + date_exp + '}';
    }
    
}

