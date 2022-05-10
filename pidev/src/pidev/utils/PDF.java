/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.utils;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import pidev.entities.actualite;
import pidev.services.actualiteCRUD;

//import javax.swing.text.Document;


/**
 *
 * @author user
 */
public class PDF {



  public void PDF() throws IOException, DocumentException
    {
        Document document = new Document();

        actualiteCRUD ac = new actualiteCRUD ();
        List<actualite> res=ac.afficheractualite();
        PdfWriter.getInstance(document, new FileOutputStream (new File ("actualite.pdf")));
        document.open();
       PDF pdf=new PDF();
        for (actualite o :res) {

            Paragraph actualite= new Paragraph ("Title"+ o.getTitre_actualite());
            actualite.setAlignment (Element.ALIGN_CENTER);
            document.add (actualite);
            Paragraph desc_actualite = new Paragraph ("description_actualite : " + o.getDescription());
            desc_actualite.setAlignment (Element.ALIGN_LEFT);
            Paragraph Etendu_actualite = new Paragraph ("Etendu_actualite : " + o.getEtendu());
            Etendu_actualite.setAlignment (Element.ALIGN_LEFT);
            Paragraph  image_actualite = new Paragraph (" image_actualite: " + o.getImage_act());
            image_actualite.setAlignment (Element.ALIGN_LEFT);
           

            document.add (actualite);
            document.add (desc_actualite);
            document.add (Etendu_actualite);
            document.add (image_actualite);
            




        }
        document.close ();
        Desktop.getDesktop().open(new File("actualite.pdf"));

    }

}