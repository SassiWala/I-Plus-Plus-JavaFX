/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import pidev.entities.Admin;
import pidev.entities.User;
import pidev.services.ServiceUser;
import pidev.utils.Boxes;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class AddAdminFXMLController implements Initializable {

    @FXML
    private Button liste_des_admins_nav;
    @FXML
    private Button liste_des_clients_nav;
    @FXML
    private Button ajouter_admin_nav;
    @FXML
    private Label lbNom;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfNumTel;
    @FXML
    private TextField tfAdr;

    public String id_user;

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
        id_user=String.valueOf(cl.getId());

    }

    

    @FXML
    private void liste_des_clients_nav(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListUserFXML.fxml"));
        Parent root = loader.load();
        lbNom.getScene().setRoot(root);
        ListUserFXMLController home = loader.getController();
        home.show_data(String.valueOf(id_user));
        home.id_user = id_user;

    }

    @FXML
    private void ajouter_admin_nav(ActionEvent event) {
    }

    @FXML
    private void log_out(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent root = loader.load();
        lbNom.getScene().setRoot(root);
    }

    @FXML
    private void ajouterAdmin(ActionEvent event) throws SQLException {
        ServiceUser u = new ServiceUser();
        if (tfNom.getText().length() == 0 || tfPrenom.getText().length() == 0 || tfEmail.getText().length() == 0 || tfAdr.getText().length() == 0 || tfNumTel.getText().length() == 0) {
            Boxes.alert_Box("", "Remplir tout les champs");
        } else if (u.patternMatches(tfEmail.getText()) == false || u.email_verifier(tfEmail.getText())) {
            Boxes.alert_Box("", "Email invalid");

        } else if (u.whenMatchesDigitsNumber_thenCorrect(tfNumTel.getText()) == false) {
            Boxes.alert_Box("", "le numéro de téléphone doit être des chiffres");
        } else {
            Admin ad = new Admin(tfNom.getText(), tfPrenom.getText(), tfEmail.getText(), "GameWarriorChangeme!", tfAdr.getText(), Integer.valueOf(tfNumTel.getText()));
            u.ajouter(ad);
            JOptionPane.showMessageDialog(null, "Admin ajoutée !");

        }

    }

    

}
