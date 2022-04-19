/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONArray;
import pidev.entities.Admin;
import pidev.entities.User;
import pidev.utils.DataSource;

/**
 *
 * @author 21695
 */
public class ServiceUser implements IService<User> {
    
    Connection cnx = DataSource.getinstance().getCns();
    
    @Override
    public void ajouter(User p) {
        
        try {
            String req = "";
            JSONArray roles = new JSONArray();
            if (p instanceof Admin) {
                roles.put("ROLE_ADMIN");
                req = "INSERT INTO `user`(`nom`, `prenom`, `email`, `password`,`num_tel`, `roles`) VALUES "
                        + "('" + p.getNom() + "',"
                        + "'" + p.getPrenom() + "',"
                        + "'" + p.getEmail() + "',"
                        + "'" + this.crypter_password(p.getPassword()) + "',"
                        + "'" + p.getNumTel() + "',"
                        + "'" + roles + "'"
                        + ")";
            } else {
                roles.put("ROLE_USER");
                req = "INSERT INTO `user`(`nom`, `prenom`, `email`, `password`,`num_tel`, `roles`) VALUES "
                        + "('" + p.getNom() + "',"
                        + "'" + p.getPrenom() + "',"
                        + "'" + p.getEmail() + "',"
                        + "'" + this.crypter_password(p.getPassword()) + "',"
                        + "'" + p.getNumTel() + "',"
                        + "'" + roles + "'"
                        + ")";
            }
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
    public void modifier(User p) {
        String req = "UPDATE `user` SET `id_user`='" + p.getId()
                + "',`nom`='" + p.getNom() + "',`prenom`='" + p.getPrenom()
                + "',`adresse`='" + p.getAdresse()
                + "',`code_postale`='" + p.getCodePostale()
                + "',`num_tel`='" + p.getNumTel()
                + "'WHERE `user`.`id_user` =" + p.getId() + "";
        
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("user updated");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void updateRole(int id, String role) {
        String req = "UPDATE `user` SET `id_user`='" + id
                + "',`roles`='" + role
                + "'WHERE `user`.`id_user` =" + id + "";
        
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("user updated");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public User login(String email, String password) {
        String req = "SELECT * FROM `user` WHERE `email`='" + email + "' and `password`='" + this.crypter_password(password) + "'";
        User p2 = new User();
        
        try {
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                p2.setId(rst.getInt(1));
                p2.setNom(rst.getString(2));
                p2.setPrenom(rst.getString(3));
                p2.setEmail(rst.getString(4));
                p2.setPassword(rst.getString(5));
                p2.setAdresse(rst.getString(6));
                p2.setCodePostale(rst.getInt(7));
                p2.setNumTel(rst.getInt(8));
                String role = rst.getString(9);
                p2.setRoles(role);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(p2);
        return p2;
        
    }
    
    public User getUser(String id) {
        String req = "SELECT * FROM `user` WHERE `id_user`='" + id + "'";
        User p2 = new User();
        
        try {
            Statement stm = cnx.createStatement();
            
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                p2.setId(rst.getInt(1));
                p2.setNom(rst.getString(2));
                p2.setPrenom(rst.getString(3));
                p2.setEmail(rst.getString(4));
                p2.setPassword(rst.getString(5));
                p2.setAdresse(rst.getString(6));
                p2.setCodePostale(rst.getInt(7));
                p2.setNumTel(rst.getInt(8));
                String role = rst.getString(9);
                p2.setRoles(role);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(p2);
        return p2;
        
    }
    
    public void modifier_password(String id, String password) {
        
        Statement st;
        try {
            st = cnx.createStatement();
            
            String query = "UPDATE user SET password='" + this.crypter_password(password) + "' WHERE id_user='" + id + "'";
            st.executeUpdate(query);
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public boolean supprimer(int id) {
        try {
            
            Statement stmt = cnx.createStatement();
            stmt.execute("DELETE FROM `user`  WHERE `user`.`id_user` = '" + id + "'");
            System.out.println("utilisateur supprimé avec succés");
            return true;
            
        } catch (SQLException e) {
            System.out.println("Erreur :" + e.getMessage());
            return false;
        }
    }
    
    @Override
    public List<User> afficher() {
        List<User> users = new ArrayList<>();
        
        try {
            Statement stm = cnx.createStatement();
            String query = "select * from `user`";
            ResultSet rst = stm.executeQuery(query);
            while (rst.next()) {
                User p2 = new User();
                p2.setId(rst.getInt(1));
                p2.setNom(rst.getString(2));
                p2.setPrenom(rst.getString(3));
                p2.setEmail(rst.getString(4));
                p2.setPassword(rst.getString(5));
                p2.setAdresse(rst.getString(6));
                p2.setCodePostale(rst.getInt(7));
                p2.setNumTel(rst.getInt(8));
                String role = rst.getString(9);
                p2.setRoles(role);
                if (p2.getRoles().contains("ROLE_USER")) {
                    users.add(p2);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(users);
        return users;
        
    }
    public List<User> afficherAdmin() {
        List<User> users = new ArrayList<>();
        
        try {
            Statement stm = cnx.createStatement();
            String query = "select * from `user`";
            ResultSet rst = stm.executeQuery(query);
            while (rst.next()) {
                User p2 = new User();
                p2.setId(rst.getInt(1));
                p2.setNom(rst.getString(2));
                p2.setPrenom(rst.getString(3));
                p2.setEmail(rst.getString(4));
                p2.setPassword(rst.getString(5));
                p2.setAdresse(rst.getString(6));
                p2.setCodePostale(rst.getInt(7));
                p2.setNumTel(rst.getInt(8));
                String role = rst.getString(9);
                p2.setRoles(role);
                if (p2.getRoles().contains("ROLE_ADMIN")) {
                    users.add(p2);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(users);
        return users;
        
    }
    
    public void trierUsersParNom() throws SQLException {
        
        List<User> users = afficher();
        //  Collections.sort(users, new ComparerParNom());
        users.sort((User o1, User o2) -> {
            return o1.getNom().compareTo(o2.getNom());
        });
        System.out.println(users);
    }
    
    public List<User> rechercherUser(String nom) throws SQLException {
        List<User> users = afficher();
        
        List<User> results = new ArrayList<>();
        
        users.stream()
                .filter(t -> t.getNom().equals(nom))
                .forEach(t -> results.add(t));
        System.out.println("results" + results);
        
        return results;
    }
     public List<User> rechercherAdmin(String nom) throws SQLException {
        List<User> users = afficherAdmin();
        
        List<User> results = new ArrayList<>();
        
        users.stream()
                .filter(t -> t.getNom().equals(nom))
                .forEach(t -> results.add(t));
        System.out.println("results" + results);
        
        return results;
    }
    
    public static boolean patternMatches(String emailAddress) {
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
    
    public boolean whenMatchesDigitsNumber_thenCorrect(String tel) {
        return Pattern.compile("^\\d{8}$").matcher(tel).matches();
    }
    
    public boolean whenMatchesCode_thenCorrect(String code) {
        return Pattern.compile("^\\d{4}$").matcher(code).matches();
    }

    public boolean whenMatchesPassword_thenCorrect(String password) {
        return Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$").matcher(password).matches();
    }
    
    public String crypter_password(String password) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
            
        } catch (Exception e) {
        }

        //   return hashValue;
        return hashValue;
    }
    
    public boolean email_verifier(String email) throws SQLException {
        Statement stm = null;
        ResultSet rst = null;
        
        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE email='" + email + "'";
            rst = stm.executeQuery(query);
            if (rst.next()) {
                return true;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        
        return false;
        
    }
}
