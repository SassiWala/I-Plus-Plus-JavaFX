/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author sahra
 */
public class myconnection {
     public final String url="jdbc:mysql://localhost:3306/pidev";
    public final String user="root";
    public final String pwd ="";
   private Connection cnx;
   public static myconnection ct;
   public myconnection(){ 
        try{
            cnx=DriverManager.getConnection(url, user, pwd);
            System.out.println("Success");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            
        }
    }
    public Connection getConnection(){
        return cnx;
    }
    public static myconnection getInstance(){
        if(ct==null)
            ct=new myconnection();
        return ct;
    }

    public Connection myconnection() {
        return cnx;
    }
}


