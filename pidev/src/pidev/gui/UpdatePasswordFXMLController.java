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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pidev.PidevFXMain;
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
                    PidevFXMain.id_users = Integer.valueOf(id_user);

            u.modifier_password(id_user, tfMdp.getText());
            JOptionPane.showMessageDialog(null, "mot de passe modifié");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientHomeFXML.fxml"));
            Parent fxml = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ClientHomeFXMLController dbc = loader.getController();
            dbc.show("UserInfoFXML");
            dbc.show_data(id_user);
            Scene scene = new Scene(fxml);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        }
    }

    @FXML
    private void InfoUserView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientHomeFXML.fxml"));

        Parent fxml = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ClientHomeFXMLController dbc = loader.getController();
        dbc.show_data(id_user);
        dbc.show("UserInfoFXML");
        Scene scene = new Scene(fxml);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

}
