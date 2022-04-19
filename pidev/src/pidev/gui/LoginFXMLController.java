/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pidev.entities.User;
import pidev.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Button loginBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Login(ActionEvent event) throws IOException {
        if (tfEmail.getText().length() == 0 || tfPassword.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "remplir tout les champs");
        } else {
            ServiceUser u = new ServiceUser();
            User p2 = u.login(tfEmail.getText(), tfPassword.getText());
            if (p2.getEmail() != null && p2.getRoles().contains("ROLE_USER")==true) {
                client_home(String.valueOf(p2.getId()));
            } else if (p2.getEmail() != null && p2.getRoles().contains("ROLE_ADMIN")==true) {
                Admin_Home(String.valueOf(p2.getId()));
            } else {
                JOptionPane.showMessageDialog(null, "Email ou mot de passe invalid");

            }
        }

    }

    @FXML
    private void forgottenPassword(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgotPassFXML.fxml"));

        try {
            Parent root = loader.load();
            tfEmail.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void client_home(String id) throws IOException {
        ServiceUser u = new ServiceUser();
        User p = u.getUser(id);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientHomeFXML.fxml"));
        Parent root = loader.load();
        tfEmail.getScene().setRoot(root);
        ClientHomeFXMLController home = loader.getController();
        home.id_user = id;
        home.show_data(id);

    }

    private void Admin_Home(String id) throws IOException {
        ServiceUser u = new ServiceUser();
        User p = u.getUser(id);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListUserFXML.fxml"));
        Parent root = loader.load();
        tfEmail.getScene().setRoot(root);
        ListUserFXMLController home = loader.getController();
        home.id_user = id;
        home.show_data(id);

    }

    @FXML
    private void InscriptionShow(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InscriptionFXML.fxml"));

        try {
            Parent root = loader.load();
            tfEmail.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
