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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import pidev.entities.User;
import pidev.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class InscriptionFXMLController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMdp;
    @FXML
    private TextField tfNumTel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void inscription(ActionEvent event) throws IOException, SQLException {
        ServiceUser s = new ServiceUser();
        if (tfNom.getText().length() == 0 || tfPrenom.getText().length() == 0 || tfEmail.getText().length() == 0 || tfMdp.getText().length() == 0 || tfNumTel.getText().length() == 0) {
            alert_Box("", "Remplir tout les champs");        }
       else if (s.patternMatches(tfEmail.getText())==false || s.email_verifier(tfEmail.getText())) {
            alert_Box("", "Email invalid");

        }else if(s.whenMatchesDigitsNumber_thenCorrect(tfNumTel.getText())==false){
            alert_Box("", "le numéro de téléphone doit être des chiffres");
        }else if(s.whenMatchesPassword_thenCorrect(tfMdp.getText())==false){
            alert_Box("", "Le mot de passe doit contenir au moins un caractère minuscule, un caractère majuscule, un chiffre et une longueur comprise entre 8 et 20");
        }else {
            s.ajouter(new User(tfNom.getText(), tfPrenom.getText(), tfEmail.getText(), tfMdp.getText(), Integer.valueOf(tfNumTel.getText())));
            JOptionPane.showMessageDialog(null, "Utilisateur ajoutée !");

        }
    }

    @FXML
    private void loginShow(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));

        try {
            Parent root = loader.load();
            tfEmail.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void alert_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.WARNING);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }

}
