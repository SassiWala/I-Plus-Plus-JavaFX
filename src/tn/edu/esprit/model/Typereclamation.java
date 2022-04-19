/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.model;

/**
 *
 * @author admin
 */
public class Typereclamation{
    private int id;
    private String niveau;

    public Typereclamation() {
        this.id = 0;
        this.niveau = "";
    }

    
    public Typereclamation(int id, String niveau) {
        this.id = id;
        this.niveau = niveau;
    }

    public Typereclamation(String niveau) {
        this.niveau = niveau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Typereclamation{" + "id=" + id + ", niveau=" + niveau + '}';
    }
    
    
}
