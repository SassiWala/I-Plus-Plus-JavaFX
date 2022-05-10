/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.util.List;

/**
 *
 * @author 21695
 */
public interface IService<T> {
   
    
    public void ajouter(T p);
    public void modifier(T p);
    public void supprimer(int id);
    public List<T> getAll();
    


}
