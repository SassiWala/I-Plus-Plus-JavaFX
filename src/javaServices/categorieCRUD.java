/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaentity.actualite;
import javaentity.categorie;
import javatools.myconnection;
/**
 *
 * @author sahra
 */
public class categorieCRUD {
    
        public void ajoutCategorie2(categorie c){
        try {
            String requete2 = "INSERT INTO categorie_actualite(nom_cat_actualite) "
                    + "VALUES (?) ";
            PreparedStatement pst = new myconnection().getConnection().prepareStatement(requete2);
            pst.setString(1, c.getNom_cat_actualite());
            System.out.println(c.getNom_cat_actualite());
            pst.executeUpdate();
            System.out.println("categorie ajoute avec succes");
        } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
        }
    }
    public void DeleteCategorie(int ca){
          try {
              String req = "DELETE from categorie_actualite  WHERE id_cat_actualite =" +ca+ " ";
               Statement st = new myconnection().getConnection().createStatement();
              st.executeUpdate(req);
              System.out.println("done");
          } catch (SQLException ex) {
              System.err.println(ex.getMessage()); 

          }
          }
        
        public void UpdateCategorie(categorie c,int idc)
        { String req ="UPDATE categorie_actualite set nom_cat_actualite=? WHERE id_cat_actualite =" +idc+ " ";
        try {
           PreparedStatement pst = new myconnection().getConnection().prepareStatement(req);
            pst.setString(1, c.getNom_cat_actualite());
              pst.executeUpdate();
              System.out.println("categorie modifié");
            
        } catch (SQLException ex) {
            
              System.err.println(ex.getMessage()); 
            
        } 
        }
    public List<categorie> afficherCategorie(){
                    List<categorie> myList =new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM categorie_actualite";
            Statement st =new myconnection().getConnection().createStatement();
           ResultSet rs = st.executeQuery(requete3);
           while (rs.next()){
           categorie c = new categorie();
           c.setId_cat_actualite(rs.getInt("id_cat_actualite"));
           c.setNom_cat_actualite(rs.getString("nom_cat_actualite"));
          
           myList.add(c);

           
           }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); 
        }
        return myList;
    }
 public categorie findCategorie(String nom){
                    categorie Cat =new categorie();
        try {
            String requete3 = "SELECT * FROM categorie_actualite where nom_cat_actualite='"+nom+"'";
            Statement st =new myconnection().getConnection().createStatement();
           ResultSet rs = st.executeQuery(requete3);
           while (rs.next()){
            Cat.setId_cat_actualite(rs.getInt("id_cat_actualite"));  

           Cat.setNom_cat_actualite(rs.getString("nom_cat_actualite"));  
               System.out.println(Cat.toString());
           }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); 
        }
        return Cat;
    }
}
