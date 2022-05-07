/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import tn.edu.esprit.entities.LigneCommande;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tn.edu.esprit.entities.Commande;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.edu.esprit.services.LigneCommandeServices;
/**
 * FXML Controller class
 *
 * @author yassi
 */
public class DetailsFXMLController implements Initializable {

    public int commande;
    @FXML
    private TableView<LigneCommande> tvDetails;
    @FXML
    private TableColumn<?, ?> clmProd;
    @FXML
    private TableColumn<?, ?> clmQuantite;
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
    @FXML
    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setIdCmd(int p){
        commande=p;
        System.err.println(p);
    }
     public void showDetails(int id) {
         LigneCommandeServices ps = new LigneCommandeServices();
        ObservableList<LigneCommande> list = FXCollections.observableArrayList(ps.getByIdCommande(id));
         System.err.println("5555"+ps.getByIdCommande(id));
        clmQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        clmProd.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        
        tvDetails.setItems(list);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CommandeFXML.fxml"));
        Parent root = loader.load();
        btnRetour.getScene().setRoot(root);
    }
}
