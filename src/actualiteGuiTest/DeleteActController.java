/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actualiteGuiTest;

import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javaServices.actualiteCRUD;
import javaServices.categorieCRUD;
import javaentity.actualite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;




/**
 * FXML Controller class
 *
 * @author sahra
 */
public class DeleteActController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private Button btndelete;
    @FXML
    private ListView<actualite> listviewdelete;
     ObservableList<actualite> alist= FXCollections.observableArrayList();
    @FXML
    private Button btnhome;
    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    private Button btntri;


   
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualiteCRUD ac = new actualiteCRUD();
        List<actualite> list = ac.afficheractualite();
        alist= FXCollections.observableArrayList(list);
        listviewdelete.setItems(alist);
    }    

    @FXML
    private void DeleteAct(ActionEvent event) {
         if(tfid.getText().length()<=0 )
        { 
            JOptionPane.showMessageDialog(null,"You need to select first");
               }
         else{
                         JOptionPane.showMessageDialog(null,"Successfully deleted");

int ID =Integer.parseInt(tfid.getText());
          actualiteCRUD ac = new actualiteCRUD();
         ac.DeleteActualite(ID);
              String title = "Congratulations sir";
        String message = "You've successfully deleted news";
        Notification notification = Notifications.SUCCESS;
        
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotification(notification);
        tray.showAndWait();
         }
    }

    @FXML
    private void idfilled(MouseEvent event) {
         actualite a =new actualite();
        a=listviewdelete.getSelectionModel().getSelectedItem();
        tfid.setText(String.valueOf(a.getId_actualite()));
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
           root = FXMLLoader.load(getClass().getResource("/actualiteGuiTest/Actualites.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void tri(ActionEvent event) {
        actualiteCRUD acd= new actualiteCRUD ();
        List<actualite> list = acd.tri();
        alist= FXCollections.observableArrayList(list);
        listviewdelete.setItems(alist);
    }
    
    
    
}
