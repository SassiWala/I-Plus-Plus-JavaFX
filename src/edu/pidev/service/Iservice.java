/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.service;

import edu.pidev.entities.Evenement;
import javafx.collections.ObservableList;

/**
 *
 * @author karim
 */
public interface Iservice <E>{
      public void Ajouter(Evenement E);
      public  ObservableList<Evenement> Afficher();
      public void supprimer(Evenement E);
      public void modifier(Evenement E );
    
}
