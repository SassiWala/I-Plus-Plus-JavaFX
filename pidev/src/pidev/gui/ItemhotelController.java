/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import pidev.services.ServiceProd;




/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ItemhotelController implements Initializable {

    @FXML
    private ImageView imghotel;
    @FXML
    private Text nomhotel;
    @FXML
    private Text villehotel;
    @FXML
    private Text nbetoile;
    @FXML
    private ImageView edithotel;
    @FXML
    private ImageView supphotel;
    Prod h=null;
     @FXML
    private AnchorPane panel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      void SetData(Prod ev){
        this.h=ev; 
       nomhotel.setText(this.h.getNom_prod());
       villehotel.setText(this.h.getPrix_prod()+"");
        nbetoile.setText(this.h.getRate_prod()+"");
        
           String imageE = "file:" + this.h.getImg_prod();

      Image imageg = new Image(imageE, 110, 110, false, true);

         imghotel.setImage(imageg);

  
    }
 

    @FXML
    private void supprimerhotel(MouseEvent event) {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
               
               
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Voulez vous supprimer cet produit  ?");

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get() == ButtonType.OK) {

                ServiceProd erd= new ServiceProd();
            
             
               int idE= this.h.getId();
                erd.supprimer(idE);
//stage.close();
    // do what you have to do
   
            } else {

                return;

                
        
    }
    }

    @FXML
    private void modifierhotel(MouseEvent event) {
        
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous modifier le jeux");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader ();
                           loader.setLocation(getClass().getResource("Modifeirhotel.fxml"));
                            try {
                                loader.load();
                                
                            } catch (Exception ex) {
                                System.err.println(ex.getMessage());
                            }
                            
                           ModifeirhotelController atc = loader.getController();
                           // atc.setUpdate(true);
                            
                            atc.selectEvenement(h);
                
                
               // ModifierEvenementController me=new ModifierEvenementController();
                // me.selectEvenement(ev);
                Parent root = loader.getRoot();
//Parent root = FXMLLoader.load(getClass().getResource("../GUI/modifierEvenement.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        
        
        
        
        
        
    }
 
}
