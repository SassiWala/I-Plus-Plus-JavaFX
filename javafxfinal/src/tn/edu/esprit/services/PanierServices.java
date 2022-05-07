/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import tn.edu.esprit.entities.Panier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utis.MyConnection;
import tn.edu.esprit.entities.Commande;
import tn.edu.esprit.entities.LigneCommande;
import tn.edu.esprit.entities.Prod;
/**
 *
 * @author yassi
 */
public class PanierServices implements IServices<Panier>{
    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void ajouter(Panier p) {
 
        try {
            String req = "INSERT INTO `panier` (`produit_id`,`nomProd`,`id_user`,`prixUnitaire`,`stock`) VALUES ('" + p.getProduit_id()+ "','" + getNomProduit(p.getProduit_id())+ "','" + p.getUser_id()+ "','" + getPrixProduit(p.getProduit_id())+ "','" + getQuantiteProd(p.getProduit_id())+ "')";
             Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("produit passé created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
        
    }

    @Override
    public void supprimer(int id) {
 try {
            String req = "DELETE FROM `panier` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Panier deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Panier p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Panier> getAll() {
List<Panier> list = new ArrayList<>();
        try {
            String req = "Select * from panier";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Panier p = new Panier( rs.getInt(1),rs.getInt(2), rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
System.out.println(list);
        return list;
    }
    ///////////////////////////////////////////////////////////////////////////
    public String getNomProduit(int id){
         String nom="";
         try {
            String req = "SELECT * FROM `prod`  WHERE `prod`.`id` =' " +id+"'";
            Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(req);
            while(rs.next()){
           nom =rs.getString("nom_prod");}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         System.out.println(nom);
         return nom ;
     }
     public void modifier2(int id, int q) {
 try {
            String req = "UPDATE `panier` SET `quantite` = '" + q+ "' WHERE `panier`.`id` = " +id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("quantité updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }
      public void vider() {
 try {
            String req = "DELETE FROM `panier`  " ;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Panier vide !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
     }
       public ObservableList<Panier> getPanier() {
         ObservableList<Panier>panierlist=FXCollections.observableArrayList();
        try {
            String req = "Select * from panier";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Panier p = new Panier( rs.getInt(1),rs.getInt(2), rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getFloat(6),rs.getInt(7));
                panierlist.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return panierlist; 
       }
       
        public Float getPrixProduit(int id){
         float n=0;
         try {
            String req = "SELECT * FROM `prod`  WHERE `prod`.`id` =' " +id+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
           n =rs.getFloat("prix_prod");}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         System.out.println(n);
         return n ;
     }
        public Panier getProduitparNom(String nom){
         Panier p = new Panier();
         try {
            String req = "SELECT * FROM `panier`  WHERE `panier`.`NomProd` LIKE ' " +nom+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
          p= new Panier(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getInt(5));
           
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         System.out.println(p.toString());
         return p ;
         
     }
     public List<Panier> recherche(String nom) {
        List<Panier> list = new ArrayList<>();

        try {
            nom = "'%" + nom + "%'";
            String requete = "select * from panier WHERE NomProd LIKE " + nom;
            PreparedStatement pst = cnx.prepareStatement(requete);
            cnx.prepareStatement(requete).executeQuery();
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Panier(rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getInt(5) ));
                System.out.println();
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
 System.out.println(list.get(0).toString());
        return list;
    }
        public List<Panier> rechercherPanier(String nom) throws SQLException {
        List<Panier> users = getAll();

        List<Panier> results = new ArrayList<>();

        users.stream()
                .filter(t -> t.getNomProd().equals(nom))
                .forEach(t -> results.add(t));
        System.out.println("results" + results);

        return results;
    }
         public Panier getLastligne() {
         ObservableList<Panier>cmdlist=FXCollections.observableArrayList();
        try {
            String req = "Select * from panier ORDER BY `panier`.`id` DESC ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                
                Panier p = new Panier(rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getInt(5));
                cmdlist.add(p);
            }//System.out.println(cmdlist.toString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }System.out.println(cmdlist.get(0).toString());   
        return cmdlist.get(0);
    }
        public Panier getLigne(int id) {
            Panier p=new Panier();
        try {
            String req = "SELECT * FROM `panier`  WHERE `panier`.`id` =' " +id+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                p.setId(rs.getInt(1));
                p.setQuantite(rs.getInt(3));
                p.setProduit_id(rs.getInt(2));
                p.setNomProd(rs.getString(4));
                p.setPrixUnitaire(rs.getFloat(6));
                p.setUser_id(rs.getInt(5));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
        
    }
         public float getTotal(){
            float total=0;
             List<Panier> list = new ArrayList<>();
             
            PanierServices ps=new PanierServices();
            list =ps.getPanier();
            ListIterator<Panier> iterator=list.listIterator();
            //System.out.println(list);
            while (iterator.hasNext()) { 
               Panier p =iterator.next();
               System.out.println(p.toString());
               total=total+(p.getPrixUnitaire()*p.getQuantite());
                
            }//System.out.println(total);
            return total; 
        }
        public void ValiderCommande() {
            Commande cmd =new Commande();
            CommandeServices cs = new CommandeServices();
            LigneCommandeServices lcs=new LigneCommandeServices();
            PanierServices ps=new PanierServices();
            cmd.setMontantCommande(ps.getTotal());
            cmd.setUser_id(ps.getLastligne().getUser_id());
            List<Panier> list = new ArrayList<>();
            list =ps.getPanier();
            ListIterator<Panier> iterator=list.listIterator();
            //System.out.println(list);
            cs.ajouter(cmd);
            while (iterator.hasNext()) {
            LigneCommande lc=new LigneCommande();
            Panier p =iterator.next();
            lc.setId_commande(cs.getLastCmd().getId_commande());
            lc.setId_produit(p.getProduit_id());
            lc.setQuantite(p.getQuantite());
            lcs.ajouter(lc);
            } 
            
            ps.vider();
            System.out.println("succes");
        }
    public int getQuantiteProd(int id){
         Prod p=new Prod();
        try {
            String req = "SELECT * FROM `prod`  WHERE `prod`.`id` =' " +id+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                p.setId(rs.getInt(1));
                p.setQuantite(rs.getInt(3));
                p.setImgProd(rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return p.getQuantite() ;
    }
      public String getEmailUser(int id){
         String mail="";
         try {
            String req = "SELECT * FROM `user`  WHERE `user`.`id_user` =' " +id+"'";
            Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(req);
            while(rs.next()){
           mail =rs.getString("email");}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         System.out.println(mail);
         return mail ;
     }
}
