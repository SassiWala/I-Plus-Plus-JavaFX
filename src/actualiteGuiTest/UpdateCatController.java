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
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author sahra
 */
public class UpdateCatController implements Initializable {

    
    @FXML
    private TextField tfupdatecat;
    @FXML
    private Button btnmodifier;
    @FXML
    private ListView<categorie> listviewupdatecat;
    ObservableList<categorie> clist= FXCollections.observableArrayList();
    @FXML
    private TextField tfid;
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
        validationSupport.registerValidator(tfupdatecat, Validator.createEmptyValidator("Text is required"));
        categorieCRUD cc = new categorieCRUD();
        List<categorie> list = cc.afficherCategorie();
        clist= FXCollections.observableArrayList(list);
        listviewupdatecat.setItems(clist);
    }    

    @FXML
    private void fillformupdate(MouseEvent event) {
        categorie c =new categorie();
        c=listviewupdatecat.getSelectionModel().getSelectedItem();
        tfupdatecat.setText(c.getNom_cat_actualite());
        tfid.setText(String.valueOf(c.getId_cat_actualite()));
    }

    @FXML
    private void modifieractualite(ActionEvent event) {
        if(tfupdatecat.getText().length()<=0 || tfid.getText().length()<=0){
           JOptionPane.showMessageDialog(null,"you need to select and change Nom Categorie");

        }
        else{
        JOptionPane.showMessageDialog(null,"Successfully Updated");
    
          String nomcat = tfupdatecat.getText();
        categorie c = new categorie(nomcat);
        categorieCRUD cc=new categorieCRUD();
        cc.UpdateCategorie(c,Integer.valueOf(tfid.getText()));
        List<categorie> list = cc.afficherCategorie();
        clist= FXCollections.observableArrayList(list);
        listviewupdatecat.setItems(clist);
    }
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
