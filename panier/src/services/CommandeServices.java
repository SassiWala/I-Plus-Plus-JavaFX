/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Commande;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;
/**
 *
 * @author yassi
 */
public class CommandeServices implements Iservices<Commande>{
        Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void ajouter(Commande p) {
         try {
            String req = "INSERT INTO `commande` (`montant_commande`,'matricule') VALUES ('" + p.getMontantCommande()+ "','" + p.getMatricule()+ "')";
             Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("commande created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
try {
            String req = "DELETE FROM `commande` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("commande deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

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
                Commande p = new Commande(rs.getInt(1), rs.getDouble(2), rs.getDate(3),rs.getInt(4));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 System.out.println(list);
        return list;
    }
}
