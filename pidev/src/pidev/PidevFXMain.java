/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author 21695
 */
public class PidevFXMain extends Application {
        public static int id_users;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Button btn = new Button();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/LoginFXML.fxml"));
       
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Game Warrior");
            primaryStage.setScene(scene);
            primaryStage.show();

      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
