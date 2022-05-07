/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import tn.edu.esprit.entities.Panier;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import tn.edu.esprit.services.PanierServices;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class ModifierQuantiteFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public int stockp;
    public Panier panier;
    @FXML
    private TextField tfQuantite;
    @FXML
    private Button btnModif;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setPanier(Panier p){
        panier=p;
        //System.out.println("panier"+p);
    }
    public void setStock(Panier p){
        stockp=p.getStock();
        //System.out.println("panier"+p);
    }

    @FXML
    private void modifQ(ActionEvent event) throws IOException {
        
        if(!tfQuantite.getText().isEmpty()&&Integer.valueOf(tfQuantite.getText())<=panier.getStock() ){
        PanierServices ps = new PanierServices();
        ps.modifier2(panier.getId(), Integer.valueOf(tfQuantite.getText()));
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PanierFXML.fxml"));
        Parent root = loader.load();
        btnModif.getScene().setRoot(root);
        //Notification
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Modification de la Quantité ");
        tray.setMessage("Quantité Modifiée avec success");
        tray.setNotificationType(NotificationType.NOTICE);
        tray.showAndWait();
        }else{
            Alert a =new Alert(Alert.AlertType.ERROR, "veuiller metre une quantité convenable", ButtonType.OK);
            a.show();
        }
        System.err.println(Integer.valueOf(tfQuantite.getText()));
        System.err.println(panier.getStock());
    }

    @FXML
    private void retourPanier(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PanierFXML.fxml"));
        Parent root = loader.load();
        btnModif.getScene().setRoot(root);
    }
    
    
}
