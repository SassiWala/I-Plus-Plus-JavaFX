/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actualiteGuiTest;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaentity.actualite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static javassist.CtMethod.ConstParameter.string;
import javatools.myconnection;

/**
 * FXML Controller class
 *
 * @author sahra
 */
public class FrontController implements Initializable {
    @FXML
    private TableView<ModelTable> table;
    @FXML
    private TableColumn<ModelTable,String> col_titre;
    @FXML
    private TableColumn<ModelTable,String> col_description;
    @FXML
    private TableColumn<ModelTable,String> col_vu;
    @FXML
    private  TableColumn<ModelTable,String> col_rating;
    ObservableList<ModelTable> oblist =FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            
            List<actualite> myList =new ArrayList<>();
            
            String requete3 = "SELECT titre_actualite,description,vu,rating FROM actualite";
            Statement st = null;
            try {
                st = new myconnection().getConnection().createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ResultSet rs = null;
            
            rs = st.executeQuery(requete3);
            while (rs.next()){
            oblist.add(new ModelTable(rs.getString("titre_actualtie")) )
        }
            
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
      
        }
        
}   
        
col_titre.setCellFactory(new PropertyValueFactory<>("titre"));
col_description.setCellFactory(new PropertyValueFactory<>("description"));
col_vu.setCellFactory(new PropertyValueFactory<>("vu"));
col_rating.setCellFactory(new PropertyValueFactory<>("rating"));


    }    
    
}
