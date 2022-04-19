/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commande;
import java.sql.Connection;
import utils.MyConnection;
import entities.LigneCommande;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author yassi
 */
public class LigneCommandeServices implements Iservices<LigneCommande>{
    Connection cnx = MyConnection.getInstance().getCnx();

     @Override
    public void ajouter(LigneCommande p) {
         try {
            String req = "INSERT INTO `ligne_commande` (`quantite`,`produit_id`,`commande_matricule`) VALUES ('" + p.getQuantite()+ "','" + p.getId_produit()+ "','" + p.getId_commande()+ "')";
             Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("ligne de commande created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
                    String req = "DELETE FROM `ligne_commande` WHERE id = " + id;
                    Statement st = cnx.createStatement();
                    st.executeUpdate(req);
                    System.out.println("ligne deleted !");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }    
    }

    @Override
    public void modifier(LigneCommande p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LigneCommande> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
