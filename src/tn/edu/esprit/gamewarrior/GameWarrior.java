/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gamewarrior;

import java.sql.SQLException;
import tn.edu.esprit.model.Reclamation;
import tn.edu.esprit.model.Typereclamation;
import tn.edu.esprit.service.Iservices;
import tn.edu.esprit.service.ServiceReclamation;
import tn.edu.esprit.service.ServiceTypereclamation;

/**
 *
 * @author admin
 */
public class GameWarrior {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        System.out.println("************ Reclamation ************");

        Iservices Ir= new ServiceReclamation();
          
        // supprimer Reclamation
          //Ir.supprimer(26);
        
        // ajouter Reclamation
        Reclamation R=new Reclamation(2,2,1,"llllllakremmmm");
          //Ir.ajouter(R);
         
        // modifier Reclamation
            //R.setId(31);
            //R.setNiveau(2);
            //Ir.modifier(R);
          
        // afficher Reclamations
          //System.out.println(Ir.afficher());
        
        
        System.out.println("************ Type Reclamations ************");
        Iservices It=new ServiceTypereclamation();
        // supprimer
        //It.supprimer(1);
        
        // ajoute
        Typereclamation T=new Typereclamation("Systeme");
        //It.ajouter(T);
        
        // modifier 
          //T.setId(5);
          //T.setNiveau("Systeme");
          //It.modifier(T);
          
        // affichage 
        //System.out.println(It.afficher());
      
        
        System.out.println("********** Stat ************");
        ServiceReclamation Sr=new ServiceReclamation();
        
        System.out.println("Niveau 0 : " + Sr.CountN0());
        System.out.println("Niveau 1 : " + Sr.CountN1());
        System.out.println("Niveau 2 : " + Sr.CountN2());

        
        int somme;
        somme=Sr.CountN0()+Sr.CountN1()+Sr.CountN2();
        
        System.out.println("Niveau 0 : "+(Sr.CountN0()*100)/somme+" %");
        System.out.println("Niveau 1 : "+(Sr.CountN1()*100)/somme+" %");
        System.out.println("Niveau 2 : "+(Sr.CountN2()*100)/somme+" %");

    }
    
}
