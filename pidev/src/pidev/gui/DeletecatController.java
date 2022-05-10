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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pidev.PidevFXMain;
import pidev.entities.categorie;
import pidev.services.categorieCRUD;


  

/**
 * FXML Controller class
 *
 * @author sahra
 */
public class DeletecatController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private Button btndelete;
    
    @FXML
     private ListView<categorie> deleteview;
     ObservableList<categorie> alist= FXCollections.observableArrayList();
    @FXML
    private Button btnhome;
      Stage stage;
    Scene scene;
    Parent root;



    /**
     * Initializes the controller class.
     */
    @Override
   
 public void initialize(URL url, ResourceBundle rb) {
        categorieCRUD cc = new categorieCRUD();
        List<categorie> list = cc.afficherCategorie();
        alist= FXCollections.observableArrayList(list);
        deleteview.setItems(alist);
    }    

    @FXML
    private void DeleteCat(ActionEvent event) {
        if(tfid.getText().length()<=0){
           JOptionPane.showMessageDialog(null,"you need to select first");

        }
        else{
        JOptionPane.showMessageDialog(null,"Successfully Deleted");
         int ID =Integer.parseInt(tfid.getText());
          categorieCRUD cc = new categorieCRUD();
         cc.DeleteCategorie(ID);
        List<categorie> list = cc.afficherCategorie();
        alist= FXCollections.observableArrayList(list);
        deleteview.setItems(alist);
    }

    }
    @FXML
    private void fillformid(MouseEvent event) {
        categorie c =new categorie();
        c=deleteview.getSelectionModel().getSelectedItem();
        tfid.setText(String.valueOf(c.getId_cat_actualite()));

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