/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;
import tn.edu.esprit.entities.Commande;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utis.MyConnection;
/**
 *
 * @author yassi
 */
public class CommandeServices implements IServices<Commande>{
Connection cnx = MyConnection.getInstance().getCnx();
    @Override
    public void ajouter(Commande p) {
 try {
            String req = "INSERT INTO `commande` (`montantCommande`,`nomUser`,`user_id` ) VALUES ('" + p.getMontantCommande()+ "','" + getNomUser(p.getUser_id())+ "','" + p.getUser_id()+ "')";
             Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("commande created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Commande p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commande> getAll() {
            List<Commande> list = new ArrayList<>();
        try {
            String req = "Select * from commande";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Commande p = new Commande(rs.getInt(1), rs.getFloat(2), rs.getDate(3),rs.getInt(4),rs.getString(5));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 System.out.println(list);
        return list;
    }
    ////////////////////////////////////////////////////////////////
         public Commande getLastCmd() {
         ObservableList<Commande>cmdlist=FXCollections.observableArrayList();
        try {
            String req = "Select * from commande ORDER BY `commande`.`id` DESC ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                
                Commande p = new Commande(rs.getInt(1), rs.getFloat(2),rs.getDate(3),rs.getInt(4));
                cmdlist.add(p);
            }//System.out.println(cmdlist.toString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
//System.out.println(cmdlist.get(0).toString());
        return cmdlist.get(0);
    }
         public String getNomUser(int id){
         String nom="";
         try {
            String req = "SELECT * FROM `user`  WHERE `user`.`id_user` =' " +id+"'";
            Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(req);
            while(rs.next()){
           nom =rs.getString("nom");}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         //System.out.println(nom);
         return nom ;
     }
         public List<Commande> OrderByAsc(){
          
          ObservableList<Commande> commandes=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM `commande` ORDER BY `commande`.`MontantCommande` ASC ";
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            

            while(rs.next()){
                
              
                Commande p = new Commande();
                
                
                p.setId_commande(rs.getInt(1));
                p.setMontantCommande(rs.getFloat(2));
                p.setDateCommande(rs.getDate(3));
                p.setUser_id(rs.getInt(4));
                p.setNomUser(rs.getString(5));
               
                commandes.add(p);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
             System.err.println(commandes);
    return commandes;
      }     

      //------------------------------------------------------------------------
      
         public List<Commande> OrderByDesc(){
          
          ObservableList<Commande> commandes=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM `commande` ORDER BY `commande`.`MontantCommande` DESC ";
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            

            while(rs.next()){
                
              
                Commande p = new Commande();
                
                
                p.setId_commande(rs.getInt(1));
                p.setMontantCommande(rs.getFloat(2));
                p.setDateCommande(rs.getDate(3));
                p.setUser_id(rs.getInt(4));
                p.setNomUser(rs.getString(5));
               
                commandes.add(p);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }  
        System.err.println(commandes);
    return commandes;
      }        
                 public List<Commande> rechercherPanier(String nom) throws SQLException {
        List<Commande> cmd = getAll();

        List<Commande> results = new ArrayList<>();

        cmd.stream()
                .filter(t -> t.getNomUser().equals(nom))
                .filter(t -> String.valueOf(t.getDateCommande()).equals(nom))
                .filter(t -> String.valueOf(t.getMontantCommande()).equals(nom))
                .forEach(t -> results.add(t));
        System.out.println("results" + results);

        return results;
    }
                
}
