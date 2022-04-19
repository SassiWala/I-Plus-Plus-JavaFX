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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import pidev.entities.User;
import pidev.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class ListUserFXMLController implements Initializable {

    @FXML
    private TableView<User> tab_clients;
    @FXML
    private TableColumn<User, ?> nom_id_cl;
    @FXML
    private TableColumn<User, ?> prenom_id_cl;
    @FXML
    private TableColumn<User, ?> email_id_cl;
    @FXML
    private TableColumn<User, ?> tel_id_cl;
    @FXML
    private TableColumn<User, ?> adresse_id_cl;
    @FXML
    private Button liste_des_admins_nav;
    @FXML
    private Button liste_des_clients_nav;
    @FXML
    private Button ajouter_admin_nav;
    public String id_user;
    @FXML
    private Label lbNom;
    ObservableList<User> list_client = FXCollections.observableArrayList();
    @FXML
    private TextField tfRecherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            affichage_tab_Clients();
        } catch (SQLException ex) {
            Logger.getLogger(ListUserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void show_data(String id) {
        ServiceUser scl = new ServiceUser();
        User cl = scl.getUser(id);
        lbNom.setText(cl.getNom() + " " + cl.getPrenom());

    }

    @FXML
    private void click_tab_clients(MouseEvent event) {
    }

    @FXML
    private void liste_des_admins_nav(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAdminFXML.fxml"));
        Parent root = loader.load();
        lbNom.getScene().setRoot(root);
        ListAdminFXMLController home = loader.getController();
        home.show_data(id_user);
        home.id_user = id_user;
    }

    @FXML
    private void liste_des_clients_nav(ActionEvent event) {
    }

    @FXML
    private void ajouter_admin_nav(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAdminFXML.fxml"));
        Parent root = loader.load();
        lbNom.getScene().setRoot(root);
        AddAdminFXMLController home = loader.getController();
        home.show_data(id_user);
        home.id_user = id_user;

    }

    public void affichage_tab_Clients() throws SQLException {
        ServiceUser scl = new ServiceUser();

        list_client = FXCollections.observableArrayList(scl.afficher());

        tel_id_cl.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        email_id_cl.setCellValueFactory(new PropertyValueFactory<>("email"));

        nom_id_cl.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_id_cl.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresse_id_cl.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        tab_clients.setItems(list_client);
        tab_clients.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    User rowData = row.getItem();
                    String id = String.valueOf(rowData.getId());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("UserInfoBFXML.fxml"));
                    Parent root;
                    try {
                        root = loader.load();
                        lbNom.getScene().setRoot(root);
                        UserInfoBFXMLController home = loader.getController();
                        home.show_data(id);
                        System.out.println("id>>>>>>>" + id);
                    } catch (IOException ex) {
                        Logger.getLogger(ListUserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });

    }

    @FXML
    private void log_out(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent root = loader.load();
        lbNom.getScene().setRoot(root);
    }

    @FXML
    private void rechercher() throws IOException, SQLException {
        ServiceUser scl = new ServiceUser();

        list_client = FXCollections.observableArrayList(scl.rechercherUser(tfRecherche.getText()));

        tel_id_cl.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        email_id_cl.setCellValueFactory(new PropertyValueFactory<>("email"));

        nom_id_cl.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_id_cl.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresse_id_cl.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        tab_clients.setItems(list_client);
        tab_clients.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    User rowData = row.getItem();
                    String id = String.valueOf(rowData.getId());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("UserInfoBFXML.fxml"));
                    Parent root;
                    try {
                        root = loader.load();
                        lbNom.getScene().setRoot(root);
                        UserInfoBFXMLController home = loader.getController();
                        home.show_data(id);
                        System.out.println("id>>>>>>>" + id);
                    } catch (IOException ex) {
                        Logger.getLogger(ListUserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });
        if (tfRecherche.getText().isEmpty()) {
            affichage_tab_Clients();
        }

    }

}
