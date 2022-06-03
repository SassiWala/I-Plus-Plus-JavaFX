/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui;

import Service.mailEvent;
import com.jfoenix.controls.JFXMasonryPane;
import edu.pidev.entities.Evenement;
import edu.pidev.entities.Reservation;
import edu.pidev.service.ServiceEvenement;
import edu.pidev.service.ServiceReservation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author karim
 */
public class FrontEventController implements Initializable {

    @FXML
    private StackPane stck;
    @FXML
    private Label title;
    @FXML
    private ScrollPane scrollPane;
    private final JFXMasonryPane mansoryPane = new JFXMasonryPane();
    Evenement rec = new Evenement();
    ServiceEvenement work = new ServiceEvenement();

    Reservation Reservation = new Reservation();
    ServiceReservation ServiceReservation = new ServiceReservation();
    public static int idUser = 63;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        scrollPane.setStyle("-fx-background: rgb(255,255,255);\n -fx-background-color: rgb(255,255,255)");
        initMansoryCard();
        LoadCardProduits();

    }

    private void initMansoryCard() {
        mansoryPane.setPadding(new Insets(15, 15, 15, 15));
        mansoryPane.setVSpacing(5);
        mansoryPane.setHSpacing(5);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(mansoryPane);

    }

    private void LoadCardProduits() {

        mansoryPane.getChildren().clear();
        List<Evenement> listeProduits = new ArrayList<>();
        listeProduits = work.Afficher();

        if (!listeProduits.isEmpty()) {
            for (int i = 0; i < listeProduits.size(); i++) {
                VBox root = new VBox();

                int id = listeProduits.get(i).getIdEvent();
                String nom = listeProduits.get(i).getNomEvent();
                Date Date = listeProduits.get(i).getDate();
                String Lieux = listeProduits.get(i).getLieu();
                String Type = listeProduits.get(i).getType();
                String desc = listeProduits.get(i).getDescEvent();

                //
                root.setPadding(new Insets(12, 17, 17, 17));
                root.setSpacing(13);

                ///
                root.setStyle("-fx-background-color: #fff; -fx-background-radius: 15px;-fx-effect:dropshadow(three-pass-box, gray, 10, 0, 0, 0);");
                //labels[i].setTextFill(Color.color(1, 0, 0));

                Label LabelNom = new Label("" + nom);
                Label LabeDate = new Label("" + Date);
                Label LabelLieux = new Label("" + Lieux);
                Label LabelType = new Label("" + Type);
                Label LabelDesc = new Label("" + desc);

                LabelLieux.setTextFill(Color.web("#202B36", 0.8));
                LabelNom.setTextFill(Color.web("#202B36", 0.8));
                LabelNom.setStyle("-fx-font-weight: bold");

                ////
                ImageView BuyProducticon;
                BuyProducticon = new ImageView(new Image("/edu/pidev/images/tickets.png"));
                BuyProducticon.setFitHeight(30);
                BuyProducticon.setFitWidth(30);
                HBox managebtn = new HBox(BuyProducticon);

                root.getChildren().addAll(LabelNom, LabeDate, LabelLieux, LabelType, LabelDesc, managebtn);
                root.setAlignment(Pos.CENTER);
                mansoryPane.getChildren().add(root);

                BuyProducticon.setOnMouseClicked((MouseEvent event) -> {
                    System.out.println("icon NotVerified is pressed !");
                    // System.out.println("" + id);
                    Reservation.setIdEvent(id);
                    Reservation.setIdUser(idUser);
                    ServiceReservation.Ajouter(Reservation);

                    //// Send email
                    long millis = System.currentTimeMillis();
                    java.sql.Date DateLyoum = new java.sql.Date(millis);
                    String emailUser = ServiceReservation.GetEmailUser(idUser);
                    System.out.println("" + emailUser);
                    mailEvent.sendMail("vous avez Réserver à l'evenement " + nom, emailUser);
                    ///
                    LoadCardProduits();

                });

            }

        }

    }

    @FXML
    private void GOToBack(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackEvent.fxml"));
        stck.getChildren().removeAll();
        stck.getChildren().setAll(menu);
    }

}
