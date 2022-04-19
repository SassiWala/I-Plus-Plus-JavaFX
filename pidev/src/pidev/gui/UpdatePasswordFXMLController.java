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
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import pidev.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class UpdatePasswordFXMLController implements Initializable {

    @FXML
    private TextField tfMdp;
    @FXML
    private TextField tfMdpC;

    public String id_user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ModifierMotdePasse(ActionEvent event) throws IOException {
        if (tfMdp.getText().equals(tfMdpC.getText()) == false) {
            JOptionPane.showMessageDialog(null, "password doesn't much");
        } else {
            ServiceUser u = new ServiceUser();
            u.modifier_password(id_user, tfMdp.getText());
            JOptionPane.showMessageDialog(null, "mot de passe modifié");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserInfoFXML.fxml"));
            Parent root = loader.load();
            tfMdp.getScene().setRoot(root);
            UserInfoFXMLController home = loader.getController();
            home.id_user = id_user;
            home.show_data(id_user);

        }
    }

    @FXML
    private void InfoUserView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserInfoFXML.fxml"));
        Parent root = loader.load();
        tfMdp.getScene().setRoot(root);
        UserInfoFXMLController home = loader.getController();
        home.id_user = id_user;
        home.show_data(id_user);
    }

}
