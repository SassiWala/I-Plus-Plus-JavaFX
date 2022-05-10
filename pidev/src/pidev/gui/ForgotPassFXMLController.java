/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
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
import pidev.utils.Boxes;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class ForgotPassFXMLController implements Initializable {

    @FXML
    private TextField tfEmail;

    public static User Resetuser;
    public static int verificationcode;
    public static String verificationemail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void envoyerCode(ActionEvent event) throws IOException {
        Random rand = new Random();
        int randomCode = rand.nextInt(999999);
        verificationcode = randomCode;
        verificationemail = tfEmail.getText();
        ServiceUser su = new ServiceUser();
        User u = su.getUserByEmail(verificationemail);
        if (u.getEmail() == null) {
            Boxes.alert_Box("échec", "email n'existe pas");
        } else {
            Boxes.information_Box("succés", "code envoyé");
            Resetuser = u;
            su.sendEmail(verificationemail, randomCode);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VerifierCodeFXML.fxml"));
            Parent root = loader.load();
            tfEmail.getScene().setRoot(root);

        }

    }


}
