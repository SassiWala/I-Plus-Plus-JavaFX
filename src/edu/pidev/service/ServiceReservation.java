/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.service;

import edu.pidev.entities.Evenement;
import edu.pidev.entities.Reservation;
import edu.pidev.utils.Myconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author karim
 */
public class ServiceReservation implements Iservice {

    Connection cnx3;

    public ServiceReservation() {
        cnx3 = Myconnection.getInstance().getCnx();
    }

    public void Ajouter(Reservation R) {
        try {
            String requete2 = "INSERT INTO Reservation( id_reservation, id_evenement,  id_user) VALUES (?,?,?)";
            PreparedStatement pst = cnx3.prepareStatement(requete2);
            pst.setInt(1, R.getIdReservation());
            pst.setInt(2, R.getIdEvent());

            pst.setInt(3, R.getIdUser());
            pst.executeUpdate();
            System.out.println("la réservation est ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Reservation> Afficher() {
        ObservableList<Reservation> myList1 = FXCollections.observableArrayList();
        try {

            String requete3 = " SELECT * FROM Reservation";
            Statement st = cnx3.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Reservation e = new Reservation();
                e.setIdReservation(rs.getInt("id_reservation"));
                e.setIdEvent(rs.getInt("id_evenement"));

                e.setIdUser(rs.getInt("id_user"));
                myList1.add(e);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return myList1;
    }

    public void supprimer(Reservation R) {
        try {
            String req = "DELETE FROM Reservation  WHERE id_reservation=?";
            PreparedStatement pst = cnx3.prepareStatement(req);
            pst.setInt(1, R.getIdReservation());
            pst.executeUpdate();
            System.out.println("réservation suprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Reservation R) {
        try {
            String requete4 = "update Reservation set id_evenement=? ,id_user=?   where id_reservation=? ";

            PreparedStatement pst = cnx3.prepareStatement(requete4);
            pst.setInt(1, R.getIdEvent());
            pst.setInt(2, R.getIdUser());
            pst.setInt(3, R.getIdReservation());

            int ok = pst.executeUpdate();

            if (ok == -1) {
                System.out.println(" failed");
            } else {
                System.out.println("succes ! ");
            }
            System.out.println("modifiée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ObservableList<Reservation> rechercheParIdEvent(int idevent) {
        ObservableList<Reservation> listReservationEvenement = FXCollections.observableArrayList();;
        try {
            String query = "SELECT * FROM Reservation where id_evenement=" + idevent;
            Statement st = cnx3.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Reservation e = new Reservation();
                e.setIdReservation(rs.getInt(1));
                e.setIdEvent(rs.getInt("id event "));

                e.setIdUser(rs.getInt("iduser "));

                listReservationEvenement.add(e);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return listReservationEvenement;
    }

    @Override
    public void Ajouter(Evenement E) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Evenement E) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Evenement E) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String GetEmailUser(int id) {
        String email = null;
        try {

            String requetee = "SELECT email FROM user WHERE id_user = '" + id + "'";
            Statement pstt = cnx3.createStatement();
            ResultSet rs = pstt.executeQuery(requetee);
            while (rs.next()) {
                email = rs.getString(1);
                break;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return email;
    }

}
