/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui;

import animatefx.animation.Shake;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.pidev.entities.Evenement;
import edu.pidev.service.ServiceEvenement;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author karim
 */
public class BackEventController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tflieu;
    @FXML
    private TextField tfdesc;
    @FXML
    private DatePicker tfdate;
    @FXML
    private ComboBox<String> tftype;
    @FXML
    private Label title;
    @FXML
    private TableColumn<Evenement, Integer> col_id;
    @FXML
    private TableColumn<Evenement, String> col_nom;
    @FXML
    private TableColumn<Evenement, Date> col_date;
    @FXML
    private TableColumn<Evenement, String> col_lieux;
    @FXML
    private TableColumn<Evenement, String> col_description;
    @FXML
    private TableColumn<Evenement, String> col_type;
    @FXML
    private TableView<Evenement> TableViewEvent;
    @FXML
    private TextField txtSearch;
    private ObservableList<Evenement> ListEvenements;

    Evenement rec = new Evenement();
    ServiceEvenement work = new ServiceEvenement();
    Desktop desktop = Desktop.getDesktop();
    @FXML
    private StackPane stck;
    @FXML
    private Label ControleNom;
    @FXML
    private Label ControleLieux;
    @FXML
    private Label ControleDate;
    @FXML
    private Label ControleDesc;
    @FXML
    private Label ControleType;

    /**
     * Initializes the controller class.
     */
//    void create(ActionEvent event) {
//      /*String date=tfdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));*/
//         Date date=Date.valueOf(tfdate.getValue());
//         ServiceEvenement ev1= new ServiceEvenement();
//
//           // ev1.Ajouter(new Evenement(tfnom.getText(), tflieu.getText(), tfdesc.getText(), tftype.getCellFactory(),tfdate.get));
//            JOptionPane.showMessageDialog(null, "Evenement ajoutée !");
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        LoadTableEvenementS();
        getselected();
        tftype.getItems().addAll("Sport", "Dance", "Camping");

    }

    @FXML
    private void btnCreateClicked(MouseEvent event) {

        if (tfnom.getText().isEmpty()) {
            tfnom.requestFocus();
            shake(tfnom);
            ControleNom.setVisible(true);
            return;
        }
        if (tflieu.getText().isEmpty()) {
            tflieu.requestFocus();
            shake(tflieu);
            ControleLieux.setVisible(true);
            return;
        }

        if (tfdesc.getText().isEmpty()) {
            tfdesc.requestFocus();
            shake(tfdesc);
            ControleDesc.setVisible(true);
            return;
        }
        if (tfdate.getEditor().getText().isEmpty()) {
            tfdate.requestFocus();
            shake(tfdate);
            ControleDate.setVisible(true);
            return;
        }

        if (tftype.getSelectionModel().getSelectedItem() == null) {
            tftype.requestFocus();
            shake(tftype);
            ControleType.setVisible(true);
            return;
        }

        ////
        rec.setNomEvent(tfnom.getText());
        rec.setDate(java.sql.Date.valueOf(tfdate.getValue()));
        rec.setLieu(tflieu.getText());
        rec.setDescEvent(tfdesc.getText());
        rec.setType(tftype.getSelectionModel().getSelectedItem());
        work.Ajouter(rec);

        ////
        LoadTableEvenementS();
        tfnom.clear();
        tflieu.clear();
        tfdesc.clear();
        tfdate.setValue(null);
        tftype.setValue(null);
        ///
        ControleNom.setVisible(false);
        ControleLieux.setVisible(false);
        ControleDesc.setVisible(false);
        ControleDate.setVisible(false);
        ControleType.setVisible(false);

    }

    private void getselected() {

        TableViewEvent.setOnMouseClicked(ev -> {
            if (TableViewEvent.getSelectionModel().getSelectedItem() != null) {
                if (TableViewEvent.getSelectionModel().getSelectedItem().getNomEvent() != null) {
                    tfnom.setText(TableViewEvent.getSelectionModel().getSelectedItem().getNomEvent());
                }
                if (TableViewEvent.getSelectionModel().getSelectedItem().getLieu() != null) {
                    tflieu.setText(TableViewEvent.getSelectionModel().getSelectedItem().getLieu());
                }
                if (TableViewEvent.getSelectionModel().getSelectedItem().getDescEvent() != null) {
                    tfdesc.setText(TableViewEvent.getSelectionModel().getSelectedItem().getDescEvent());
                }

                if (TableViewEvent.getSelectionModel().getSelectedItem().getDate() != null) {
                    tfdate.setValue(TableViewEvent.getSelectionModel().getSelectedItem().getDate().toLocalDate());
                }

                if (TableViewEvent.getSelectionModel().getSelectedItem().getType() != null) {
                    tftype.setValue(TableViewEvent.getSelectionModel().getSelectedItem().getType());
                }
            }
        }
        );

    }

    @FXML
    private void btnModifierClicked(MouseEvent event) {

        int id;

        if (TableViewEvent.getSelectionModel().getSelectedItem() != null) {
            id = TableViewEvent.getSelectionModel().getSelectedItem().getIdEvent();
            {

                if (tfnom.getText().isEmpty()) {
                    tfnom.requestFocus();
                    shake(tfnom);
                    ControleNom.setVisible(true);
                    return;
                }
                if (tflieu.getText().isEmpty()) {
                    tflieu.requestFocus();
                    shake(tflieu);
                    ControleLieux.setVisible(true);
                    return;
                }

                if (tfdesc.getText().isEmpty()) {
                    tfdesc.requestFocus();
                    shake(tfdesc);
                    ControleDesc.setVisible(true);
                    return;
                }
                if (tfdate.getEditor().getText().isEmpty()) {
                    tfdate.requestFocus();
                    shake(tfdate);
                    ControleDate.setVisible(true);
                    return;
                }

                if (tftype.getSelectionModel().getSelectedItem() == null) {
                    tftype.requestFocus();
                    shake(tftype);
                    ControleType.setVisible(true);
                    return;
                }

                rec.setIdEvent(id);
                rec.setNomEvent(tfnom.getText());
                rec.setDate(java.sql.Date.valueOf(tfdate.getValue()));
                rec.setLieu(tflieu.getText());
                rec.setDescEvent(tfdesc.getText());
                rec.setType(tftype.getSelectionModel().getSelectedItem());
                work.modifiertt(rec);

                ////
                LoadTableEvenementS();
                tfnom.clear();
                tflieu.clear();
                tfdesc.clear();
                tfdate.setValue(null);
                tftype.setValue(null);
                //
                ControleNom.setVisible(false);
                ControleLieux.setVisible(false);
                ControleDesc.setVisible(false);
                ControleDate.setVisible(false);
                ControleType.setVisible(false);
            }
        }
    }

    @FXML
    private void btnSupprimerClicked(MouseEvent event) {
        int id;
        id = TableViewEvent.getSelectionModel().getSelectedItem().getIdEvent();
        rec.setIdEvent(id);
        work.supprimer(rec);
        ////
        LoadTableEvenementS();
        tfnom.clear();
        tflieu.clear();
        tfdesc.clear();
        tfdate.setValue(null);
        tftype.setValue(null);

    }
    ///////////

    public void LoadTableEvenementS() {

        List<Evenement> listee = new ArrayList<>();
        listee = work.Afficher();
        ObservableList<Evenement> Liste = FXCollections.observableArrayList(listee);

        col_id.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_lieux.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("descEvent"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        ListEvenements = FXCollections.observableArrayList(listee);
        TableViewEvent.setItems(ListEvenements);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////// Search
        //  Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Evenement> filteredData = new FilteredList<>(ListEvenements, b -> true);

        //  Set the filter Predicate whenever the filter changes.
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(data -> {
                // If filter text is empty, display all data.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare every table columns fields with lowercase filter text
                String lowerCaseFilter = newValue.toLowerCase();

                // Filter with all table columns
                if (data.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(data.getNomEvent()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(data.getDate()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(data.getLieu()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(data.getDescEvent()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(data.getType()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match

                }
            });
        });

        //  Wrap the FilteredList in a SortedList.
        SortedList<Evenement> sortedData = new SortedList<>(filteredData);
        //  Bind the SortedList comparator to the TableView comparator.
        // Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(TableViewEvent.comparatorProperty());
        //  Add sorted (and filtered) data to the table.
        TableViewEvent.setItems(sortedData);
        //////////////////////////////////////////////////////

    }

    @FXML
    private void GeneratePdf(MouseEvent event) throws IOException {

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(String.valueOf("Evenement" + ".pdf")));//yyyy-MM-dd
            document.open();
            Paragraph ph1 = new Paragraph("Evenement");
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(6);

            //On créer l'objet cellule.
            PdfPCell cell;

            //contenu du tableau.
            table.addCell("id");
            table.addCell("Nom");

            table.addCell("Date");
            table.addCell("Lieux");
            table.addCell("desc");
            table.addCell("Type");

            //     table.addCell("Image : ");
            work.Afficher().forEach(e
                    -> {
                table.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(String.valueOf(e.getIdEvent()));
                table.addCell(String.valueOf(e.getNomEvent()));
                table.addCell(String.valueOf(e.getDate()));

                table.addCell(e.getLieu());
                table.addCell(e.getDescEvent());
                table.addCell(e.getType());
            }
            );
            document.add(ph1);
            document.add(ph2);
            document.add(table);
            //  document.addAuthor("Bike");
            // AlertDialog.showNotification("Creation PDF ", "Votre fichier PDF a ete cree avec success", AlertDialog.image_checked);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();

        ///Open FilePdf
        File file = new File("Evenement" + ".pdf");
        if (file.exists()) //checks file exists or not  
        {
            desktop.open(file); //opens the specified file   
        }
    }

    @FXML
    private void GOToFront(MouseEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("FrontEvent.fxml"));
        stck.getChildren().removeAll();
        stck.getChildren().setAll(menu);
    }

    @FXML
    private void GoToBackEvent(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackEvent.fxml"));
        stck.getChildren().removeAll();
        stck.getChildren().setAll(menu);
    }

    @FXML
    private void GoToReservationEvent(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackReservation.fxml"));
        stck.getChildren().removeAll();
        stck.getChildren().setAll(menu);
    }

    public static void shake(Node node) {
        new Shake(node).play();
    }
}
