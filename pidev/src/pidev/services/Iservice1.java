/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import javafx.collections.ObservableList;
import pidev.entities.Evenement;

/**
 *
 * @author karim
 */
public interface Iservice1 <E>{
      public void Ajouter(Evenement E);
      public  ObservableList<Evenement> Afficher();
      public void supprimer(Evenement E);
      public void modifier(Evenement E );
    
}
