/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author c0691289
 */
public class connection {

    /**
     * A Database Utility Class
     *
     *
     */
    /**
     * Utility method used to create a Database Connection
     *
     * @return the Connection object
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);

        }
        String url = "jdbc:mysql://ipro:3306/c0691289-java";
        String username = "c0691289-java";
        String password = "c0691289";
//        String server = "ipro.lambton.on.ca";
//        String username = studentNumber + "-java";
//        String password = studentNumber;
//        String database = username;
//        String jdbc = String.format("jdbc:mysql://%s/%s", server, database);
        System.out.println("connected");
        return DriverManager.getConnection(url, username, password);

    }

    public static void main(String[] args) throws SQLException {
        getConnection();
    }
}
