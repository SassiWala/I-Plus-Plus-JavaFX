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
import pidev.entities.Reclamation;
import pidev.utils.DataSource;


/**
 *
 * @author admin
 */
public class ServiceReclamation implements IService<Reclamation>{

        Connection cnx= DataSource.getinstance().getCns();

    @Override
    public void ajouter(Reclamation r)  {
            try {
                //request
                String req="INSERT INTO `reclamation` (`typereclamations_id`, `sujet_rec`, `niveau`, `user_id`) VALUES (?,?,?,?)";
                
                
                PreparedStatement pst =cnx.prepareStatement(req);
                pst.setInt(1,r.getTypereclamation_id());
                pst.setString(2,r.getSujet_rec());
                pst.setInt(3,r.getNiveau());
                pst.setInt(4,r.getUser_id());
                
                pst.executeUpdate();
                
                System.out.println("Reclamation ajouter avec Succes");
            } catch (SQLException ex) {
                Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void modifier(Reclamation t)  {
            try {
                //request
                String req="UPDATE `reclamation` SET `niveau`=? WHERE `id`=?";
                
                
                PreparedStatement pst =cnx.prepareStatement(req);
                pst.setInt(1,t.getNiveau());
                pst.setInt(2,t.getId());
                
                pst.executeUpdate();
                System.out.println("Modification terminer avec Succes");
            } catch (SQLException ex) {
                Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void supprimer(int id) {
            try {
                //request
                String req="DELETE FROM `RECLAMATION` WHERE id=?";
                
                
                PreparedStatement pst =cnx.prepareStatement(req);
                pst.setInt(1,id);
                pst.executeUpdate();
                
                
                System.out.println("RECLAMATION Supprimer avec Succes");
            } catch (SQLException ex) {
                Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public List<Reclamation> getAll()  {
                        List<Reclamation> Reclamations = new ArrayList<>();

            try {
                //LIST
                //request
                String req ="SELECT * FROM RECLAMATION";
                
                //insert
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(req);
                while(rs.next())
                {
                    Reclamations.add(new Reclamation(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5)));
                    
                }
                
                return Reclamations;
            } catch (SQLException ex) {
                Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
            }
                            return Reclamations;

    }
    
    public List<Reclamation> trier() throws SQLException {
        //LIST
        List<Reclamation> Reclamations = new ArrayList<>();
        //request 
        String req ="SELECT * FROM `reclamation` ORDER by niveau DESC ;";

            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                Reclamations.add(new Reclamation(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5)));
                
            }
          
        return Reclamations;
    }
    public List<Reclamation> recherche(String rechercher) throws SQLException {
        //LIST
        List<Reclamation> Reclamations = new ArrayList<>();
        //request 
        String req ="SELECT * FROM `reclamation` WHERE id like '%"+rechercher+"%' OR typereclamations_id  LIKE '%"+rechercher+"%' or sujet_rec LIKE '%"+rechercher+"%' or niveau LIKE '%"+rechercher+"%' or user_id LIKE '%"+rechercher+"%' ;";

            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                Reclamations.add(new Reclamation(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5)));
                
            }
          
        return Reclamations;
    }
    public List<Reclamation> afficherPourutilisateur(int id) throws SQLException {
        //LIST
        List<Reclamation> Reclamations = new ArrayList<>();
        //request 
        String req ="SELECT * FROM RECLAMATION where user_id="+id;

            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                Reclamations.add(new Reclamation(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5)));
                
            }
          
        return Reclamations;
    }
    
    
    public int CountN0() throws SQLException {
        int count=0;
        //request 
        String req ="SELECT count(*) FROM RECLAMATION where niveau=0";

            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                count=rs.getInt(1);
            }
                
            
          
        return count;
    }
     public int CountN1() throws SQLException {
        int count=0;
        //request 
        String req ="SELECT count(*) FROM RECLAMATION where niveau=1";

            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                count=rs.getInt(1);
            }
                
            
          
        return count;
    }
    
    
     public int CountN2() throws SQLException {
        int count=0;
        //request 
        String req ="SELECT count(*) FROM RECLAMATION where niveau=2";

            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                count=rs.getInt(1);
            }
                
            
          
        return count;
    }
    
    
    
    
    
}
