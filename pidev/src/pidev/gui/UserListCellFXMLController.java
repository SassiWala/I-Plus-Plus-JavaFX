/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pidev.entities.User;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class UserListCellFXMLController extends ListCell<User> {

    @FXML
    private GridPane gridp;
    @FXML
    private Label lbNom;
    @FXML
    private Label lbPrenom;
    @FXML
    private Label lbEmail;
    @FXML
    private Label lbAdr;
    @FXML
    private Label lbNum;
    @FXML
    private Button btnModif;

    /**
     * Initializes the controller class.
     */
    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(User u, boolean empty) {
        super.updateItem(u, empty);

        if (empty || u == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("UserListCellFXML.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            lbNom.setText(u.getNom());
            lbEmail.setText(u.getEmail());
            lbPrenom.setText(u.getPrenom());
            lbAdr.setText(u.getAdresse());
            lbNum.setText(String.valueOf(u.getNumTel()));
            btnModif.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1) {

                    String id = String.valueOf(u.getId());
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("UserInfoBFXML.fxml"));
                    try {
                        loader.load();
                        UserInfoBFXMLController home = loader.getController();
                        home.show_data(id);
                        Parent root = loader.getRoot();
                        Scene scene = new Scene(root);
                        Stage primaryStage = new Stage();
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ListUserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            setText(null);
            setGraphic(gridp);
        }

    }

}
