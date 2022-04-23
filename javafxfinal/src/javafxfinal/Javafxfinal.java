/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxfinal;

import entities.LigneCommande;
import services.PanierServices;
import utis.MyConnection;
import entities.Commande;
import entities.Panier;
import services.CommandeServices;
import services.LigneCommandeServices;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 *
 * @author yassi
 */
public class Javafxfinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyConnection mc2 = MyConnection.getInstance();
        Panier p1=new Panier(2,8,1);
        PanierServices ps=new PanierServices();
        //ps.getNomProduit(1);
        ps.ajouter(p1);
        //ps.supprimer(1);
        //ps.modifier2(3, 4);
        //ps.vider();
        //ps.getAll().toString();
        //ps.getPanier();
        //ps.getPrixProduit(1);
        //ps.getTotal();
        //ps.ValiderCommande();
        //ps.getLastligne();
        CommandeServices cs=new CommandeServices();
        Commande cmd=new Commande();
        //cs.getNomUser(1);
        //cs.ajouter(cmd);
        //cs.getLastCmd();
        //cs.getAll().toString();
       // cs.OrderByAsc();
        //cs.OrderByDesc();
        LigneCommande lc=new LigneCommande(2, 1, 1);
        LigneCommandeServices lcs=new LigneCommandeServices();
        //lcs.getAll().toString();
        //lcs.ajouter(lc);
        
    }
    
}
