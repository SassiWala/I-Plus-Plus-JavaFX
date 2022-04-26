/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import com.itextpdf.text.DocumentException;
import tn.edu.esprit.services.Pdf;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entities.Commande;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CommandeServices;
/**
 * FXML Controller class
 *
 * @author yassi
 */
public class CommandeFXMLController implements Initializable {

    @FXML
    private TableView<Commande> tvCommande;
    @FXML
    private TableColumn<?, ?> clmDate;
    @FXML
    private TableColumn<?, ?> clmMontant;
    @FXML
    private TableColumn<?, ?> clmUser;
    private TextField tfSearch;
    @FXML
    private TextField recherche;
    private final ObservableList<Commande> data = FXCollections.observableArrayList();
    @FXML
    private Button btnD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCommande();
    }    
    public void showCommande(){
          CommandeServices ps=new CommandeServices();
          ObservableList<Commande> list=FXCollections.observableArrayList(ps.getAll());
          data.addAll(list);
            clmDate.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
            clmMontant.setCellValueFactory(new PropertyValueFactory<>("montantCommande"));
            clmUser.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
            
             tvCommande.setItems(data);
         RechercheAV();
    }
   



                 public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Commande> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(tmp -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (tmp.getNomUser().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(tmp.getMontantCommande()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Commande> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvCommande.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvCommande.setItems(sortedData);
    }

    @FXML
    private void ImprimPDF(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException {
            ObservableList<Commande> Single = tvCommande.getSelectionModel().getSelectedItems();
                         Commande A = Single.get(0);

                Pdf p = new Pdf();
                p.add("Commande",String.valueOf(A.getMontantCommande()),String.valueOf( A.getDateCommande()),A.getNomUser());

    }

    @FXML
    private void Details(ActionEvent event) throws IOException {
        Commande p=tvCommande.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsFXML.fxml"));
        Parent root = loader.load();
        btnD.getScene().setRoot(root);
        DetailsFXMLController dc=loader.getController();
        dc.setIdCmd(p.getId_commande());
        System.err.println(p.getId_commande()+"eeeeehhhh");
        dc.showDetails(p.getId_commande());
    }


    
}
