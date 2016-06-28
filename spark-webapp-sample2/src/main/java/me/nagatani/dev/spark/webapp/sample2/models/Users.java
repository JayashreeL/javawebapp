package me.nagatani.dev.spark.webapp.sample2.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Users
 * @author Nagatani
 */
public class Users {
    
    private static final String TABLE_NAME = "Users";
    
    /**
     * Usersテーブルから全データを取得
     * @return 
     */
    public static List<User> selectAll() {
        
        List<User> result = new ArrayList<>();
        
        try ( Connection connection = Database.getConnection(); ) {
            
            StringBuilder SQL = new StringBuilder();
            SQL.append("SELECT ").append(System.lineSeparator());
            SQL.append("    *  ").append(System.lineSeparator());
            SQL.append("FROM ").append(System.lineSeparator());
            SQL.append("    ").append(TABLE_NAME).append(System.lineSeparator());
            
            PreparedStatement stm = connection.prepareStatement(SQL.toString());
            
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                result.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * 1件追加
     * @param data
     * @return 更新件数
     */
    public static int insertOne(User data) {
        int result = 0;
        
        try ( Connection connection = Database.getConnection(); ) {
            
            StringBuilder SQL = new StringBuilder();
            SQL.append("INSERT INTO ").append(System.lineSeparator());
            SQL.append("    ").append(TABLE_NAME).append(System.lineSeparator());
            SQL.append("( ").append(System.lineSeparator());
            SQL.append("    `name` ").append(System.lineSeparator());
            SQL.append(",   `email` ").append(System.lineSeparator());
            SQL.append(") VALUES ( ").append(System.lineSeparator());
            SQL.append("    ? ").append(System.lineSeparator());
            SQL.append(",   ? ").append(System.lineSeparator());
            SQL.append(") ").append(System.lineSeparator());
            
            PreparedStatement stm = connection.prepareStatement(SQL.toString());
            
            stm.setString(1, data.getName());
            stm.setString(2, data.getEmail());
            
            result = stm.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
        
    }
    
}
