/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import pidev.entities.User;
import pidev.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class ClientHomeFXMLController implements Initializable {

    @FXML
    private Label lbEmail;
    @FXML
    private Label name_client;
    @FXML
    private Button Actualité;
    @FXML
    private Button profile;
    @FXML
    private Hyperlink log_out;

    public String id_user;
    @FXML
    private Circle picture_id;
    @FXML
    private Button events;
    @FXML
    private Button recla;
    @FXML
    private Button panier;
    @FXML
    private BorderPane center_panel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        show_data(id_user);
                loadpage("front_end");

    }

  

    public void show_data(String id) {
        ServiceUser scl = new ServiceUser();
        User cl = scl.getUser(id);
        lbEmail.setText("Bienvenue " + cl.getNom() + " " + cl.getPrenom());
        picture_id.setStroke(Color.SEAGREEN);
        if (cl.getImg() != null) {
            Image picture = new Image("pidev/images/" + cl.getImg(), false);
            picture_id.setFill(new ImagePattern(picture));
            picture_id.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

        }

    }

    private void loadpage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));

        } catch (IOException ex) {
            System.err.println(ex.getMessage());        }

        center_panel.setCenter(root);
    }

    @FXML
    private void Actualité(ActionEvent event) {
        loadpage("front_end");
    }

    @FXML
    private void ProduitView(ActionEvent event) {
        loadpage("ProduitFront");
    }

    @FXML
    private void dashboard(MouseEvent event) {
    }

    @FXML
    private void log_out_button(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent root = loader.load();
        lbEmail.getScene().setRoot(root);
    }

    @FXML
    private void ProfileView(ActionEvent event) throws IOException {
        loadpage("UserInfoFXML");
    }

    @FXML
    private void EventsView(ActionEvent event) throws IOException {
      loadpage("FrontEvent");
      
    }

    @FXML
    private void ReclamationView(ActionEvent event) {
        loadpage("ReclamationFront");
    }

    @FXML
    private void PanierView(ActionEvent event) {
        loadpage("PanierFXML");
    }
    public void show(String page){
        loadpage(page);
    }

}
