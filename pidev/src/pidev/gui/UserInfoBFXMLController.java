/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pidev.entities.User;
import pidev.services.ServiceUser;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void liste_des_admins_nav(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAdminFXML.fxml"));
        Parent root = loader.load();
        lbNom.getScene().setRoot(root);
        ListAdminFXMLController home = loader.getController();
        home.show_data(String.valueOf(id_user));
    }

    public void show_data(String id) {
        ServiceUser scl = new ServiceUser();
        User cl = scl.getUser(id);
        lbNom.setText(cl.getNom() + " " + cl.getPrenom());

        lbAdr.setText(cl.getAdresse());
        lbEmail.setText(cl.getEmail());
        lbNumTel.setText(String.valueOf(cl.getNumTel()));
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
        information_Box("", "Utilisateur supprimé");

    }

    public void information_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.INFORMATION);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }
}
