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
import javafx.scene.control.PasswordField;
import static pidev.gui.ForgotPassFXMLController.Resetuser;
import pidev.services.ServiceUser;
import pidev.utils.Boxes;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class ResetPasswordFXMLController implements Initializable {

    @FXML
    private PasswordField tfMdp;
    @FXML
    private PasswordField tfMdpC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ChangerMdp(ActionEvent event) throws IOException {
        if (tfMdp.getText().equals(tfMdpC.getText()) == false) {
            Boxes.alert_Box("échec", "les champs ne sont pas identiques");
        } else {
            ServiceUser su = new ServiceUser();
            su.modifier_password(String.valueOf(Resetuser.getId()),tfMdp.getText());
            System.out.println("ResetUser+++++"+Resetuser);
            Boxes.information_Box("succés", "votre mot de passe est modifié");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
            Parent root = loader.load();
            tfMdp.getScene().setRoot(root);
        }
    }

}
