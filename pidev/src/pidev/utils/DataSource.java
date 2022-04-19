/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 21695
 */
public class DataSource {

    private static DataSource instance;

    private static Connection cnx;
    private final String URL = "jdbc:mysql://localhost:3306/pidevvv";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    public DataSource() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("connecting");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static DataSource getinstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public static Connection getCns() {
        return cnx;
    }

}
