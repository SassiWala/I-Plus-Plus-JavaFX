/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Commande;
import entities.Prod;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import utils.MyConnection;
/**
 *
 * @author yassi
 */
public class ProdServices implements Iservices<Prod>{
    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void ajouter(Prod p) {
         try {
            String req = "INSERT INTO `prod` ( `nomProd`, `quantite`, `prix_prod`) VALUES ( '"+p.getNomProd()+"', '"+p.getQuantite()+"', '"+p.getPrixProd()+"')";
             Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("produit created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Prod p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prod> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
