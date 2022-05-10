/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entities.Evenement;
import pidev.utils.DataSource;

/**
 *
 * @author karim
 */
public class ServiceEvenement implements Iservice1<Evenement> {

    Connection cnx2;

    public ServiceEvenement() {
        cnx2 =DataSource.getinstance().getCns();

    }

    @Override
    public void Ajouter(Evenement E) {

        String requete2 = "INSERT INTO `evenement`(`nom_event`,`date`,`lieu`,`desc_event`,`type`) VALUES " + "('" + E.getNomEvent() + "',"
                + "'" + E.getDate() + "',"
                + "'" + E.getLieu() + "',"
                + "'" + E.getDescEvent() + "',"
                + "'" + E.getType() + "')";
        ;

        try {
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete2);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Evenement> Afficher() {
        ObservableList<Evenement> myList1 = FXCollections.observableArrayList();
        try {

            String requete3 = " SELECT * FROM evenement";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setIdEvent(rs.getInt("id_event"));
                e.setNomEvent(rs.getString("nom_event"));
                e.setDate(rs.getDate("date"));
                e.setLieu(rs.getString("lieu"));
                e.setDescEvent(rs.getString("desc_event"));

                e.setType(rs.getString("type"));
                myList1.add(e);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return myList1;
    }

    @Override
    public void supprimer(Evenement E) {

        try {
            String requete3 = "DELETE FROM evenement where id_event=? ";
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            pst.setInt(1, E.getIdEvent());
            int ok = pst.executeUpdate();

            if (ok == -1) {
                System.out.println("Failed");
            } else {
                System.out.println("Event deleted ! ");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Evenement E) {
        String req = "UPDATE `evenement` SET `id_event`='" + E.getIdEvent()
                + "',`nom_event`='" + E.getNomEvent() + "',`date`='" + E.getDate()
                + "',`lieu`='" + E.getLieu()
                + "',`desc_event`='" + E.getDescEvent()
                + "',`type`='" + E.getType()
                + "'WHERE `evenement`.`id_event` =" + E.getIdEvent() + "";

        try {
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifiertt(Evenement t) {
        try {
            String sql = "UPDATE evenement SET nom_event= ?,date = ?, lieu = ? ,desc_event = ? , type = ?  WHERE id_event = ?";
            PreparedStatement preparedStatement = cnx2.prepareStatement(sql);
            preparedStatement.setString(1, t.getNomEvent());
            preparedStatement.setDate(2, t.getDate());
            preparedStatement.setString(3, t.getLieu());
            preparedStatement.setString(4, t.getDescEvent());
            preparedStatement.setString(5, t.getType());
            preparedStatement.setInt(6, t.getIdEvent());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
    }

    public ObservableList<Evenement> rechercheEvenement(String nom) {
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        try {
            String requete3 = "SELECT  * FROM evenement WHERE nomEvent=?";
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            pst.setString(1, nom);
            ResultSet rs = pst.executeQuery();
            Evenement p = new Evenement();
            rs.first();
            p.setIdEvent(rs.getInt(1));
            p.setNomEvent(rs.getString("nomEvent"));
            p.setDate(rs.getDate("date"));
            list.add(p);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;

    }

    /*public boolean rechercherById(int id) {
        return List.stream().anyMatch(e -> e.getId() == id);
    }
     */
    class MyComparatorEvenement implements Comparator<Evenement> {

        @Override
        public int compare(Evenement o1, Evenement o2) {
            return o1.getDate().compareTo(o2.getDate());

        }
    }

    public ObservableList<Evenement> triParDate() throws SQLException {
        ObservableList<Evenement> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM Evenement ";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Evenement(rs.getInt("idEvent"), rs.getString("nomEvent"), rs.getDate("date")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //TRI
        Collections.sort(list, new MyComparatorEvenement());

        return list;
    }
}
