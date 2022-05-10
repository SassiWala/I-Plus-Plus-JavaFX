/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pidev.entities.Prod;


/**
 * FXML Controller class
 *
 * @author 21695
 */
public class ItemProdController implements Initializable {

    @FXML
    private ImageView imghotel;
    @FXML
    private Text nomhotel;
    @FXML
    private Text villehotel;
    @FXML
    private Text nbetoile;

    Prod h = null;
    @FXML
    private AnchorPane panel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void SetData(Prod ev) {
        this.h = ev;
        nomhotel.setText(this.h.getNom_prod());
        villehotel.setText(this.h.getPrix_prod() + "");
        nbetoile.setText(this.h.getRate_prod() + "");

        String imageE = "file:" + this.h.getImg_prod();

        Image imageg = new Image(imageE, 110, 110, false, true);

        imghotel.setImage(imageg);

    }

}
