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
import pidev.entities.Typereclamation;
import pidev.utils.DataSource;


/**
 *
 * @author admin
 */
public class ServiceTypereclamation  implements IService<Typereclamation>{

        Connection cnx= DataSource.getinstance().getCns();

    @Override
    public void ajouter(Typereclamation t)  {
            try {
                //request
                String req="INSERT INTO `TYPERECLAMATIONS`(`niveau`,`image`) VALUES (?,?)";
                
                
                PreparedStatement pst =cnx.prepareStatement(req);
                pst.setString(1,t.getNiveau());
                pst.setString(2,t.getImage());
                
                pst.executeUpdate();
                System.out.println("Typereclamation ajouter avec Succes");
            } catch (SQLException ex) {
                Logger.getLogger(ServiceTypereclamation.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void modifier(Typereclamation t) {
            try {
                //request
                String req="UPDATE `TYPERECLAMATIONS` SET `niveau`=? , `image`=? WHERE `id`=?";
                
                
                PreparedStatement pst =cnx.prepareStatement(req);
                pst.setString(1,t.getNiveau());
                pst.setInt(3,t.getId());
                pst.setString(2,t.getImage());
                
                pst.executeUpdate();
                System.out.println("Modification terminer avec Succes");
            } catch (SQLException ex) {
                Logger.getLogger(ServiceTypereclamation.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void supprimer(int id)  {
            try {
                //request
                String req="DELETE FROM `TYPERECLAMATIONS` WHERE id=?";
                
                
                PreparedStatement pst =cnx.prepareStatement(req);
                pst.setInt(1,id);
                pst.executeUpdate();
                
                
                System.out.println("TYPERECLAMATIONS Supprimer avec Succes");
            } catch (SQLException ex) {
                Logger.getLogger(ServiceTypereclamation.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public List<Typereclamation> getAll()  {
        //LIST
                List<Typereclamation> Typesreclamations = new ArrayList<>();
            try {
                
                //request
                String req ="SELECT * FROM TYPERECLAMATIONS";
                
                //insert
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(req);
                while(rs.next())
                {
                    Typesreclamations.add(new Typereclamation(rs.getInt(1),rs.getString(2),rs.getString(3)));
                    
                }
                
                return Typesreclamations;
            } catch (SQLException ex) {
                Logger.getLogger(ServiceTypereclamation.class.getName()).log(Level.SEVERE, null, ex);
            }
                            return Typesreclamations;

    }
    
     public Typereclamation getTypereclamation(int ID_typereclamation) throws SQLException {
     //LIST
        Typereclamation t = new Typereclamation();
        //request 
        String req ="SELECT * FROM TYPERECLAMATIONS where id="+ID_typereclamation;
        
            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                Typereclamation T=new Typereclamation(rs.getInt(1),rs.getString(2),rs.getString(3));
                t=T;
            }
            
            
        
        return t;   
    }
     
      public Typereclamation getID(String niveau) throws SQLException {
     //LIST
        Typereclamation t = new Typereclamation();
        //request 
        String req ="SELECT * FROM TYPERECLAMATIONS where niveau='"+niveau+"';";
        
            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                Typereclamation T=new Typereclamation(rs.getInt(1),rs.getString(2),rs.getString(3));
                t=T;
            }
            
            
        return t;   
    }
     
     public Typereclamation getAlltype() throws SQLException {
     //LIST
        Typereclamation t = new Typereclamation();
        //request 
        String req ="SELECT * FROM TYPERECLAMATIONS ;";
        
            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                Typereclamation T=new Typereclamation(rs.getInt(1),rs.getString(2),rs.getString(3));
                t=T;
            }
            
            
        
        return t;   
    }
    
}
