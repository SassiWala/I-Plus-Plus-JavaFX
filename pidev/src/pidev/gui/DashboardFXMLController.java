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
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import pidev.PidevFXMain;
import pidev.entities.User;
import pidev.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class DashboardFXMLController implements Initializable {

    @FXML
    private Label lbNom;
    @FXML
    private Button liste_des_clients_nav;
    @FXML
    private Button ajouter_admin_nav;
    @FXML
    private BorderPane centerPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadpage("ListUserFXML");
    }

    public void show_data(String id) {
        ServiceUser scl = new ServiceUser();
        User cl = scl.getUser(String.valueOf(PidevFXMain.id_users));
        lbNom.setText(cl.getNom() + " " + cl.getPrenom());
        System.out.println("ooooooo"+PidevFXMain.id_users);

    }

    private void loadpage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));

        } catch (IOException ex) {

        }

        centerPane.setCenter(root);
    }

    @FXML
    private void liste_des_clients_nav(ActionEvent event) {
        loadpage("ListUserFXML");

    }

    @FXML
    private void ajouter_admin_nav(ActionEvent event) {
        loadpage("AddAdminFXML");
    }

    @FXML
    private void log_out(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent root = loader.load();
        lbNom.getScene().setRoot(root);

    }

    @FXML
    private void liste_des_events(ActionEvent event) {
        loadpage("BackEvent");

    }

    @FXML
    private void ProduitsBack(ActionEvent event) {
        loadpage("pechotel");
    }

    @FXML
    private void CommandeBack(ActionEvent event) {
        loadpage("CommandeFXML");

    }

    @FXML
    private void ReclamationBack(ActionEvent event) {
                loadpage("Reclamation");

    }
    public void show(String page){
        loadpage(page);
    }

    @FXML
    private void ActualitéBack(ActionEvent event) {
        loadpage("Actualites");
    }

}
