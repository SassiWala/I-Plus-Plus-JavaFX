/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import pidev.entities.Prod;
import pidev.services.ServiceProd;



/**
 * FXML Controller class
 *
 * @author 21695
 */
public class ProduitFrontController implements Initializable {
    
    private GridPane grid;
    @FXML
    private TextField tfSearch;
    List<Prod> ListeDesEvent;
    @FXML
    private ListView<Prod> list_prod;
    ObservableList<Prod> list_recherche_produit = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceProd sh = new ServiceProd();
        afficher();
        
    }
    
    
    public void afficher() {
        ServiceProd sh = new ServiceProd();
        ListeDesEvent = FXCollections.observableArrayList(sh.getAll());
        list_prod.getItems().addAll(ListeDesEvent);
        list_prod.setCellFactory(param -> new ListCell<Prod>() {
            private ImageView imageView = new ImageView();
            
            @Override
            public void updateItem(Prod name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    String imageE = "file:" + name.getImg_prod();
                    Image imageg = new Image(imageE, 110, 110, false, true);
                    imageView.setImage(imageg);
                    setText(name.getNom_prod());
                    setGraphic(imageView);
                }
                
            }
        });
        list_prod.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ProduitDetailsFXML.fxml"));
                try {
                    loader.load();
                    
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
                ProduitDetailsFXMLController pdc = loader.getController();
                pdc.showData(list_prod.getSelectionModel().getSelectedItem());
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.show();
                System.out.println(list_prod.getSelectionModel().getSelectedItem());
            }
        });
        
        System.out.println("khanfir.aa7.ClientHomeFXMLController.afficher()" + list_prod.getSelectionModel().getSelectedItem());
        
    }
    
    @FXML
    private void rechercher(ActionEvent event) throws SQLException {
        ServiceProd sh = new ServiceProd();
        list_prod.getItems().setAll();
        list_recherche_produit = FXCollections.observableArrayList(sh.rechercherUser(tfSearch.getText()));
        list_prod.getItems().addAll(list_recherche_produit);
        list_prod.setCellFactory(param -> new ListCell<Prod>() {
            private ImageView imageView = new ImageView();
            
            @Override
            public void updateItem(Prod name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    String imageE = "file:" + name.getImg_prod();
                    Image imageg = new Image(imageE, 110, 110, false, true);
                    imageView.setImage(imageg);
                    setText(name.getNom_prod());
                    setGraphic(imageView);
                }
                
            }
        });
        
    }
    
}
