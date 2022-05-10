/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.entities.Prod;
import pidev.utils.DataSource;

/**
 *
 * @author msi
 */
public class ServiceProd implements IService<Prod> {

    Connection cnx = DataSource.getinstance().getCns();

    @Override
    public void ajouter(Prod p) {
        try {
            String req = "";
            if (p.getId_promo_id() != 0) {
                req = "INSERT INTO `prod`( `nom_prod`, `prix_prod`, `status_prod`, `rate_prod`, `description_prod`,  `img_prod`,`quantite`,`id_promo_id`) VALUES ('" + p.getNom_prod() + "','" + p.getPrix_prod() + "','" + p.getSatut_prod() + "','" + p.getRate_prod() + "','" + p.getDescription_prod() + "','" + p.getImg_prod() + "','" + p.getQuantite() + "','" + p.getId_promo_id() + "')";
            } else {
                req = "INSERT INTO `prod`( `nom_prod`, `prix_prod`, `status_prod`, `rate_prod`, `description_prod`,  `img_prod`,`quantite`) VALUES ('" + p.getNom_prod() + "','" + p.getPrix_prod() + "','" + p.getSatut_prod() + "','" + p.getRate_prod() + "','" + p.getDescription_prod() + "','" + p.getImg_prod() + "','" + p.getQuantite() + "')";
            }

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Prod created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `prod` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Prod p) {
        try {
            String req = "UPDATE `prod` SET `nom_prod` = '" + p.getNom_prod() + "', `prix_prod` = '" + p.getPrix_prod() + "',`status_prod`='" + p.getSatut_prod() + "',`rate_prod`='" + p.getRate_prod() + "',`description_prod`='" + p.getDescription_prod() + "',`img_prod`='" + p.getImg_prod() + "' WHERE `prod`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Prod> getAll() {
        List<Prod> list = new ArrayList<>();
        try {
            String req = "SELECT `id`, `nom_prod`, `prix_prod`, `status_prod`, `rate_prod`, `description_prod`, `img_prod`,`id_promo_id`,`quantite` FROM `prod` ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Prod p = new Prod(((int) Float.parseFloat(rs.getString("id"))), rs.getString("nom_prod"), ((int) Float.parseFloat(rs.getString("prix_prod"))), ((int) Float.parseFloat(rs.getString("status_prod"))), ((int) Float.parseFloat(rs.getString("rate_prod"))), rs.getString("description_prod"), rs.getString("img_prod"), rs.getInt("id_promo_id"),rs.getInt("quantite"));
                list.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            System.out.println(list);

        return list;
    }

    public List<Prod> rechercherUser(String nom) throws SQLException {
        List<Prod> users = getAll();

        List<Prod> results = new ArrayList<>();

        users.stream()
                .filter(t -> t.getNom_prod().equalsIgnoreCase(nom))
                .forEach(t -> results.add(t));
        System.out.println("results" + results);

        return results;
    }

    public void liste_produit() throws FileNotFoundException, DocumentException {

        String message = "M/MME : Yassine Khanfir";

        String file_name = "src/pidev/PDF/produits.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
        List<Prod> produits = getAll();

        PdfPTable table = new PdfPTable(3);

        PdfPCell cl = new PdfPCell(new Phrase("Nom"));
        table.addCell(cl);
        PdfPCell cl1 = new PdfPCell(new Phrase("Prix"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("Description"));
        table.addCell(cl2);
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < produits.size(); i++) {
            table.addCell(produits.get(i).getNom_prod());
            table.addCell(String.valueOf(produits.get(i).getPrix_prod()));
            table.addCell(produits.get(i).getDescription_prod());

        }
        document.add(table);

        document.close();

    }
     public void sendSms() throws UnsupportedEncodingException, MalformedURLException, IOException {

        String AUTH_TOKEN = "01be7c8cab5b1dd6409eef2cf3a1a538";
        String ACCOUNT_SID = "ACa84c9d240e9fdaf0352cd9b244c3ce77";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+21656912106"),
                new PhoneNumber("+19803855351"),
                "Produit Ajoutée").create();

        System.out.println(message.getSid());
    }

}
