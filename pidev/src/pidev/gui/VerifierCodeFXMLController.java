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
import static pidev.gui.ForgotPassFXMLController.verificationcode;
import pidev.utils.Boxes;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class VerifierCodeFXMLController implements Initializable {

    @FXML
    private TextField tfCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void verifier(ActionEvent event) throws IOException {
        if(verificationcode == Integer.valueOf(tfCode.getText())){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPasswordFXML.fxml"));
            Parent root = loader.load();
            tfCode.getScene().setRoot(root);
        }else{
            Boxes.alert_Box("échec", "le code est erroné");
        }
    }
    
    
    
}
