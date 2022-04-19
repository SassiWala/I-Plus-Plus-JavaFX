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
import javafx.scene.control.TextField;
import pidev.entities.User;
import pidev.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class UpdateUserFXMLController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfNumTel;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfCodePostale;

    String id_user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void show_data(String id) {
        ServiceUser scl = new ServiceUser();
        User cl = scl.getUser(id_user);
        tfNom.setText(cl.getNom());
        tfPrenom.setText(cl.getPrenom());
        tfAdresse.setText(cl.getAdresse());
        tfCodePostale.setText(String.valueOf(cl.getCodePostale()));
        tfNumTel.setText(String.valueOf(cl.getNumTel()));

    }

    @FXML
    private void modifierUser(ActionEvent event) throws IOException {
        ServiceUser u = new ServiceUser();
        if (u.whenMatchesDigitsNumber_thenCorrect(tfNumTel.getText()) == false) {
            alert_Box("", "le numéro de téléphone doit être des chiffres");
        } else {
            User p = new User(Integer.valueOf(id_user), tfNom.getText(), tfPrenom.getText(), tfAdresse.getText(), Integer.valueOf(tfCodePostale.getText()), Integer.valueOf(tfNumTel.getText()));
            u.modifier(p);
            information_Box("Succés", "utilisateur modifié");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserInfoFXML.fxml"));
            Parent root = loader.load();
            tfNom.getScene().setRoot(root);
            UserInfoFXMLController home = loader.getController();
            home.id_user = id_user;
            home.show_data(id_user);
        }

    }

    @FXML
    private void cancelUpdate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserInfoFXML.fxml"));
        Parent root = loader.load();
        tfNom.getScene().setRoot(root);
        UserInfoFXMLController home = loader.getController();
        home.id_user = id_user;
        home.show_data(id_user);
    }

    public void information_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.INFORMATION);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }

    public void alert_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.WARNING);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }
}
