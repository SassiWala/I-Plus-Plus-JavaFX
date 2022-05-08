/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actualiteGuiTest;

import Tools.PDF;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaServices.actualiteCRUD;
import javaServices.categorieCRUD;
import javaentity.actualite;
import javaentity.categorie;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author sahra
 */
public class UpdateActController implements Initializable {

    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfetendu;
    @FXML
    private TextField tfimage;
    @FXML
    private Button btnmodifier;
    @FXML
    private ComboBox<String> CBcategorie;
    ObservableList<String> ulist= FXCollections.observableArrayList();
    @FXML
    private ListView<actualite> listviewupdate;
    ObservableList<actualite> alist= FXCollections.observableArrayList();
    @FXML
    private TextField tfid;
    @FXML
    private Button pdf;
    @FXML
    private TextField tfsearch;
        ValidationSupport validationSupport = new ValidationSupport();
    @FXML
    private ImageView imageview;
    @FXML
    private Button selectimg;
       final FileChooser fileChooser = new FileChooser();
    @FXML
    private Button btnhome;
    Stage stage;
    Scene scene;
    Parent root;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        validationSupport.registerValidator(tftitre, Validator.createEmptyValidator("Text is required"));
            validationSupport.registerValidator(tfdescription, Validator.createEmptyValidator("Text is required"));
        validationSupport.registerValidator(tfetendu, Validator.createEmptyValidator("Text is required"));
        validationSupport.registerValidator(CBcategorie, Validator.createEmptyValidator("Categorie is required"));

        actualiteCRUD ac = new actualiteCRUD();
        List<actualite> list = ac.afficheractualite();
        alist= FXCollections.observableArrayList(list);
        listviewupdate.setItems(alist);
    }    

    @FXML
    private void modifieractualite(ActionEvent event) {
          
        if(tftitre.getText().length()<=0 || tfdescription.getText().length()<=0 || tfetendu.getText().length()<=0)
        { 
            JOptionPane.showMessageDialog(null,"One or more field are empty");
               }
        else if(tfimage.getText().length()<=0){
           JOptionPane.showMessageDialog(null,"you need to upload an image");

        }
        else{
        JOptionPane.showMessageDialog(null,"successfully updated");
        String Titre = tftitre.getText();
        String Description =tfdescription.getText();
        String Etendu = tfetendu.getText();
        String image = tfimage.getText();
        String nomCat=CBcategorie.getValue();
        categorieCRUD cc=new categorieCRUD();
        categorie c=cc.findCategorie(nomCat);
        System.out.println(nomCat);
        actualite a = new actualite(Titre,Description,Etendu,image,c);
        actualiteCRUD ac = new actualiteCRUD();
        ac.UpdateActualite(a,Integer.valueOf(tfid.getText()));
         

    }
    }

    @FXML
    private void populateCAT(MouseEvent event) {   categorieCRUD cc=new categorieCRUD();
        List<categorie> listC=new ArrayList<>();
        listC=cc.afficherCategorie();
        List<String> nomCats=new ArrayList<>();
        for(categorie c:listC){
            nomCats.add(c.getNom_cat_actualite());
        }
        ulist= FXCollections.observableArrayList(nomCats);
        CBcategorie.setItems(ulist);
    }
    

    @FXML
    private void fillformupdate(MouseEvent event) {
        actualite a =new actualite();
        a=listviewupdate.getSelectionModel().getSelectedItem();
        tftitre.setText(a.getTitre_actualite());
        tfdescription.setText(a.getDescription());
        tfetendu.setText(a.getEtendu());
        tfimage.setText(a.getImage_act());
        tfid.setText(String.valueOf(a.getId_actualite()));
       
        
    }

    @FXML
    private void pdfdownload(ActionEvent event) {
             
        try {
            PDF pdf=new  PDF();
            pdf.PDF();
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UpdateActController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(UpdateActController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UpdateActController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void selectimg(ActionEvent event) {
         fileChooser.setTitle("choose image");
                

        File file = fileChooser.showOpenDialog(null);
        
         fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("ImageFile","*.png","*.jpeg","*.gif","*.jpg"));
         
        tfimage.appendText(file.getName());
         //imageview=new ImageView(new Image(file.toURI().toString()));
         imageview.setImage(new Image(file.toURI().toString()));
            imageview.setFitWidth(400);
            imageview.setFitHeight(400);
            imageview.scaleXProperty();
            imageview.scaleYProperty();
            imageview.setSmooth(true);
            imageview.setCache(true);
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
    private void searching(KeyEvent event) {
        String search= tfsearch.getText();
        System.out.println(search);
        actualiteCRUD acd= new actualiteCRUD ();
        List<actualite> list = acd.Searcho(search);
        alist= FXCollections.observableArrayList(list);
        listviewupdate.setItems(alist);
    }
    
}
