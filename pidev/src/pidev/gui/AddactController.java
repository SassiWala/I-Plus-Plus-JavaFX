/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import pidev.PidevFXMain;
import pidev.entities.actualite;
import pidev.entities.categorie;
import pidev.services.actualiteCRUD;
import pidev.services.categorieCRUD;
import pidev.utils.SendMail;





/**
 * FXML Controller class
 *
 * @author sahra
 */
public class AddactController implements Initializable {
 @FXML
    private TextField tftitre;

    @FXML
    private TextField tfdescription;

    @FXML
    private TextField tfetendu;

    @FXML
    private TextField tfimage;

    private TextField tfrating;

    private TextField tfvu;

    @FXML
    private Button btnajouter;
    
    @FXML
    private ComboBox<String> CBcategorie;
    ObservableList<String> ulist= FXCollections.observableArrayList();
    @FXML
    private ImageView imageview;
    @FXML
    private Button btnimg;
    Parent root;
    Stage stage;
    Scene scene;
    ValidationSupport validationSupport = new ValidationSupport();
    //create a file choser
   final FileChooser fileChooser = new FileChooser();
    @FXML
    private Button btnhome;
 /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  validationSupport.setErrorDecorationEnabled(false);
        validationSupport.registerValidator(tftitre, Validator.createEmptyValidator("Text is required"));
            validationSupport.registerValidator(tfdescription, Validator.createEmptyValidator("Text is required"));
        validationSupport.registerValidator(tfetendu, Validator.createEmptyValidator("Text is required"));
        validationSupport.registerValidator(CBcategorie, Validator.createEmptyValidator("Categorie is required"));

    }    
    @FXML
    void ajoutactualite(ActionEvent event) throws IOException {
        
        if(tftitre.getText().length()<=0 || tfdescription.getText().length()<=0 || tfetendu.getText().length()<=0)
        { 
            JOptionPane.showMessageDialog(null,"One or more field are empty");
               }
        else if(tfimage.getText().length()<=0){
           JOptionPane.showMessageDialog(null,"you need to upload an image");

        }
        else{
        JOptionPane.showMessageDialog(null,"Successfully added");
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
        ac.ajoutactualite2(a);
        
        /*QRcode qrc=new QRcode();
        qrc.QRcode();
        */
       
       
        String message="Bonjour Mr/Mme "
                + "Merci de consulter notre application ou page web pour voir notre nouvelles actualitées";
        ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
        emailExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    SendMail.sendMail ("sahraouirayen3@gmail.com","Une nouvelle actualite a ete ajouter ",message);
                } catch (AddressException e) {
                    e.printStackTrace ();
                } catch (MessagingException e) {
                    e.printStackTrace ();
                }
            }
        });
        emailExecutor.shutdown();
        
             
                }
    }

    @FXML
    private void populateCAT(MouseEvent event) {
                categorieCRUD cc=new categorieCRUD();
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
    private void img(ActionEvent event) {
        
        
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
    /*   QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "qr code";
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            Logger.getLogger(JavaFX_QRCodeWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
          
       StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(qrView);
               actualite.getChildren().add(stackPane);*/

    @FXML
    private void home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardFXML.fxml"));
        Parent fxml = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         DashboardFXMLController dbc = loader.getController();
        dbc.show("Actualites");
                dbc.show_data(String.valueOf(PidevFXMain.id_users));

        scene = new Scene(fxml);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    
       
}
   
    
