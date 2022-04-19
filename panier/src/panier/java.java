/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panier;

import entities.Prod;
import services.PanierServices2;
import services.CommandeServices;
import services.PanierServices;
import services.ProdServices;
import utils.MyConnection;
import entities.Commande;
import entities.Panier;
import java.sql.SQLException;

/**
 *
 * @author yassi
 */
public class java {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       MyConnection mc2 = MyConnection.getInstance();
        // TODO code application logic here
        
         /*Prod p1 = new Prod(1,"Camera", 3, 100);
        Prod p2 = new Prod(2,"CODE GAMING", 2, 50);
        Prod p3 = new Prod("MICROPHONE", 1, 75);
        Prod p4 = new Prod("PULL", 50, 70);
        Prod p5 = new Prod("PC GAMER", 2, 1000);
        Prod p6 = new Prod("CLAVIER", 400, 50);
        ProdServices ps = new ProdServices();*/
        //ps.ajouter(p3);
        PanierServices2 panier = new PanierServices2();
        
        //panier.addToPanier(p1);
        //panier.addToPanier(p2);
        //panier.addToPanier(p3);
        //panier.AffichagePanier();
        //panier.removeFromPanier(p2);
        //panier.ViderPanier();
        //panier.getTotal();
        //panier.AffichagePanier();
        //panier.AffichePanier();
       
        //panier.ValiderCommande();
        
        CommandeServices cs = new CommandeServices();
        //cs.getAll();
        //cs.supprimer(3);
        //System.out.println("creés");
        
        Commande c1 =new Commande();
        //System.out.println(c1);
                PanierServices cart = new PanierServices();
                Panier p=new Panier(6, 8);
                //cart.ajouter(p);
                //cart.modifier2(p, 7);
                //cart.modifier2(p, 7);
                //cart.getAll();
                //cart.supprimer(2);
                //cart.vider();
                //cart.getAll();
                //cart.getNomProduit(3);
                cart.recherche("CODE GAMING");
                cart.rechercherPanier("CODE GAMING");
                
                

    }
    
}
