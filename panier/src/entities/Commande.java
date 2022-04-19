/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import static java.lang.Math.abs;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author yassi
 */
public class Commande {
    private int id_commande;
    private double montantCommande;
    private Date dateCommande;
    
    
    Random random = new Random();
    int matricule = abs(random.nextInt());

    public Commande(int id_commande, double montantCommande, Date dateCommande, int matricule) {
        this.id_commande = id_commande;
        this.montantCommande = montantCommande;
        this.dateCommande = dateCommande;
        this.matricule = matricule;
    }

    public Commande(double montantCommande, Date dateCommande, int matricule) {
        this.montantCommande = montantCommande;
        this.dateCommande = dateCommande;
        this.matricule = matricule;
    }

    public Commande(double montantCommande, int matricule) {
        this.montantCommande = montantCommande;
        this.matricule = matricule;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule() {
        this.matricule = abs(random.nextInt());
    }

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande + ", montantCommande=" + montantCommande + ", dateCommande=" + dateCommande + ", random=" + random + ", matricule=" + matricule + '}';
    }

   
    
    

    public Commande(int id_commande, double montantCommande, Date dateCommande) {
        this.id_commande = id_commande;
        this.montantCommande = montantCommande;
        this.dateCommande = dateCommande;
    }

    public Commande() {
    }

    
    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public double getMontantCommande() {
        return montantCommande;
    }

    public void setMontantCommande(double montantCommande) {
        this.montantCommande = montantCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }
    
    
    
}
