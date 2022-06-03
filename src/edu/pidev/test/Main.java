/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.test;

import edu.pidev.entities.Evenement;
import edu.pidev.entities.Reservation;
import edu.pidev.service.ServiceEvenement;
import edu.pidev.service.ServiceReservation;
import edu.pidev.utils.Myconnection;
import java.sql.Date;

/**
 *
 * @author karim
 */
public class Main {
        public static void main(String[] args) {


            Myconnection mc2 = new Myconnection(); 
            /*tester les cruds d'evenements*/
       /* ServiceEvenement ev= new ServiceEvenement();
        //Evenement e= new Evenement (26,"kkk",new Date(25-06-2022),"kkkk","x",true);
                  Evenement e= new Evenement (2,"Tournoi Fifa22",new Date(29-06-2022),"kkkk","x",true);
//Date d=Date.valueOf(LocalDate.of(0, Month.MARCH, 0))
//ev.Ajouter(e);
//ev.supprimer(e);
//ev.modifier(e);
System.out.println(ev.Afficher());*/
       /*test des cruds de reservation*/
       ServiceReservation sr=new ServiceReservation();
            //  Reservation r=new Reservation(2,5,23);

       Reservation r=new Reservation(2,13);
      // sr.Ajouter(r);
       //sr.supprimer(r);
      // sr.modifier(r);
System.out.println(sr.Afficher());
     }
}
