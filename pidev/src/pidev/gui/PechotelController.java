/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.controlsfx.control.Notifications;
import pidev.entities.Prod;
import pidev.entities.Promo;
import pidev.services.ServiceProd;
import pidev.services.ServicePromo;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class PechotelController implements Initializable {

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
    private AnchorPane main;
    @FXML
    private Label file_path;
    @FXML
    private Button addbutton;
    @FXML
    private Button annuler;
    @FXML
    private AnchorPane listhotelpanel;
    private List<Prod> listhotel = new ArrayList();
    Prod ho;
    private EListener listen;
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane listhotelpanel1;
    @FXML
    private TextField taux;
    @FXML
    private Button ajouterpromo;
    @FXML
    private DatePicker date_exp;
    @FXML
    private TableView<Promo> tableau;
    @FXML
    private TableColumn<Promo, String> id;
    @FXML
    private TableColumn<Promo, String> pourc;
    @FXML
    private TableColumn<Promo, String> dateexp;
    ServicePromo ps = new ServicePromo();
    @FXML
    private Button suppprom;
    @FXML
    private Button modifprom;
    @FXML
    private Label idpromo;
    @FXML
    private ComboBox<String> combo_promo;
    @FXML
    private TextField quantite;

    /**
     * Initializes the controller class.
     */
    @FXML

    private void ajouterhotel() throws SQLException, MalformedURLException, IOException {
        ServiceProd sp = new ServiceProd();

        if (refhotel.getText().isEmpty() | nomhotel.getText().isEmpty()
                | villehotel.getText().isEmpty()
                | nbetoilehotel.getText().isEmpty()
                | imgview.getImage() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("i plus plus :: Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs sont requis !!");
            alert.showAndWait();
        } else {
            Prod H;

            H = new Prod(refhotel.getText(), ((int) Float.parseFloat(nomhotel.getText())), ((int) Float.parseFloat(villehotel.getText())), ((int) Float.parseFloat(nbetoilehotel.getText())), desc.getText(), file_path.getText(), ps.getPromoById(combo_promo.getValue()), Integer.valueOf(quantite.getText()));

            sp.ajouter(H);
            sp.sendSms();
            System.out.println("ajout avec succee");

            /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("i plus plus:: produit");
            alert.setHeaderText(null);
            alert.setContentText("produit ajouté !!");
            alert.showAndWait();
            clear();*/
            ajoutNotif();
            //showUser();

            //clear();
        }

    }

    @FXML
    public void InsertImage() {
        FileChooser open = new FileChooser();
        Stage stage = (Stage) main.getScene().getWindow();
        File file = open.showOpenDialog(stage);
        if (file != null) {
            String path = file.getAbsolutePath();
            path = path.replace("\\", "\\\\");

            file_path.setText(path);
            Image image = new Image(file.toURI().toString(), 110, 110, false, true);
            imgview.setImage(image);
        } else {
            System.out.println("No FILE EXIST");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceProd sh = new ServiceProd();
        Prod prods = new Prod();
        EListener Listener;
        List<Prod> ListeDesEvent = sh.getAll();

        //TextField_ID.setDisable(true);
        grid.getChildren().clear();
        if (ListeDesEvent.size() > 0) {
            prods = ListeDesEvent.get(0);
            //setChosenTable_Resto(ListeDesEvent.get(0));
            Listener = new EListener() {

                public void onClickListener(Prod h) {
                    setChooseEvenement(h);
                }

                public void onClickListener1(Promo h) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

            };
        }
        int col = 0;
        int row = 0;

        try {

            for (Prod e : ListeDesEvent) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Itemhotel.fxml"));
                AnchorPane anchorPane = fxmlloader.load();

                ItemhotelController itemControler = fxmlloader.getController();
                itemControler.SetData(e);
                //itemControler.SetData(ev.get(i),listen);

                if (col == 2) {
                    col = 0;
                    row++;
                }
                grid.add(anchorPane, col++, row);
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(238);
                grid.setMaxWidth(Region.USE_PREF_SIZE);//set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(297);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                grid.setVgap(110);
                grid.setHgap(250);
                GridPane.setMargin(anchorPane, new Insets(50, 50, 50, 50));
            }
        } catch (Exception e) {
            System.out.println("exception" + e.getMessage());

        }

        List produits = ps.getAll();
        ObservableList list = FXCollections.observableArrayList(produits);
        tableau.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        pourc.setCellValueFactory(new PropertyValueFactory<>("pourcent_promo"));
        dateexp.setCellValueFactory(new PropertyValueFactory<>("date_exp"));

        ObservableList<Promo> dataList = FXCollections.observableArrayList(produits);
        tableau.setItems(dataList);
        FilteredList<Promo> filteredData = new FilteredList<>(dataList, b -> true);
        for (int i = 0; i < dataList.size(); i++) {
            Promo pom = dataList.get(i);
            combo_promo.getItems().add(String.valueOf(pom.getPourcent_promo()));
        }
        // TODO

    }

    @FXML
    private void clear() {
        refhotel.setText("");
        nomhotel.setText("");
        villehotel.setText("");
        nbetoilehotel.setText("");

        imgview.setImage(null);
    }

    private void refresh() {
        List produits = ps.getAll();
        ObservableList list = FXCollections.observableArrayList(produits);
        tableau.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        pourc.setCellValueFactory(new PropertyValueFactory<>("pourcent_promo"));
        dateexp.setCellValueFactory(new PropertyValueFactory<>("date_exp"));

        ObservableList<Promo> dataList = FXCollections.observableArrayList(produits);
        tableau.setItems(dataList);
        FilteredList<Promo> filteredData = new FilteredList<>(dataList, b -> true);
    }

    private void setChooseEvenement(Prod e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText("selectionné!");
        alert.showAndWait();
    }

    @FXML
    private void ajouterpromo(ActionEvent event) {

        if (taux.getText().isEmpty() | date_exp.getEditor().getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("i plus plus :: Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs sont requis !!");
            alert.showAndWait();
        } else {
            Promo p = new Promo(((int) Float.parseFloat(taux.getText())), date_exp.getEditor().getText());

            ServicePromo sp = new ServicePromo();

            sp.ajouter(p);
            System.out.println("ajout avec succee");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("i plus plus:: produit");
            alert.setHeaderText(null);
            alert.setContentText("produit ajouté !!");
            alert.showAndWait();
            clear();
            refresh();
            //showUser();

            //clear();
        }

    }

    @FXML
    private void supprimerpromo(ActionEvent event) {
        Promo p = tableau.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Trood :: Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Séléctionnez une ligne !!");
            alert.showAndWait();
        } else {
            ps.supprimer(p.getId());
            refresh();
        }
    }

    @FXML
    private void modifierpromo(ActionEvent event) {
        if (taux.getText().isEmpty() | date_exp.getEditor().getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("i plus plus :: Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs sont requis !!");
            alert.showAndWait();
        } else {
            Promo p = new Promo(((int) Float.parseFloat(idpromo.getText())), ((int) Float.parseFloat(taux.getText())), date_exp.getEditor().getText());

            ServicePromo sp = new ServicePromo();

            sp.modifier(p);
            System.out.println("ajout avec succee");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("i plus plus:: produit");
            alert.setHeaderText(null);
            alert.setContentText("promo modifier !!");
            alert.showAndWait();
            clear();
            refresh();
            //showUser();

            //clear();
        }

    }

    @FXML
    private void selectpromo(MouseEvent event) {
        Promo p = tableau.getSelectionModel().getSelectedItem();
        taux.setText(p.getId() + "");
        date_exp.getEditor().setText(p.getDate_exp());
        idpromo.setText(p.getId() + "");

    }

    @FXML
    private void produits_pdf(ActionEvent event) throws FileNotFoundException, DocumentException {
        ServiceProd sp = new ServiceProd();
        sp.liste_produit();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("i plus plus:: produit");
        alert.setHeaderText(null);
        alert.setContentText("Vous etes sur le point de generer un fichier pdf !!");
        ajoutNotifPdf();
    }

    public void ajoutNotif() {
        Image img = new Image("pidev/images/ajouter.png");
        Notifications notif = Notifications.create().title("Succés").text("Produit Ajoutée").graphic(new ImageView(img)).hideAfter(Duration.seconds(10)).position(Pos.TOP_RIGHT);
        notif.show();
    }

    public void ajoutNotifPdf() {
        Image img = new Image("pidev/images/pdf.png");
        Notifications notif = Notifications.create().title("Succés").text("Pdf enregistré").graphic(new ImageView(img)).hideAfter(Duration.seconds(10)).position(Pos.TOP_RIGHT);
        notif.show();
    }

}
