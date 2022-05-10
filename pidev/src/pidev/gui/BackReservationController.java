/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import animatefx.animation.Shake;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import pidev.entities.Reservation;
import pidev.services.ServiceReservation;
import pidev.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author karim
 */
public class BackReservationController implements Initializable {

    @FXML
    private StackPane stck;
    @FXML
    private TextField txtSearch;
    @FXML
    private Label title;
    @FXML
    private TableColumn<Reservation, Integer> col_id;
    @FXML
    private ComboBox<String> ComboUser;
    @FXML
    private TableView<Reservation> TableView;
    @FXML
    private ComboBox<String> ComboEvent;
    @FXML
    private TableColumn<Reservation, Integer> col_User;
    @FXML
    private TableColumn<Reservation, Integer> col_Event;
    Reservation rec = new Reservation();
    ServiceReservation work = new ServiceReservation();
    Desktop desktop = Desktop.getDesktop();
    private ObservableList<Reservation> ListReservations;
    @FXML
    private Label ControleUser;
    @FXML
    private Label ControleEvent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        LoadTableReservation();
        RemplirCombouser();
        RemplirEvent();
        getselected();
    }

    public void LoadTableReservation() {

        List<Reservation> listee = new ArrayList<>();
        listee = work.Afficher();
        ObservableList<Reservation> Liste = FXCollections.observableArrayList(listee);

        col_id.setCellValueFactory(new PropertyValueFactory<>("idReservation"));
        col_User.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        col_Event.setCellValueFactory(new PropertyValueFactory<>("idEvent"));

        ListReservations = FXCollections.observableArrayList(listee);
        TableView.setItems(ListReservations);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////// Search
        //  Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Reservation> filteredData = new FilteredList<>(ListReservations, b -> true);

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
                if (String.valueOf(data.getIdReservation()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(data.getIdUser()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(data.getIdEvent()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match

                }
            });
        });

        //  Wrap the FilteredList in a SortedList.
        SortedList<Reservation> sortedData = new SortedList<>(filteredData);
        //  Bind the SortedList comparator to the TableView comparator.
        // Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(TableView.comparatorProperty());
        //  Add sorted (and filtered) data to the table.
        TableView.setItems(sortedData);
        //////////////////////////////////////////////////////
    }

    private void getselected() {

        TableView.setOnMouseClicked(ev -> {
            if (TableView.getSelectionModel().getSelectedItem() != null) {
                if (Integer.valueOf(TableView.getSelectionModel().getSelectedItem().getIdEvent()) != null) {
                    ComboEvent.setValue(String.valueOf(TableView.getSelectionModel().getSelectedItem().getIdEvent()));
                }
                if (Integer.valueOf(TableView.getSelectionModel().getSelectedItem().getIdUser()) != null) {
                    ComboUser.setValue(GetEmail(TableView.getSelectionModel().getSelectedItem().getIdUser()));
                }
            }
        }
        );

    }

    @FXML
    private void btnCreateClicked(MouseEvent event) {
        if (ComboUser.getSelectionModel().getSelectedItem() == null) {
            ComboUser.requestFocus();
            shake(ComboUser);
            ControleUser.setVisible(true);
            return;
        }
        if (ComboEvent.getSelectionModel().getSelectedItem() == null) {
            ComboEvent.requestFocus();
            shake(ComboEvent);
            ControleEvent.setVisible(true);
            return;
        }

        ////
        rec.setIdEvent(Integer.valueOf(ComboEvent.getSelectionModel().getSelectedItem()));
        rec.setIdUser(GetidUser(ComboUser.getSelectionModel().getSelectedItem()));
        work.Ajouter(rec);
        ////
        LoadTableReservation();
        ComboUser.setValue(null);
        ComboEvent.setValue(null);
        //
        ControleUser.setVisible(false);
        ControleEvent.setVisible(false);
    }

    @FXML
    private void btnModifierClicked(MouseEvent event) {
        int id;

        if (TableView.getSelectionModel().getSelectedItem() != null) {
            id = TableView.getSelectionModel().getSelectedItem().getIdReservation();
            {
                if (ComboUser.getSelectionModel().getSelectedItem() == null) {
                    ComboUser.requestFocus();
                    shake(ComboUser);
                    ControleUser.setVisible(true);
                    return;
                }
                if (ComboEvent.getSelectionModel().getSelectedItem() == null) {
                    ComboEvent.requestFocus();
                    shake(ComboEvent);
                    ControleEvent.setVisible(true);
                    return;
                }
                ////
                rec.setIdReservation(id);
                rec.setIdEvent(Integer.valueOf(ComboEvent.getSelectionModel().getSelectedItem()));
                rec.setIdUser(GetidUser(ComboUser.getSelectionModel().getSelectedItem()));
                work.modifier(rec);

                ////
                LoadTableReservation();
                ComboUser.setValue(null);
                ComboEvent.setValue(null);
                //
                ControleUser.setVisible(false);
                ControleEvent.setVisible(false);
            }
        }
    }

    @FXML
    private void btnSupprimerClicked(MouseEvent event) {

        int id;
        id = TableView.getSelectionModel().getSelectedItem().getIdReservation();
        rec.setIdReservation(id);
        work.supprimer(rec);
        ////
        LoadTableReservation();
        ComboUser.setValue(null);
        ComboEvent.setValue(null);
    }

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

    @FXML
    private void GeneratePdf(MouseEvent event) throws IOException {

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(String.valueOf("Reservation" + ".pdf")));//yyyy-MM-dd
            document.open();
            Paragraph ph1 = new Paragraph("Reservation");
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(3);

            //On créer l'objet cellule.
            PdfPCell cell;

            //contenu du tableau.
            table.addCell("id");
            table.addCell("User");
            table.addCell("Event");

            //     table.addCell("Image : ");
            work.Afficher().forEach(e
                    -> {
                table.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(String.valueOf(e.getIdReservation()));
                table.addCell(String.valueOf(e.getIdUser()));
                table.addCell(String.valueOf(e.getIdEvent()));

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
        File file = new File("Reservation" + ".pdf");
        if (file.exists()) //checks file exists or not  
        {
            desktop.open(file); //opens the specified file   
        }

    }

    private void RemplirEvent() {
        try {

            String requetee = "SELECT id_event FROM evenement";
            Statement pstt = DataSource.getinstance().getCns().createStatement();
            ResultSet rs = pstt.executeQuery(requetee);
            while (rs.next()) {
                ComboEvent.getItems().addAll(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void RemplirCombouser() {
        try {

            String requetee = "SELECT email FROM user";
            Statement pstt = DataSource.getinstance().getCns().createStatement();
            ResultSet rs = pstt.executeQuery(requetee);
            while (rs.next()) {
                ComboUser.getItems().addAll(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String GetEmail(int id) {
        String email = null;
        try {

            String requetee = "SELECT email FROM user WHERE id_user = '" + id + "'";
            Statement pstt =DataSource.getinstance().getCns().createStatement();
            ResultSet rs = pstt.executeQuery(requetee);
            while (rs.next()) {
                email = rs.getString(1);
                break;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return email;
    }

    private int GetidUser(String email) {
        int id = 0;
        try {

            String requetee = "SELECT id_user FROM user WHERE email = '" + email + "'";
            Statement pstt =DataSource.getinstance().getCns().createStatement();
            ResultSet rs = pstt.executeQuery(requetee);
            while (rs.next()) {
                id = rs.getInt(1);
                break;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public static void shake(Node node) {
        new Shake(node).play();
    }
}
