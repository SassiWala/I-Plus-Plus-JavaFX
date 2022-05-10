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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pidev.entities.actualite;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class ActualitéCelluleFXMLController extends ListCell<actualite> {

    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label rating;
    @FXML
    private Label Date;
    @FXML
    private Label vue;
    @FXML
    private Label categorie;
     @FXML
    private GridPane gridp;
    private FXMLLoader mLLoader;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
      @Override
    protected void updateItem(actualite u, boolean empty) {
        super.updateItem(u, empty);

        if (empty || u == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("ActualitéCelluleFXML.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            
                titre.setText(u.getTitre_actualite());
                description.setText(u.getDescription());
                rating.setText(String.valueOf(u.getRating_act()));
                Date.setText(String.valueOf(u.getDate_act()));
                vue.setText(String.valueOf(u.getVu()));
                categorie.setText(String.valueOf(u.getCategorie()));

            
           

            setText(null);
            setGraphic(gridp);
        }

    }}
    
}
