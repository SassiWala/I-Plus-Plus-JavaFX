/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author yassi
 */
public class LigneCommande {
    private int id_ligne;
    private Prod produit;
    private int quantite;
    private Commande commande;
    
    private int id_commande;
    private int id_produit;

    @Override
    public String toString() {
        return "LigneCommande{" + "id_ligne=" + id_ligne + ", produit=" + produit + ", quantite=" + quantite + ", commande=" + commande + '}';
    }
    
    

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
    
    

    public int getId_ligne() {
        return id_ligne;
    }

    public void setId_ligne(int id_ligne) {
        this.id_ligne = id_ligne;
    }

    public Prod getProduit() {
        return produit;
    }
    public int getid_Produit() {
        return produit.getId();
    }

    public void setProduit(Prod produit) {
        this.produit = produit;
    }



    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public LigneCommande() {
    }

    public LigneCommande(Prod produit, int quantite,Commande commande) {
        this.produit = produit;
        this.quantite = quantite;
        this.commande = commande;
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
    

   
    
    
}
