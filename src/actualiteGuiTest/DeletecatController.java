/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actualiteGuiTest;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javaServices.actualiteCRUD;
import javaServices.categorieCRUD;
import javaentity.actualite;
import javaentity.categorie;
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
         root = FXMLLoader.load(getClass().getResource("/actualiteGuiTest/Actualites.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }
    
}