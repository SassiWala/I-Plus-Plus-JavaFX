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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import pidev.PidevFXMain;
import pidev.entities.categorie;
import pidev.services.categorieCRUD;


/**
 * FXML Controller class
 *
 * @author sahra
 */
public class AddcatController implements Initializable {

    @FXML
    private TextField tfnomcat;
    @FXML
    private Button btnaddcat;
    @FXML
    private Button btnhome;
      Stage stage;
    Scene scene;
    Parent root;
        ValidationSupport validationSupport = new ValidationSupport();



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     validationSupport.registerValidator(tfnomcat, Validator.createEmptyValidator("Text is required"));
    }    

    @FXML
    private void AjouterCategorie(ActionEvent event) {
        if(tfnomcat.getText().length()<=0){
           JOptionPane.showMessageDialog(null,"you need to fill Nom Categie");

        }
        else{
        JOptionPane.showMessageDialog(null,"Successfully added");
    
     
         String nomecat = tfnomcat.getText();
         
         
            categorie c = new categorie(nomecat);
            categorieCRUD acd = new categorieCRUD();
         acd.ajoutCategorie2(c);
         
      
         
    }
    }
    @FXML
    private void home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardFXML.fxml"));
        Parent fxml = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         DashboardFXMLController dbc = loader.getController();
        dbc.show("Actualites");
                dbc.show_data(String.valueOf(PidevFXMain.id_users));

        scene = new Scene(fxml);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }
}


