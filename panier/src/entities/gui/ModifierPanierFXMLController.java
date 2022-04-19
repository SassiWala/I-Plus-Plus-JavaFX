/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import services.PanierServices;
import entities.Panier;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class ModifierPanierFXMLController implements Initializable {

    @FXML
    private TextField tfquantite;
    @FXML
    private Button btnDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void show_data(int id) {
        PanierServices ps = new PanierServices();
        Panier cl = ps.getLigne(id);
       //lblProduit.setText(cl.getNomProd());
        //lblQuantite.setText(String.valueOf(cl.getQuantite()));
        

    }

    /*
    @FXML
    public void modifierquantite(int id) throws IOException{
        PanierServices ps = new PanierServices();
        Panier cl = ps.getLigne(id);
        ps.modifier2(id, Integer.valueOf(tfquantite.getText()));
        Alert a=new Alert(Alert.AlertType.CONFIRMATION);
        a.show();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../gui/PanierFXML.fxml"));
            Parent root=loader.load();
            btnDelete.getScene().setRoot(root);
        
    }*/

  
    
}
