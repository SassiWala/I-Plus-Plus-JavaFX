/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class CommandeFXMLController implements Initializable {

    @FXML
    private TableView<?> tvPanier;
    @FXML
    private TableColumn<?, ?> prodId;
    @FXML
    private TableColumn<?, ?> quantiteId;
    @FXML
    private Button btnvaliderId;
    @FXML
    private Button btnannulerId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
