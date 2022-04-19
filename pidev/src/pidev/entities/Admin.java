/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

/**
 *
 * @author 21695
 */
public class Admin extends User{

    public Admin() {
    }

    public Admin(String nom, String prenom, String email, String password, int numTel) {
        super(nom, prenom, email, password, numTel);
    }

    public Admin(String nom, String prenom, String email, String password, String adresse, int numTel) {
        super(nom, prenom, email, password, adresse, numTel);
    }
    

    public Admin(int id, String nom, String prenom, String email, String password, String adresse, int codePostale, int numTel) {
        super(id, nom, prenom, email, password, adresse, codePostale, numTel);
    }

    public Admin(int id, String nom, String prenom, String email, String password, String adresse, int codePostale, int numTel, String roles) {
        super(id, nom, prenom, email, password, adresse, codePostale, numTel, roles);
    }
    
    
    
}
