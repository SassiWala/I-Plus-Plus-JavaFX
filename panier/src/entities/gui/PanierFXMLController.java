/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.gui;

import java.net.URL;
import entities.Prod;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import panier.FXMain;
import entities.Panier;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableRow;
import services.PanierServices;

/**
 * FXML Controller class
 *
 * @author yassi
 */

public class PanierFXMLController implements Initializable {

    @FXML
    private TableColumn<?, ?> prodId;
    @FXML
    private TableColumn<?, ?> quantiteId;
    @FXML
    private Button btnvaliderId;
    @FXML
    private Button btnannulerId;
    @FXML
    private TableView<Panier> tvPanier;
    
        

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                showPanier();
    }
    
    public void showPanier(){
        PanierServices ps=new PanierServices();
        ObservableList<Panier> list=FXCollections.observableArrayList(ps.getPanier());
            quantiteId.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            prodId.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
            
            
             tvPanier.setItems(list);
            
                tvPanier.setRowFactory(tv -> {
                TableRow<Panier> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        Panier rowData = row.getItem();
                        Integer id = Integer.valueOf(rowData.getId());
                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierPanierFXML.fxml"));
                        
                        Parent root;
                        
                        root = loader.load();
                        btnvaliderId.getScene().setRoot(root);
                        ModifierPanierFXMLController home = loader.getController();
                        home.show_data(id);
                        System.out.println("id>>>>>>>" + id);
                    } catch (IOException ex) {
                        Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });
            return row;});
            
          
    }
  /*  public void viderPanier(){
        PanierServices sp=new PanierServices();

            sp.vider();
            Alert a = new Alert(Alert.AlertType.INFORMATION, "panier vidé", ButtonType.CANCEL);
            a.show();
            
    }*/
    
}