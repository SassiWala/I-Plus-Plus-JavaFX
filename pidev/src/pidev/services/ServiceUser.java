/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import com.sun.mail.smtp.SMTPTransport;
import com.twilio.Twilio;

import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
                req = "INSERT INTO `user`(`nom`, `prenom`, `email`, `password`,`num_tel`,`img`,`roles`) VALUES "
                        + "('" + p.getNom() + "',"
                        + "'" + p.getPrenom() + "',"
                        + "'" + p.getEmail() + "',"
                        + "'" + this.crypter_password(p.getPassword()) + "',"
                        + "'" + p.getNumTel() + "',"
                        + "'" + p.getImg() + "',"
                        + "'" + roles + "'"
                        + ")";
            } else {
                roles.put("ROLE_USER");
                req = "INSERT INTO `user`(`nom`, `prenom`, `email`, `password`,`num_tel`,`img`,`roles`) VALUES "
                        + "('" + p.getNom() + "',"
                        + "'" + p.getPrenom() + "',"
                        + "'" + p.getEmail() + "',"
                        + "'" + this.crypter_password(p.getPassword()) + "',"
                        + "'" + p.getNumTel() + "',"
                        + "'" + p.getImg() + "',"
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
                p2.setDateBan(rst.getDate(12));
                p2.setIsBanned(rst.getBoolean(11));
                p2.setImg(rst.getString(13));
                String role = rst.getString(9);
                p2.setRoles(role);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Loginnn" + p2);
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
                p2.setDateBan(rst.getDate(12));
                p2.setIsBanned(rst.getBoolean(11));
                p2.setImg(rst.getString(13));
                p2.setRoles(role);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("getUserr" + p2);
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
    public void supprimer(int id) {
        try {

            Statement stmt = cnx.createStatement();
            stmt.execute("DELETE FROM `user`  WHERE `user`.`id_user` = '" + id + "'");
            System.out.println("utilisateur supprimé avec succés");
           

        } catch (SQLException e) {
            System.out.println("Erreur :" + e.getMessage());
            
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            Statement stm = cnx.createStatement();
            String query = "select * from `user`";
            ResultSet rst = stm.executeQuery(query);
            while (rst.next()) {
                if (rst.getString(9).contains("ROLE_ADMIN")) {
                    users.add(new Admin(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getInt(7), rst.getInt(8), rst.getString(9)));
                } else if (rst.getString(9).contains("ROLE_USER")) {
                    users.add(new User(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getInt(7), rst.getInt(8), rst.getString(9)));

                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(users);
        return users;

    }

    public void trierUsersParNom() throws SQLException {

        List<User> users = getAll();
        //  Collections.sort(users, new ComparerParNom());
        users.sort((User o1, User o2) -> {
            return o1.getNom().compareTo(o2.getNom());
        });
        System.out.println(users);
    }

    public List<User> rechercherUser(String nom) throws SQLException {
        List<User> users = getAll();

        List<User> results = new ArrayList<>();

        users.stream()
                .filter(t -> t.getNom().equalsIgnoreCase(nom))
                .forEach(t -> results.add(t));
        System.out.println("results" + results);

        return results;
    }

    public List<User> rechercherUserRole(String role) throws SQLException {
        List<User> users = getAll();

        List<User> results = new ArrayList<>();

        users.stream()
                .filter(t -> t.getRoles().contains(role))
                .forEach(t -> results.add(t));
        System.out.println("results roles" + results);

        return results;
    }

    public static boolean patternMatches(String emailAddress) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
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

    public void ban_user(User u) {
        Statement st;
        try {
            st = cnx.createStatement();

            String query = "UPDATE `user` SET `isBanned`=" + true + ",`dateBan`='" + String.valueOf(u.getDateBan()) + "' WHERE `id_user`=" + u.getId() + "";
            st.executeUpdate(query);
            System.out.println("bannned");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void deban_user(int id) {
        Statement st;
        try {
            st = cnx.createStatement();

            String query = "UPDATE `user` SET `isBanned`=" + false + " WHERE `id_user`=" + id + "";
            st.executeUpdate(query);
            System.out.println("debbannned");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void sendEmail(String email, int code) {
        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtps.host", "smtp.gmail.com");
            props.put("mail.smtps.auth", "true");

            Session session = Session.getInstance(props, null);

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("reinitialisation mot de passe  <monEmail@domaine.com>"));
            msg.setRecipients(Message.RecipientType.TO, email);
            msg.setSubject("GAMEWARRIOR reinitialisation mot de passe ");
            msg.setSentDate(new Date(System.currentTimeMillis()));

            String txt = "reinitialisation mot de passe : code de verification : " + code;
            msg.setText(txt);

            SMTPTransport st = (SMTPTransport) session.getTransport("smtps");
            st.connect("smtp.gmail.com", 465, "warriorgame126", "Chaima23912570");

            st.sendMessage(msg, msg.getAllRecipients());
            System.out.println("server response :");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public User getUserByEmail(String email) {
        String req = "SELECT * FROM `user` WHERE `email`='" + email + "'";
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
                p2.setDateBan(rst.getDate(12));
                p2.setIsBanned(rst.getBoolean(11));
                p2.setRoles(role);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("getUserrByMail" + p2);
        return p2;

    }

    public void sendSms(String message_, String number) throws UnsupportedEncodingException, MalformedURLException, IOException {

        String AUTH_TOKEN = "4f2a9afc7fb39f47867a418dbf1ad9a0";
        String ACCOUNT_SID = "AC25ff29750579123a872688dcdc2fbaea";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+216" + number),
                new PhoneNumber("+12395228089"),
                message_).create();

        System.out.println(message.getSid());
    }

    public  boolean matches_nom(String str) {
        String expression = "^[a-zA-Z\\s]+";
        return str.matches(expression);
    }

    public  boolean matches_prénom(String str) {
        String expression = "^[a-zA-Z\\s]+";
        return str.matches(expression);
    }

}
