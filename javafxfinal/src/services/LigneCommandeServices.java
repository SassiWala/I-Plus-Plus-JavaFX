/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.LigneCommande;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import utis.MyConnection;
import entities.Commande;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author yassi
 */
public class LigneCommandeServices implements IServices<LigneCommande>{
        Connection cnx = MyConnection.getInstance().getCnx();
    @Override
    public void ajouter(LigneCommande p) {
         PanierServices ps=new PanierServices();
        try {
            String req = "INSERT INTO `ligne_commande` (`quantite`,`produit_id`,`commande_matricule`) VALUES ('" + p.getQuantite()+ "','" + p.getId_produit()+ "','" + p.getId_commande()+ "')";
             Statement st = cnx.createStatement();
            st.executeUpdate(req);
            int q=ps.getQuantiteProd(p.getId_produit())-p.getQuantite();
            String req1 = "UPDATE `prod` SET `quantite` = '" + q+ "' WHERE `prod`.`id` = " +p.getId_produit();
            Statement st1 = cnx.createStatement();
            st1.executeUpdate(req1);
            System.out.println("ligne de commande created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(LigneCommande p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LigneCommande> getAll() {
        List<LigneCommande> list = new ArrayList<>();
        try {
            String req = "Select * from ligne_commande";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                LigneCommande p = new LigneCommande(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 System.out.println(list);
        return list;
    }
    /////////////////////////////////////////////////////////////

}
