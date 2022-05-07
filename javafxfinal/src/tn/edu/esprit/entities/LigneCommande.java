/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

/**
 *
 * @author yassi
 */
public class LigneCommande {
    private int id_ligne;
    private int quantite;
    private int id_commande;
    private int id_produit;
    private String nomProd;

    
    
    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    @Override
    public String toString() {
        return "LigneCommande{" + "id_ligne=" + id_ligne + ", quantite=" + quantite + ", id_commande=" + id_commande + ", id_produit=" + id_produit + ", nomProd=" + nomProd + '}';
    }
    
    

    public LigneCommande(int id_ligne, int quantite, int id_commande, int id_produit) {
        this.id_ligne = id_ligne;
        this.quantite = quantite;
        this.id_commande = id_commande;
        this.id_produit = id_produit;
    }

    public LigneCommande() {
    }

    public LigneCommande(int id_ligne, int quantite, int id_commande, int id_produit, String nomProd) {
        this.id_ligne = id_ligne;
        this.quantite = quantite;
        this.id_commande = id_commande;
        this.id_produit = id_produit;
        this.nomProd = nomProd;
    }

  
    
    
    public int getId_ligne() {
        return id_ligne;
    }

    public void setId_ligne(int id_ligne) {
        this.id_ligne = id_ligne;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public LigneCommande(int quantite, int id_commande, int id_produit) {
        this.quantite = quantite;
        this.id_commande = id_commande;
        this.id_produit = id_produit;
    }

    public LigneCommande(int id_ligne, int quantite, int id_commande, int id_produit, int id_user, String nomProd) {
        this.id_ligne = id_ligne;
        this.quantite = quantite;
        this.id_commande = id_commande;
        this.id_produit = id_produit;
        this.nomProd = nomProd;
    }
    
    
}
