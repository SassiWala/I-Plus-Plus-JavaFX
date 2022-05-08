/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actualiteGuiTest;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaServices.actualiteCRUD;
import javaentity.actualite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class ShowActController implements Initializable {

  @FXML
    private AnchorPane content;
    @FXML
    private AnchorPane root;
    /**
     * Initializes the controller class.
     */
 @Override
    public void initialize(URL url, ResourceBundle rb) {
      try {
          ShowAct();
      } catch (Exception ex) {
          Logger.getLogger(ShowActController.class.getName()).log(Level.SEVERE, null, ex);
      }
        }  
   public void ShowAct() throws Exception {

        // clear existing content if it exists
        if (content.getChildren () != null) {
            content.getChildren ().clear ();
        }

        // get Elements to display
        actualiteCRUD as = new actualiteCRUD ();
        List<actualite> myList = new ArrayList<actualite> ();
        myList = as.afficheractualite();

        VBox Container = new VBox ();  // main container for all data specific to a materiel

        ScrollPane scrollPane = new ScrollPane (Container);
        scrollPane.setPrefSize (1520, 900);
        scrollPane.setHbarPolicy (ScrollPane.ScrollBarPolicy.NEVER);

        AnchorPane.setTopAnchor (scrollPane, 0.);
        Container.setPrefWidth (1520);
        Container.setPrefHeight (900);

        content.setRightAnchor (scrollPane, 0.);
        content.setBottomAnchor (scrollPane, 0.);
        content.setLeftAnchor (scrollPane, 0.);

         //Container.setPadding(new Insets (30,30,30,30));
        HBox titre = new HBox ();
        Label Title = new Label ("Title");
        Title.setStyle ("-fx-alignment : center; -fx-font-weight:bold;  -fx-font-size: 17; -fx-text-fill: #ffffff ");
        Label Description = new Label ("Description");
        Description.setStyle ("-fx-alignment : center; -fx-font-weight:bold;  -fx-font-size: 17; -fx-text-fill: #ffffff ");
        Label Etendue = new Label ("Etendue");
        Etendue.setStyle ("-fx-alignment : center; -fx-font-weight:bold;  -fx-font-size: 17; -fx-text-fill: #ffffff ");
        Label Image = new Label ("Image");
        Image.setStyle ("-fx-alignment : center; -fx-font-weight:bold;  -fx-font-size: 17; -fx-text-fill: #ffffff ");
        Label Categorie = new Label ("Categorie");
        Categorie.setStyle ("-fx-alignment : center; -fx-font-weight:bold;  -fx-font-size: 17; -fx-text-fill: #ffffff ");
       

        Title.setMinWidth (100);
        Description.setMinWidth ((500));
        Image.setMinWidth (200);
        Categorie.setMinWidth (100);

        titre.getChildren ().add (Title);
        titre.getChildren ().add (Description);
        titre.getChildren ().add (Image);


        titre.setStyle ("-fx-background-color : linear-gradient(to top right, #ff7f50, #6a5acd)");

        Container.getChildren ().add (titre);
        Label sep = new Label ("   ");
        sep.setMinWidth (100);
        Label sep1 = new Label ("   ");
        sep1.setMinWidth (100);
        Label sep2 = new Label ("   ");
        sep2.setMinWidth (100);
        Label sep3 = new Label ("   ");
        sep3.setMinWidth (100);
        Label sep4 = new Label ("   ");
        sep4.setMinWidth (100);
        Label sep5 = new Label ("   ");
        sep5.setMinWidth (100);
        Label sep6 = new Label ("   ");
        sep6.setMinWidth (100);
        Label sep7 = new Label ("   ");
        sep7.setMinWidth (100);
        // iterate through all events and create an event element
        String color = "#ffffff;";
        for (actualite act : myList) {

            //HBOX
            HBox Hb = new HBox ();

            Label TitleS = new Label (act.getTitre_actualite());
            TitleS.setPrefWidth (100);
            TitleS.setStyle (" -fx-alignment : center;-fx-text-fill: #000000;   -fx-font-size: 17;");

            Label DescriptionS = new Label (act.getDescription());
            DescriptionS.setMinWidth (500);
            DescriptionS.setStyle ("-fx-alignment : center;-fx-text-fill: #000000;   -fx-font-size: 17;");

            Label ImageS = new Label (act.getImage_act());
            ImageS.setMinWidth (200);
            ImageS.setStyle ("-fx-alignment : center;-fx-text-fill: #000000;   -fx-font-size: 17;");

            

            VBox btn = new VBox ();

          
            Label space = new Label("");
            space.setMinHeight(20);
            Label space1 = new Label("");
            space.setMinHeight(20);
         


            /* Action listeners  */


           
       /*   TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss (Duration.millis (3200));
*/
            Hb.getChildren ().add (Title);
            Hb.getChildren ().add (DescriptionS);
            Hb.getChildren ().add (ImageS);
                      

            Hb.getChildren ().add (btn);

            Hb.setStyle ("-fx-background-color : #ffffff");

            // Add all the materiel elements to the event container


            Container.getChildren ().add (Hb);
            Container.setStyle ("-fx-background-color: #ffffff");

        }

        // Finally add all the events inside the Scrollpane to the main content Anchorpane

        content.getChildren ().add (scrollPane);
    }
    
}
