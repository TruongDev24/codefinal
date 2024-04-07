/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class DBConnect {
    private static final String user = "sa";
    private static final String pass = "truong2004";
    
    public static Connection getConnection(){
        String url = "jdbc:sqlserver://localhost\\TRUONGLEVAN:1433;databaseName=BookStore;encryt=true;trustserverCertificate=true;";
        try{
            return DriverManager.getConnection(url, user, pass);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
