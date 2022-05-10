/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import java.util.Date;

/**
 *
 * @author yassi
 */
public class Commande {
    private int id_commande;
    private float montantCommande;
    private Date dateCommande;
    private int matricule;
    private int user_id;
    private String nomUser;

    public Commande(int id_commande, float montantCommande, Date dateCommande, int user_id, String nomUser) {
        this.id_commande = id_commande;
        this.montantCommande = montantCommande;
        this.dateCommande = dateCommande;
        this.user_id = user_id;
        this.nomUser = nomUser;
    }

    public Commande(float montantCommande, int user_id) {
        this.montantCommande = montantCommande;
        this.user_id = user_id;
    }
    

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Commande(int id_commande, float montantCommande, Date dateCommande, int matricule, int user_id) {
        this.id_commande = id_commande;
        this.montantCommande = montantCommande;
        this.dateCommande = dateCommande;
        this.matricule = matricule;
        this.user_id = user_id;
    }
    
    
    public Commande(int id_commande, float montantCommande, Date dateCommande, int matricule) {
        this.id_commande = id_commande;
        this.montantCommande = montantCommande;
        this.dateCommande = dateCommande;
        this.matricule = matricule;
    }

    public Commande(float montantCommande, Date dateCommande, int matricule) {
        this.montantCommande = montantCommande;
        this.dateCommande = dateCommande;
        this.matricule = matricule;
    }

   

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande + ", montantCommande=" + montantCommande + ", dateCommande=" + dateCommande + ", matricule=" + matricule + '}';
    }

    

    

   
    
    

    public Commande(int id_commande, float montantCommande, Date dateCommande) {
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

    public float getMontantCommande() {
        return montantCommande;
    }

    public void setMontantCommande(float montantCommande) {
        this.montantCommande = montantCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }
}
