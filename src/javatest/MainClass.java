/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatest;

import javaServices.actualiteCRUD;
import javaentity.actualite;
import javaServices.categorieCRUD;
import javaentity.categorie;
import javatools.myconnection;

/**
 *
 * @author sahra
 */
public class MainClass {
    public static void main(String[] args){
        //myconnection mc = new myconnection();
        actualiteCRUD acd = new actualiteCRUD();
        actualite a2 = new actualite("valo","patch","note","pic",3,5);
        //acd.ajoutactualite2(a2);
        //System.out.println(acd.afficheractualite());
       // acd.DeleteActualite(2);
       acd.UpdateActualite(a2,30);
         categorieCRUD ccd = new categorieCRUD();
        categorie c2 = new categorie("sensitive");
        //ccd.ajoutCategorie2(c2);
        //System.out.println(ccd.afficherCategorie());
       //ccd.DeleteCategorie(320);
       ccd.UpdateCategorie(c2,320);
    }    
}
