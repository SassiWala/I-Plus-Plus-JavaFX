/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entities.Panier;
import entities.gui.ModifierPanierFXMLController;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Scene;

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
              showPnaier();
    }    
     public void showPnaier(){
        PanierServices ps=new PanierServices();
        ObservableList<Panier> list=FXCollections.observableArrayList(ps.getPanier());
            quantiteId.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            prodId.setCellValueFactory(new PropertyValueFactory<>("nomProd"));

            
            tvPanier.setItems(list);
            
              tvPanier.setRowFactory(tv -> {
                TableRow<Panier> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Panier rowData = row.getItem();
                    Integer id = Integer.valueOf(rowData.getId());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../entities/gui/ModifierPanierFXML.fxml"));
                    Parent root;
                    try {root = loader.load();
                        btnvaliderId.getScene().setRoot(root);
                        ModifierPanierFXMLController home = loader.getController();
                        home.show_data(id);
                        System.out.println("id>>>>>>>" + id);
                    } catch (IOException ex) {
                        
                    }
                }
            });
            return row;});
            
          
    }
}
