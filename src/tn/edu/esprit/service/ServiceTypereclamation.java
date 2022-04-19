/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.edu.esprit.model.Reclamation;
import tn.edu.esprit.model.Typereclamation;
import tn.edu.esprit.util.MaConnexion;

/**
 *
 * @author admin
 */
public class ServiceTypereclamation  implements Iservices<Typereclamation>{

        Connection cnx= MaConnexion.getInstance().getCnx();

    @Override
    public void ajouter(Typereclamation t) throws SQLException {
        //request 
        String req="INSERT INTO `TYPERECLAMATIONS`(`niveau`) VALUES (?)";

        
            PreparedStatement pst =cnx.prepareStatement(req);
            pst.setString(1,t.getNiveau());
            
            pst.executeUpdate();
            System.out.println("Typereclamation ajouter avec Succes");
    }

    @Override
    public void modifier(Typereclamation t) throws SQLException {
        //request 
        String req="UPDATE `TYPERECLAMATIONS` SET `niveau`=? WHERE `id`=?";

        
            PreparedStatement pst =cnx.prepareStatement(req);
            pst.setString(1,t.getNiveau());
            pst.setInt(2,t.getId());

            pst.executeUpdate();
            System.out.println("Modification terminer avec Succes");
    }

    @Override
    public void supprimer(int id) throws SQLException {
        //request 
        String req="DELETE FROM `TYPERECLAMATIONS` WHERE id=?";

        
            PreparedStatement pst =cnx.prepareStatement(req);
            pst.setInt(1,id);            
            pst.executeUpdate();
            
        
            System.out.println("TYPERECLAMATIONS Supprimer avec Succes");
    }

    @Override
    public List<Typereclamation> afficher() throws SQLException {
        //LIST
        List<Typereclamation> Typesreclamations = new ArrayList<>();
        //request 
        String req ="SELECT * FROM TYPERECLAMATIONS";

            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                Typesreclamations.add(new Typereclamation(rs.getInt(1),rs.getString(2)));
                
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
                Typereclamation T=new Typereclamation(rs.getInt(1),rs.getString(2));
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
                Typereclamation T=new Typereclamation(rs.getInt(1),rs.getString(2));
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
                Typereclamation T=new Typereclamation(rs.getInt(1),rs.getString(2));
                t=T;
            }
            
            
        
        return t;   
    }
    
}
