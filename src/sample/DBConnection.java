/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.sql.*;

/**
 *
 * @author midha
 */
public class DBConnection {

    private Connection connection = null;

    public Connection setConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_kuliah";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return connection;
    }

}
