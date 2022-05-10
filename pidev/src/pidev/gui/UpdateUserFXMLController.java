/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pidev.entities.User;
import pidev.services.ServiceUser;
import pidev.utils.Boxes;

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
    private TextField tfAdresse;
    @FXML
    private TextField tfCodePostale;

    String id_user;
    List reg = Arrays.asList("Ariana", "Bizerte", "Beja", "Jendouba", "Kairaouen", "Gafsa", "Monastir", "Manouba", "Mahdia", "Sousse", "Kebilli", "Gabes", "Medinine", "Kasserine", "Zaghouen", "Sfax", "Kef", "Nabeul");
    @FXML
    private ComboBox<String> cbAdr;
    ObservableList<String> list_client = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        list_client = FXCollections.observableArrayList(reg);
        cbAdr.setItems(list_client);
    }

    public void show_data(String id) {
        ServiceUser scl = new ServiceUser();
        User cl = scl.getUser(id_user);
        tfNom.setText(cl.getNom());
        tfPrenom.setText(cl.getPrenom());

        tfCodePostale.setText(String.valueOf(cl.getCodePostale()));
        tfNumTel.setText(String.valueOf(cl.getNumTel()));

    }

    @FXML
    private void modifierUser(ActionEvent event) throws IOException {
        ServiceUser u = new ServiceUser();
        if (u.whenMatchesDigitsNumber_thenCorrect(tfNumTel.getText()) == false) {
            Boxes.alert_Box("", "le numéro de téléphone doit être des chiffres");
        } else {
            User p = new User(Integer.valueOf(id_user), tfNom.getText(), tfPrenom.getText(), cbAdr.getSelectionModel().getSelectedItem(), Integer.valueOf(tfCodePostale.getText()), Integer.valueOf(tfNumTel.getText()));
            u.modifier(p);
            Boxes.information_Box("Succés", "utilisateur modifié");
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
    private void cancelUpdate(ActionEvent event) throws IOException {
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
