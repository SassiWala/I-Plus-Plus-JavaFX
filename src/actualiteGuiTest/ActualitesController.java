/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actualiteGuiTest;

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
import javafx.stage.Stage;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author sahra
 */
public class ActualitesController implements Initializable {

    @FXML
    private Button btnajoutact1;
    @FXML
    private Button btnmodact1;
    @FXML
    private Button btnsuppact1;
    @FXML
    private Button btnajoutcat1;
    @FXML
    private Button btnmodcat1;
    @FXML
    private Button btnsuppcat1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
    Stage stage;
    Scene scene;
    Parent root;

    @FXML
    private void ajoutact1(ActionEvent event) throws IOException {
     
      root = FXMLLoader.load(getClass().getResource("/actualiteGuiTest/Addact.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void modact1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/actualiteGuiTest/UpdateAct.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void suppact1(ActionEvent event) throws IOException {
          root = FXMLLoader.load(getClass().getResource("/actualiteGuiTest/DeleteAct.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
        
    }

    @FXML
    private void ajoutcat1(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("/actualiteGuiTest/Addcat.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void modcat1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/actualiteGuiTest/UpdateCat.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void suppcat1(ActionEvent event) throws IOException {
          root = FXMLLoader.load(getClass().getResource("/actualiteGuiTest/Deletecat.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }
    
}
