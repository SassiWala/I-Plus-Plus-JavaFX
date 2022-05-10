/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

/**
 *
 * @author sahra
 */
public class categorie {
   private  int id_cat_actualite ;
   private String nom_cat_actualite;
   

   public categorie(){
   
   }
    public categorie(int id_cat_actualite, String nom_cat_actualite) {
        this.id_cat_actualite = id_cat_actualite;
        this.nom_cat_actualite = nom_cat_actualite;
       
    }

    public categorie(String nom_cat_actualite) {
        this.nom_cat_actualite = nom_cat_actualite;
       
    }

    public int getId_cat_actualite() {
        return id_cat_actualite;
    }

    public void setId_cat_actualite(int id_cat_actualite) {
        this.id_cat_actualite = id_cat_actualite;
    }

    public String getNom_cat_actualite() {
        return nom_cat_actualite;
    }

    public categorie(int id_cat_actualite) {
        this.id_cat_actualite = id_cat_actualite;
    }

    public void setNom_cat_actualite(String nom_cat_actualite) {
        this.nom_cat_actualite = nom_cat_actualite;
    }

   
    @Override
    public String toString() {
        return "categorie_actualite{" + "id_cat_actualite=" + id_cat_actualite + ", nom_cat_actualite=" + nom_cat_actualite +  '}';
    }
   
}