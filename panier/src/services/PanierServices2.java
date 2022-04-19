/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commande;
import entities.LigneCommande;
import entities.Prod;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;
import services.CommandeServices;
import services.LigneCommandeServices;
import services.ProdServices;

/**
 *
 * @author yassi
 */
public class PanierServices2 {
    private static ArrayList<Prod> produit;
	double total;


    public PanierServices2() {
        this.produit = new ArrayList<Prod>();
		this.total = 0;
    }

    public static ArrayList<Prod> getProduit() {
        return produit;
    }

    public static void setProduit(ArrayList<Prod> produit) {
        PanierServices2.produit = produit;
    }
        
        public void addToPanier(Prod product) {
		this.produit.add(product);
        }
       
        public void AffichePanier() {
		ListIterator<Prod> iterator = produit.listIterator();
		while(iterator.hasNext()) {
			Prod produit1 = iterator.next();
			System.out.println(produit1);
		}
        }
        public void removeFromPanier(Prod a) {
		ListIterator<Prod> iterator1 = produit.listIterator();
		while(iterator1.hasNext()) {
			Prod produit2 = iterator1.next();
			if (produit2.getNomProd().equals(a.getNomProd())) {
				this.produit.remove(a);
				break;
			}
		}
	}
        public double getTotal() {
		ListIterator<Prod> iterator2 = produit.listIterator();
		this.total = 0;
		while(iterator2.hasNext()) {
			Prod produit3 = iterator2.next();
			this.total = this.total + (produit3.getPrixProd());
		}
                System.out.println(total);
		return this.total;
        }
        
        public void AffichagePanier() {
		ListIterator<Prod> iterator3 = produit.listIterator();
		while(iterator3.hasNext()) {
			Prod produit4 = iterator3.next();
			System.out.print(produit4.getNomProd()+ "\t");
			System.out.print(produit4.getQuantite()+ "\t");
			System.out.print(produit4.getPrixProd()+ "\t");
			System.out.println(produit4.getPrixProd()* produit4.getQuantite());
		}
		System.out.println("\t\t\t" + "Total    : " + this.getTotal());
		
	}
        
        public void ValiderCommande() {
		ListIterator<Prod> iterator3 = produit.listIterator();
                Commande cmd =new Commande();
                CommandeServices cs = new CommandeServices();
                LigneCommandeServices lcs=new LigneCommandeServices();
                ProdServices ps = new ProdServices();
                cmd.setDateCommande(new Date());
                cmd.setMontantCommande(this.getTotal());
                cmd.setMatricule();
                cs.ajouter(cmd);
                //Commande cmd=cs.ajouter(cmd);
		while(iterator3.hasNext()) {
	Prod produit4 = iterator3.next();
        ps.ajouter(produit4);
           int qte=  produit4.getQuantite();
                 double price= produit4.getPrixProd();
                  double total= produit4.getPrixProd()* produit4.getQuantite();
                  LigneCommande ligne =new LigneCommande();
                  //ligne.setProduit(produit4);
                  ligne.setQuantite(qte);
                  ligne.setId_commande(cmd.getMatricule());
                  
                  ligne.setId_produit(produit4.getId());
                  //ligne.setId_commande(cmd.getId_commande());
                  //ligne.setId_commande(1);
                  System.out.println(ligne.toString());
                  lcs.ajouter(ligne);
                  iterator3.remove();
	}
                System.out.println(cmd.toString());
                System.out.println(iterator3.toString());
                
                }
        
        public void ViderPanier() {
		ListIterator<Prod> iterator4 = produit.listIterator();
                while(iterator4.hasNext()) {
                   // iterator4.remove();
                }
                }
                
        
                
}
