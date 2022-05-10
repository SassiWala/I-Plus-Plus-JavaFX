/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pidev.entities.actualite;
import pidev.services.actualiteCRUD;

/**
 * FXML Controller class
 *
 * @author sahra
 */
public class Front_endController implements Initializable {

    @FXML
    private ListView<actualite> listfront;
    ObservableList<actualite> alist = FXCollections.observableArrayList();

    @FXML
    private TextField tfrech;
    @FXML
    private Button btnrech;
    @FXML
    private Button btnlist;
    @FXML
    private Button btnvoir;
    @FXML
    private Button btnweb;
    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    private Label tftitree;
    @FXML
    private Label tfdesc;
    @FXML
    private Label tfetendu;
    @FXML
    private Label tfdate;
    @FXML
    private ImageView imgview;
    @FXML
    private Button btnlike;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualiteCRUD ac = new actualiteCRUD();
        List<actualite> list = ac.affichfront();
        alist = FXCollections.observableArrayList(list);
        listfront.setItems(alist);
    }

    @FXML
    private void rechercher(ActionEvent event) {
        String search = tfrech.getText();
        System.out.println(search);
        actualiteCRUD acd = new actualiteCRUD();
        List<actualite> list = acd.Searcho(search);
        alist = FXCollections.observableArrayList(list);
        listfront.getItems().addAll(alist);
        listfront.setCellFactory(param -> new ActualitéCelluleFXMLController());
    }

    @FXML
    private void listall(ActionEvent event) {
        actualiteCRUD ac = new actualiteCRUD();
        List<actualite> list = ac.affichfront();
        alist = FXCollections.observableArrayList(list);
        listfront.getItems().addAll(alist);
        listfront.setCellFactory(param -> new ActualitéCelluleFXMLController());
    }

    @FXML
    private void voir(ActionEvent event) throws IOException {

        actualiteCRUD ac = new actualiteCRUD();
        actualite a = ac.afficheractualite3(tftitree.getText());
        tfdesc.setText(a.getDescription());
        tfetendu.setText(a.getEtendu());
        tfdate.setText(String.valueOf(a.getDate_act()));
        Image image = new Image("file:" + a.getImage_act());
        imgview.setImage(image);
        ac.Vu(a);
    }

    @FXML
    private void web(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Front_Actualite.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void fillformfront(MouseEvent event) {
        actualite a = new actualite();
        a = listfront.getSelectionModel().getSelectedItem();
        tftitree.setText(a.getTitre_actualite());
    }

    @FXML
    private void like(ActionEvent event) {
        actualiteCRUD ac = new actualiteCRUD();

    }

}
