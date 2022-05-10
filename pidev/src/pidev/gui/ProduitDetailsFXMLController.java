/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import pidev.PidevFXMain;
import pidev.entities.Panier;
import pidev.entities.Prod;
import pidev.services.PanierServices;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class ProduitDetailsFXMLController implements Initializable {

    @FXML
    private ImageView imgProd;
    @FXML
    private Label lbPrix;
    @FXML
    private Label lbNom;
    @FXML
    private Text tNom;
    @FXML
    private Text tDesc;
    @FXML
    private Text tPrix;
    @FXML
    private Button btnAjouter;
    Prod p1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void showData(Prod p) {

        String imageE = "file:" + p.getImg_prod();
        Image imageg = new Image(imageE, 110, 110, false, true);
        imgProd.setImage(imageg);
        tNom.setText(p.getNom_prod());
        tDesc.setText(p.getDescription_prod());
        tPrix.setText(String.valueOf(p.getPrix_prod()));
        p1 = p;

    }

    @FXML
    private void Ajouter(ActionEvent event) {
        Panier p = new Panier();
        PanierServices ps = new PanierServices();
        boolean res = false;
        ObservableList<Panier> cart = ps.getPanier();
        while (cart.iterator().hasNext() && !cart.isEmpty()) {
            if (cart.iterator().next().getProduit_id() == p1.getId()) {
                Alert a = new Alert(Alert.AlertType.ERROR, "ce produit existe deja", ButtonType.OK);
                a.show();
                res=true;
                break;
            } 
        }
        if (p1.getQuantite() == 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, "ce produit est hors stock", ButtonType.OK);
            a.show();
            res=true;

        }else if(res ==false) {
                p.setProduit_id(p1.getId());
                p.setQuantite(1);
                p.setUser_id(PidevFXMain.id_users);
                System.out.println(p);
                ps.ajouter(p);
            }
    }

}
