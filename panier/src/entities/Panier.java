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
        return "Panier{" + "id=" + id + ", produit_id=" + produit_id + ", quantite=" + quantite + ", nomProd=" + nomProd + '}';
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
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
