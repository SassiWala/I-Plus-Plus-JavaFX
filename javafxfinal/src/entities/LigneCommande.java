/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author yassi
 */
public class LigneCommande {
    private int id_ligne;
    private int quantite;
    private int id_commande;
    private int id_produit;
    private int id_user;

    public LigneCommande(int id_ligne, int quantite, int id_commande, int id_produit) {
        this.id_ligne = id_ligne;
        this.quantite = quantite;
        this.id_commande = id_commande;
        this.id_produit = id_produit;
    }

    public LigneCommande() {
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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
    
    
}
