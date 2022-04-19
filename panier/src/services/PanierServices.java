/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Panier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author yassi
 */
public class PanierServices implements Iservices<Panier>{
            Connection cnx = MyConnection.getInstance().getCnx();


    @Override
    public void ajouter(Panier p) {
        
        try {
            String req = "INSERT INTO `panier` (`produit_id`,`nomProd`) VALUES ('" + p.getProduit_id()+ "','" + getNomProduit(p.getProduit_id())+ "')";
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
        }    }

    

    @Override
    public List<Panier> getAll() {
List<Panier> list = new ArrayList<>();
        try {
            String req = "Select * from panier";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Panier p = new Panier( rs.getInt(2), rs.getInt(3),rs.getString(4));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
System.out.println(list);
        return list;
        
    }

    @Override
    public void modifier(Panier p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   /////////////////////////////////////////////////////////////////////////////////////
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
                Panier p = new Panier( rs.getInt(2), rs.getInt(3),rs.getString(4));
                panierlist.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
System.out.println(panierlist);
        return panierlist;
        
    }
     public String getNomProduit(int id){
         String nom="";
         try {
            String req = "SELECT * FROM `prod`  WHERE `prod`.`id` =' " +id+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
           nom =rs.getString("nomProd");}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         System.out.println(nom);
         return nom ;
     }
     public Panier getProduitparNom(String nom){
         Panier p = new Panier();
         try {
            String req = "SELECT * FROM `panier`  WHERE `panier`.`NomProd` LIKE ' " +nom+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
          p= new Panier(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4));
           
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
                list.add(new Panier(rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getString(4) ));
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
        
        public Panier getLigne(int id) {
Panier p=new Panier();
        try {
            String req = "SELECT * FROM `panier`  WHERE `panier`.`id` =' " +id+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                p.setId(rs.getInt(1));
                p.setQuantite(rs.getInt(3));
                p.setProduit_id(2);
                p.setNomProd(rs.getString(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
        
    }
        
}
