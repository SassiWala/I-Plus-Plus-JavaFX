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
import pidev.entities.Promo;
import pidev.utils.DataSource;

/**
 *
 * @author msi
 */
public class ServicePromo implements IService<Promo> {
    Connection cnx = DataSource.getinstance().getCns();

    @Override
    public void ajouter(Promo p) {
        try {
            String req = "INSERT INTO `promo`( `pourcent_promo`, `date_exp`) VALUES ('"+p.getPourcent_promo()+"','"+p.getDate_exp()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Promotion created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `promo` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Promotion deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void modifier(Promo p) {
        try {
            String req = "UPDATE `promo` SET `pourcent_promo` = '" + p.getPourcent_promo() + "', `date_exp` = '" + p.getDate_exp() + "' WHERE `promo`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Promotion updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
     public List<Promo> getAll() {
        List<Promo> list = new ArrayList<>();
        try {
            String req = "SELECT `id`, `pourcent_promo`, `date_exp`  FROM `promo` ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Promo p = new Promo(((int)Float.parseFloat(rs.getString("id"))),((int)Float.parseFloat(rs.getString("pourcent_promo"))),rs.getString("date_exp"));
                list.add(p);
 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
     public int getPromoById(String promo) throws SQLException{
         int id=0;
         String req = "SELECT `id`, `pourcent_promo`, `date_exp`  FROM `promo` where `pourcent_promo`="+promo+"";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
             while(rs.next()){
                 id = (int)Float.parseFloat(rs.getString("id"));
             }
             return id;
     }
}
