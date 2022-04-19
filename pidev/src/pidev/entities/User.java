/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import java.util.Objects;

/**
 *
 * @author 21695
 */
public class User {
    private int id;
    private String nom,prenom,email,password,adresse;
    private int codePostale;
    private long numTel;
    private String roles;

    public User() {
    }

    public User(String nom, String prenom, String email, String password, String adresse, long numTel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.numTel = numTel;
    }

    public User(String nom, String prenom, String email, String password, int numTel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
    }

    public User(int id, String nom, String prenom, String adresse, int codePostale, long numTel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostale = codePostale;
        this.numTel = numTel;
    }

  

    public User(int id, String nom, String prenom, String email, String password, String adresse, int codePostale, int numTel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.codePostale = codePostale;
        this.numTel = numTel;
    }

    public User(int id, String nom, String prenom, String email, String password, String adresse, int codePostale, int numTel, String roles) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.codePostale = codePostale;
        this.numTel = numTel;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getCodePostale() {
        return codePostale;
    }

    public long getNumTel() {
        return numTel;
    }

    public String getRoles() {
        return roles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCodePostale(int codePostale) {
        this.codePostale = codePostale;
    }

    public void setNumTel(long numTel) {
        this.numTel = numTel;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", adresse=" + adresse + ", codePostale=" + codePostale + ", numTel=" + numTel + ", roles=" + roles + '}';
    }

   

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.roles, other.roles)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
