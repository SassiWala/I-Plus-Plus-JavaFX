/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import pidev.entities.User;
import pidev.services.ServiceUser;
import pidev.utils.Boxes;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class UserInfoBFXMLController implements Initializable {

    @FXML
    private Label lbNom;
    @FXML
    private Button liste_des_admins_nav;
    @FXML
    private Button liste_des_clients_nav;
    @FXML
    private Button ajouter_admin_nav;
    @FXML
    private Label lbEmail;
    @FXML
    private Label lbAdr;
    @FXML
    private Label lbNumTel;

    public int id_user;
    @FXML
    private DatePicker dpPeriode;
    @FXML
    private CheckBox cbBanner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void show_data(String id) {
        ServiceUser scl = new ServiceUser();
        User cl = scl.getUser(id);
        lbNom.setText(cl.getNom() + " " + cl.getPrenom());

        lbAdr.setText(cl.getAdresse());
        lbEmail.setText(cl.getEmail());
        lbNumTel.setText(String.valueOf(cl.getNumTel()));
        if (cl.getDateBan() != null && cl.isIsBanned()==true) {
            Date dt = (Date) cl.getDateBan();
            dpPeriode.setValue(dt.toLocalDate());
            cbBanner.setSelected(true);
        }
        id_user = cl.getId();

    }

    @FXML
    private void liste_des_clients_nav(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListUserFXML.fxml"));
        Parent root = loader.load();
        lbNom.getScene().setRoot(root);
        ListUserFXMLController home = loader.getController();
        home.show_data(String.valueOf(id_user));
        home.id_user = String.valueOf(id_user);

    }

    @FXML
    private void ajouter_admin_nav(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAdminFXML.fxml"));
        Parent root = loader.load();
        lbNom.getScene().setRoot(root);
        ListUserFXMLController home = loader.getController();
        home.show_data(String.valueOf(id_user));
        home.id_user = String.valueOf(id_user);
    }

    @FXML
    private void log_out(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent root = loader.load();
        lbNom.getScene().setRoot(root);
    }

    @FXML
    private void returnToView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListUserFXML.fxml"));
        Parent root = loader.load();
        lbNom.getScene().setRoot(root);
    }

    @FXML
    private void supprimerUtilisateur(ActionEvent event) {
        ServiceUser su = new ServiceUser();
        su.supprimer(id_user);
        Boxes.information_Box("", "Utilisateur supprimé");

    }

    @FXML
    private void BannerUser(ActionEvent event) throws MalformedURLException, IOException {
        ServiceUser su = new ServiceUser();
        if (cbBanner.isSelected() == true) {
            Date dt = Date.valueOf(dpPeriode.getValue());
            User u = new User(id_user, true, dt);
            su.ban_user(u);
          su.sendSms("you are banned", lbNumTel.getText());
            Boxes.information_Box("", "User banned");
        }else if (cbBanner.isSelected() == false) {
            su.deban_user(id_user);
        }
    }
}
