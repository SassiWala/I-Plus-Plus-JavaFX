/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author sahra
 */
public class Front_ActualiteController implements Initializable {

    @FXML
    private WebView webview;
    @FXML
    private Button load;
    @FXML
    private TextField tflink;
    private WebEngine engine;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       engine = webview.getEngine();
       Loadpage();
    }    

    @FXML
    private void Loadpage() {
        engine.load("http://127.0.0.1:8000/actualite/");
    }

    @FXML
    private void link(ActionEvent event) {
    }
    
}
