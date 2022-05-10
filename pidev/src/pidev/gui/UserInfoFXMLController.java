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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import pidev.PidevFXMain;
import pidev.entities.User;
import pidev.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class UserInfoFXMLController implements Initializable {

    @FXML
    private Label lbNom;
    @FXML
    private Label lbMdp;
    @FXML
    private Label lbAdr;
    @FXML
    private Label lbPrenom;

    public String id_user;
    @FXML
    private Label lbEmail;
    @FXML
    private Label lbNumTel;
    private Circle picture_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show_data();

    }

    public void show_data() {
        ServiceUser scl = new ServiceUser();
        User cl = scl.getUser(String.valueOf(PidevFXMain.id_users));
        lbNom.setText(cl.getNom());
        lbPrenom.setText(cl.getPrenom());
        lbAdr.setText(cl.getAdresse());
        lbEmail.setText(cl.getEmail());
        lbMdp.setText("*********");
        lbNumTel.setText(String.valueOf(cl.getNumTel()));
        id_user=String.valueOf(PidevFXMain.id_users);
       /* if (cl.getImg() != null) {
            Image picture = new Image("pidev/images/" + cl.getImg(), false);
            picture_id.setFill(new ImagePattern(picture));
            picture_id.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

        }*/

    }

    @FXML
    private void UpdateView(ActionEvent event) throws IOException {
        ServiceUser u = new ServiceUser();
        User p = u.getUser(id_user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateUserFXML.fxml"));
        Parent root = loader.load();
        lbEmail.getScene().setRoot(root);
        UpdateUserFXMLController home = loader.getController();
        home.id_user = String.valueOf(p.getId());
        home.show_data(id_user);
    }

    @FXML
    private void UpdateViewMdp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdatePasswordFXML.fxml"));
        Parent root = loader.load();
        lbEmail.getScene().setRoot(root);
        UpdatePasswordFXMLController home = loader.getController();
        home.id_user = String.valueOf(id_user);

    }


    private void log_out_button(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent root = loader.load();
        lbAdr.getScene().setRoot(root);

    }

}
