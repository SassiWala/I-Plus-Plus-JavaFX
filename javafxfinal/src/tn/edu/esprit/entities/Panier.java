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
public class Panier {
      private int id;
    private int produit_id;
    private int quantite;
    private String nomProd;
    private int user_id;
    private float prixUnitaire;

    public Panier(int id, int produit_id, int quantite, String nomProd, int user_id, float prixUnitaire) {
        this.id = id;
        this.produit_id = produit_id;
        this.quantite = quantite;
        this.nomProd = nomProd;
        this.user_id = user_id;
        this.prixUnitaire = prixUnitaire;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    
    public Panier(int produit_id, int quantite, int user_id) {
        this.produit_id = produit_id;
        this.quantite = quantite;
        this.user_id = user_id;
    }
    
    
    
    public Panier(int id, int produit_id, int quantite, String nomProd) {
        this.id = id;
        this.produit_id = produit_id;
        this.quantite = quantite;
        this.nomProd = nomProd;
    }
    

    public Panier(int produit_id, int quantite, String nomProd) {
        this.produit_id = produit_id;
        this.quantite = quantite;
        this.nomProd = nomProd;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", produit_id=" + produit_id + ", quantite=" + quantite + ", nomProd=" + nomProd + ", user_id=" + user_id + ", prixUnitaire=" + prixUnitaire + '}';
    }

    

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public Panier(int id, int produit_id, int quantite, String nomProd, int user_id) {
        this.id = id;
        this.produit_id = produit_id;
        this.quantite = quantite;
        this.nomProd = nomProd;
        this.user_id = user_id;
    }

    public Panier() {
    }



    public Panier(int produit_id, int quantite) {
        this.produit_id = produit_id;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    

}
