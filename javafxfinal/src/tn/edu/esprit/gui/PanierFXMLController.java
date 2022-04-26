/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entities.Panier;
import tn.edu.esprit.services.SendMail;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import services.PanierServices;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class PanierFXMLController implements Initializable {

    @FXML
    private TableColumn<?, ?> clmProd;
    @FXML
    private TableColumn<?, ?> clmQuantite;
    @FXML
    private TableColumn<?, ?> clmPrix;
    @FXML
    private TableView<Panier> tvPanier;
    @FXML
    private Text tfHome;
    @FXML
    private Text tfPanier;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Button btnValider;
    private TextField tfSearch;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnDelete;
    @FXML
    private Label lblSum;


    /**
    @FXML
    private AnchorPane lblTot;
    @FXML
    private HBox lblTotal;
    @FXML
    private Label lblSum;
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showPanier();
    }

    public void showPanier() {
        PanierServices ps = new PanierServices();
        ObservableList<Panier> list = FXCollections.observableArrayList(ps.getPanier());
        clmQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        clmProd.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        clmPrix.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        
        tvPanier.setItems(list);
        setLblSum(String.valueOf(ps.getTotal()));
    }

    @FXML
    private void viderPanier(ActionEvent event) throws IOException {
        PanierServices sp = new PanierServices();

        sp.vider();
//Notification
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Panier vidé ");
        tray.setMessage("Panier vidé avec success");
        tray.setNotificationType(NotificationType.WARNING);
        tray.showAndWait();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PanierFXML.fxml"));
        Parent root = loader.load();
        btnAnnuler.getScene().setRoot(root);
        
    }

    @FXML
    private void valider(ActionEvent event) throws IOException {
        PanierServices sp = new PanierServices();

        sp.ValiderCommande();

        showPanier();
                
        //Notification
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Valider Panier");
        tray.setMessage("Panier validée avec success");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait();
        
        SendMail.sendMail("yassine.bensalah@esprit.tn", "Panier Validée",  "Votre panier à etait validée");

    }

    private void search(ActionEvent event) throws SQLException, IOException {
        PanierServices sp = new PanierServices();

        sp.rechercherPanier(tfSearch.getText());
        ObservableList<Panier> list = FXCollections.observableArrayList(sp.rechercherPanier(tfSearch.getText()));
        clmQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        clmProd.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        clmPrix.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));

        tvPanier.setItems(list);


    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        Panier p = tvPanier.getSelectionModel().getSelectedItem();
        System.out.println("ppppppppppp"+p);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierQuantiteFXML.fxml"));
        Parent root = loader.load();
        btnModif.getScene().setRoot(root);
        ModifierQuantiteFXMLController apc = loader.getController();
        apc.setPanier(p);
        

    }

    @FXML
    private void deleteLigne(ActionEvent event) throws IOException {
        Panier p = tvPanier.getSelectionModel().getSelectedItem();
        PanierServices ps = new PanierServices();
        ps.supprimer(p.getId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PanierFXML.fxml"));
        Parent root = loader.load();
        btnAnnuler.getScene().setRoot(root);
        //Notification
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Suppression Produit du Panier ");
        tray.setMessage("Quantité Supprimé avec success");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
    }

    private void recherche(ActionEvent event) throws IOException, SQLException {
         PanierServices sp = new PanierServices();


        ObservableList<Panier> list = FXCollections.observableArrayList(sp.rechercherPanier(tfSearch.getText()));
        clmQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        clmProd.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        clmPrix.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));

        tvPanier.setItems(list);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PanierFXML.fxml"));
        Parent root = loader.load();
        btnAnnuler.getScene().setRoot(root);
    }
 public void setLblSum(String total) {
        this.lblSum.setText(total);
    }  
}
