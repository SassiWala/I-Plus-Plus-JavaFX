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
public class Prod {
    private int Id ;
    private String nom_prod;
    private float prix_prod;
    private int satut_prod;
    private int rate_prod;
    private String description_prod;
    private String img_prod;
private int id_promo_id;
	private int quantite;

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Prod(String nom_prod, float prix_prod, int satut_prod, int rate_prod, String description_prod, String img_prod, int id_promo_id, int quantite) {
        this.nom_prod = nom_prod;
        this.prix_prod = prix_prod;
        this.satut_prod = satut_prod;
        this.rate_prod = rate_prod;
        this.description_prod = description_prod;
        this.img_prod = img_prod;
        this.id_promo_id = id_promo_id;
        this.quantite = quantite;
    }

    public Prod(int Id, String nom_prod, float prix_prod, int satut_prod, int rate_prod, String description_prod, String img_prod, int id_promo_id, int quantite) {
        this.Id = Id;
        this.nom_prod = nom_prod;
        this.prix_prod = prix_prod;
        this.satut_prod = satut_prod;
        this.rate_prod = rate_prod;
        this.description_prod = description_prod;
        this.img_prod = img_prod;
        this.id_promo_id = id_promo_id;
        this.quantite = quantite;
    }
    

    public Prod(String nom_prod, float prix_prod, int satut_prod, int rate_prod, String description_prod, String img_prod, int quantite) {
        this.nom_prod = nom_prod;
        this.prix_prod = prix_prod;
        this.satut_prod = satut_prod;
        this.rate_prod = rate_prod;
        this.description_prod = description_prod;
        this.img_prod = img_prod;
        this.quantite = quantite;
    }


    public int getId_promo_id() {
        return id_promo_id;
    }

    public void setId_promo_id(int id_promo_id) {
        this.id_promo_id = id_promo_id;
    }

    public Prod(int Id, String nom_prod, float prix_prod, int satut_prod, int rate_prod, String description_prod, String img_prod, int id_promo_id) {
        this.Id = Id;
        this.nom_prod = nom_prod;
        this.prix_prod = prix_prod;
        this.satut_prod = satut_prod;
        this.rate_prod = rate_prod;
        this.description_prod = description_prod;
        this.img_prod = img_prod;
        this.id_promo_id = id_promo_id;
    }
    public Prod() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Prod(int Id, String nom_prod, float prix_prod, int satut_prod, int rate_prod, String description_prod, String img_prod) {
        this.Id = Id;
        this.nom_prod = nom_prod;
        this.prix_prod = prix_prod;
        this.satut_prod = satut_prod;
        this.rate_prod = rate_prod;
        this.description_prod = description_prod;
        this.img_prod = img_prod;
    }

    public Prod(String nom_prod, float prix_prod, int satut_prod, int rate_prod, String description_prod, String img_prod) {
        this.nom_prod = nom_prod;
        this.prix_prod = prix_prod;
        this.satut_prod = satut_prod;
        this.rate_prod = rate_prod;
        this.description_prod = description_prod;
        this.img_prod = img_prod;
        
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public float getPrix_prod() {
        return prix_prod;
    }

    public void setPrix_prod(float prix_prod) {
        this.prix_prod = prix_prod;
    }

    public int getSatut_prod() {
        return satut_prod;
    }

    public void setSatut_prod(int satut_prod) {
        this.satut_prod = satut_prod;
    }

    public int getRate_prod() {
        return rate_prod;
    }

    public void setRate_prod(int rate_prod) {
        this.rate_prod = rate_prod;
    }

    public String getDescription_prod() {
        return description_prod;
    }

    public void setDescription_prod(String description_prod) {
        this.description_prod = description_prod;
    }

    public String getImg_prod() {
        return img_prod;
    }

    public void setImg_prod(String img_prod) {
        this.img_prod = img_prod;
    }

    @Override
    public String toString() {
        return "Prod{" + "Id=" + Id + ", nom_prod=" + nom_prod + ", prix_prod=" + prix_prod + ", satut_prod=" + satut_prod + ", rate_prod=" + rate_prod + ", description_prod=" + description_prod + ", img_prod=" + img_prod + ", id_promo_id=" + id_promo_id + ", quantite=" + quantite + '}';
    }

   

    

    public Prod(String nom_prod, float prix_prod, int satut_prod, int rate_prod, String description_prod) {
        this.nom_prod = nom_prod;
        this.prix_prod = prix_prod;
        this.satut_prod = satut_prod;
        this.rate_prod = rate_prod;
        this.description_prod = description_prod;
    }
    
    
}
