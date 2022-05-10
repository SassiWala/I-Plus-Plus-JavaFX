/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;



import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pidev.entities.Prod;
import pidev.services.ServiceProd;
 
/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ModifeirhotelController implements Initializable {
    @FXML
    private TextField desc;
    @FXML
    private TextField refhotel;
    @FXML
    private TextField nomhotel;
    @FXML
    private TextField villehotel;
    @FXML
    private TextField nbetoilehotel;
    @FXML
    private ImageView imgview;
    @FXML
    private Button btnimg;
    @FXML
    private Label file_path;
    @FXML
    private Button modifierbtn;
    @FXML
    private AnchorPane modifanchro;
        int idE;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
   public void selectEvenement(Prod e)
    {
//        //TextField_ID.setText(String.valueOf(e.getIdE()));
         idE=e.getId();
         refhotel.setText( e.getNom_prod());
        nomhotel.setText(e.getPrix_prod()+"");
        villehotel.setText(e.getSatut_prod()+"");
        nbetoilehotel.setText(e.getRate_prod()+"");
           file_path.setText(e.getImg_prod());
         desc.setText(e.getDescription_prod());

 
        String imageE = "file:" + e.getImg_prod();

        Image image = new Image(imageE, 110, 110, false, true);

     imgview.setImage(image);

        String path = e.getImg_prod();

       file_path.setText(path);
 

        
    }
    @FXML
    private void insertImage(ActionEvent event) {
        
        
        FileChooser open = new FileChooser();

        Stage stage = (Stage) modifanchro.getScene().getWindow();

        File file = open.showOpenDialog(stage);

        if (file != null) {

            String path = file.getAbsolutePath();

            path = path.replace("\\", "\\\\");

            file_path.setText(path);

            Image image = new Image(file.toURI().toString(), 110, 110, false, true);

            imgview.setImage(image);

        } else {

            System.out.println("NO DATA EXIST!");

        }
    }

    @FXML
    private void modifierhotel(ActionEvent event) {
        
              if(refhotel.getText().isEmpty() | nomhotel.getText().isEmpty()
                | villehotel.getText().isEmpty()
                
                | nbetoilehotel.getText().isEmpty()
                
                
                |imgview.getImage()==null ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("i plus plus :: Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs sont requis !!");
            alert.showAndWait();
        }else{
             Prod H = new Prod(idE,refhotel.getText(),((int)Float.parseFloat(nomhotel.getText())),((int)Float.parseFloat(villehotel.getText())),((int)Float.parseFloat(nbetoilehotel.getText())),desc.getText(),file_path.getText());
                  ServiceProd sp = new ServiceProd();
         
            sp.modifier(H);
             System.out.println("modification avec succee");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("iplusplus:: produit");
            alert.setHeaderText(null);
            alert.setContentText("produit modifié !!");
            alert.showAndWait();
             //showUser();
            
            //clear();
        }
    }
    
}
