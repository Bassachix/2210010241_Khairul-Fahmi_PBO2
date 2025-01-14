/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class Koneksi {
    private String jdbcURL="jdbc:mysql://localhost:3306/2210010241_politik";
    private String username="root";
    private String password="";
    
    public Koneksi(){}
    
    public Connection getKoneksiDB() throws SQLException{
        try {
            Driver mysqlDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
            System.out.println("Koneksi ke Database Berhasil...");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return DriverManager.getConnection(jdbcURL, username, password);
    }
}
