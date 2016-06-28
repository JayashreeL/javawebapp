/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.nagatani.dev.spark.webapp.sample2.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * MySQL Database Connection
 * @author Nagatani
 */
public class Database {
    
    private final static String JDBC_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/spark-sample?useSSL=false";
    private final static String DATABASE_USER = "root";
    private final static String USER_PASSWORD = "";
    private static Connection con;
    
    private Database() {}
    
    /**
     * Connectionを確立して返す
     * @return 
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBC_CLASS_NAME);
            
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, USER_PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException( "Error Establishing Connection: " + e.getMessage());
        }
        return con;
    }
}
