/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entities.actualite;
import pidev.entities.categorie;
import pidev.utils.DataSource;

/**
 *
 * @author sahra
 */
public class actualiteCRUD {
           Connection cnx= DataSource.getinstance().getCns();

    public void ajoutactualite2(actualite a){
        try {
            String requete2 = "INSERT INTO actualite(titre_actualite,description,etendu,image_act,categorie) "
                    + "VALUES (?,?,?,?,?) ";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, a.getTitre_actualite());
            pst.setString(2, a.getDescription());
            pst.setString(3, a.getEtendu());
            pst.setString(4, a.getImage_act());
            pst.setInt(5, a.getCategorie().getId_cat_actualite());
            System.out.println("Cat"+a.getCategorie().getId_cat_actualite());
            pst.executeUpdate();
            System.out.println("actualite ajoute avec succes");
        } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
        }
    }
    public void DeleteActualite(int ac){
          try {
              String req = "DELETE from actualite  WHERE id_actualite =" +ac+ " ";
               Statement st = cnx.createStatement();
              st.executeUpdate(req);
              System.out.println("done");
          } catch (SQLException ex) {
              System.err.println(ex.getMessage()); 

          }
          }
        
        public void UpdateActualite(actualite a,int ida)
        { String req ="UPDATE actualite set titre_actualite=? , description=? , etendu=?,image_act=?, categorie=? WHERE id_actualite =" +ida+ " ";
        try {
           PreparedStatement pst =cnx.prepareStatement(req);
            pst.setString(1, a.getTitre_actualite());
            pst.setString(2, a.getDescription());
            pst.setString(3, a.getEtendu());
            pst.setString(4, a.getImage_act());
            pst.setInt(5, a.getCategorie().getId_cat_actualite());
           
              pst.executeUpdate();
              System.out.println("actualite modifié");
            
        } catch (SQLException ex) {
            
              System.err.println(ex.getMessage()); 
            
        } 
        }
    public List<actualite> afficheractualite(){
                    List<actualite> myList =new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM actualite";
            Statement st =cnx.createStatement();
           ResultSet rs = st.executeQuery(requete3);
           while (rs.next()){
           actualite a = new actualite();
           a.setId_actualite(rs.getInt(1));
           a.setTitre_actualite(rs.getString("titre_actualite"));
           a.setDescription(rs.getString("description"));
           a.setEtendu(rs.getString("etendu"));
           a.setImage_act(rs.getString("image_act"));
           a.setRating_act(rs.getInt(8));
           a.setVu(rs.getInt("vu"));
           myList.add(a);

           
           }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); 
        }
        return myList;
    }
    
    public ObservableList<actualite> Searcho(String search ){
         List<actualite> aclist = new ArrayList();
        aclist = afficheractualite();
        
         ObservableList<actualite> list = FXCollections.observableArrayList(aclist.stream().filter(a->a.getTitre_actualite().startsWith(search)).collect(Collectors.toList()));

     return list;
    
}
     public ObservableList<actualite> tri(){
         List<actualite> aclist = new ArrayList();
        aclist = afficheractualite();
        
         ObservableList<actualite> list = FXCollections.observableArrayList(aclist.stream().sorted().collect(Collectors.toList()));

     return list;
    
}
      public ObservableList<actualite> filtre(categorie categorie ){
         List<actualite> aclist = new ArrayList();
        aclist = afficheractualite();
        
         ObservableList<actualite> list = FXCollections.observableArrayList(aclist.stream().filter(a->a.getCategorie().equals(categorie)).collect(Collectors.toList()));

     return list;
    

}
public void Vu(actualite a)
        { String req ="UPDATE actualite set vu=?  WHERE id_actualite =" +a.getId_actualite()+ " ";
        try {
           PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, a.getVu()+1);
          
           
              pst.executeUpdate();
              System.out.println("actualite modifié");
            
        } catch (SQLException ex) {
            
              System.err.println(ex.getMessage()); 
            
        } 
        }

      public List<actualite> affichfront (){
                    List<actualite> myList =new ArrayList<>();
        try {
            String requete3 = "SELECT id_actualite,titre_actualite,description,Date_act,rating_act,vu,categorie FROM actualite";
            Statement st =cnx.createStatement();
           ResultSet rs = st.executeQuery(requete3);
           while (rs.next()){
           actualite a = new actualite();
           a.setRating_act(rs.getInt(1));
           a.setTitre_actualite(rs.getString("titre_actualite"));
           a.setDescription(rs.getString("description"));
           a.setDate_act(rs.getDate("Date_act"));
           a.setRating_act(rs.getInt("rating_act"));
           a.setVu(rs.getInt("vu"));
           categorie c = new categorie(rs.getString("categorie"));
           a.setCategorie(c);
           myList.add(a);

           
           }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); 
        }
        return myList;
    }
         public actualite afficheractualite3(String t){
         
                   actualite a = new actualite() ;
        try {
            
            String requete3 = "SELECT * FROM actualite WHERE titre_actualite='" +t+ "'";
            System.out.println(requete3);
            Statement st =cnx.createStatement();
           ResultSet rs = st.executeQuery(requete3);
           while (rs.next()){
           a.setId_actualite(rs.getInt(1));
           a.setTitre_actualite(rs.getString("titre_actualite"));
           a.setDescription(rs.getString("description"));
           a.setEtendu(rs.getString("etendu"));
           a.setImage_act(rs.getString("image_act"));
           a.setRating_act(rs.getInt("rating_act"));
            a.setDate_act(rs.getDate("Date_act"));
           a.setVu(rs.getInt("vu"));
      

           
           }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); 
        }
        return a;
    }

}
