/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import com.github.sarxos.webcam.Webcam;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import pidev.entities.User;
import pidev.services.ServiceUser;
import pidev.utils.Boxes;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class InscriptionFXMLController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMdp;
    @FXML
    private TextField tfNumTel;
    @FXML
    private Button img_up;
    @FXML
    private Label lbimg;

    private String link_picture_client = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void inscription(ActionEvent event) throws IOException, SQLException {
        ServiceUser s = new ServiceUser();
        if (tfNom.getText().length() == 0 || tfPrenom.getText().length() == 0 || tfEmail.getText().length() == 0 || tfMdp.getText().length() == 0 || tfNumTel.getText().length() == 0) {
            Boxes.alert_Box("", "Remplir tout les champs");
        } else if (s.patternMatches(tfEmail.getText()) == false || s.email_verifier(tfEmail.getText())) {
            Boxes.alert_Box("", "Email invalid");

        } else if (s.whenMatchesDigitsNumber_thenCorrect(tfNumTel.getText()) == false) {
            Boxes.alert_Box("", "le numéro de téléphone doit être des chiffres");
        }else if (s.matches_nom(tfNom.getText())==false || s.matches_prénom(tfPrenom.getText())==false) {
            Boxes.alert_Box("", "le nom ou le prénom  doit être des lettres");
        }
        else if (s.whenMatchesPassword_thenCorrect(tfMdp.getText()) == false) {
            Boxes.alert_Box("", "Le mot de passe doit contenir au moins un caractère minuscule, un caractère majuscule, un chiffre et une longueur comprise entre 8 et 20");
        } else {
            s.ajouter(new User(tfNom.getText(), tfPrenom.getText(), tfEmail.getText(), tfMdp.getText(), Integer.valueOf(tfNumTel.getText()), lbimg.getText()));
            Boxes.information_Box("", "Utilisateur ajoutée");

        }
    }

    @FXML
    private void loginShow(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));

        try {
            Parent root = loader.load();
            tfEmail.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void upload_img(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getName();
        link_picture_client = filename;
        lbimg.setText(filename);
    }

    private String code_random() {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(4);

        for (int i = 0; i < 4; i++) {

            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb 
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();

    }

    @FXML
    private void prendre_photo(ActionEvent event) {
        String code_random = code_random();

        Webcam webcam = Webcam.getDefault();
        webcam.open();
        String filename = "";
        filename = code_random + "_" + tfNom.getText() + ".jpg";
        try {
            ImageIO.write(webcam.getImage(), "JPG", new File("src/pidev/images/" + filename));
            link_picture_client = filename;
            lbimg.setText(filename);
            webcam.close();
        } catch (IOException ex) {
            Logger.getLogger(InscriptionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
