/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.category;

import static config.ConfigDB.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author wiljo
 */
public class CategoryDAO {
    
    public boolean insertCategory(String name) {
        boolean success = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("INSERT INTO category (name, is_admin) VALUES (?,  false)");
            ps.setString(1, name);
            success = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
        return success;
    }
    
    public boolean selectCategory(int id) {
        boolean success = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("SELECT * FROM category WHERE id = ?");
            ps.setInt(1, id);
            success = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
        return success;
    }
    
    public boolean updateCategory(int id, String name){
        boolean success = false;
        
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("UPDATE category SET (id, name, is_admin) VALUES (?, ?,  false)");
            ps.setInt(1, id);
            ps.setString(2, name);
            success = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD update do category - " + ex);
            return false;
        }
        
        return success;
    }
    
     public boolean removeCategory(int id){
        boolean success = false;
        
        try{
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("DELETE * FROM category WHERE id = ?");
            ps.setInt(1, id);
            
            success = (ps.executeUpdate() == 1);
            
            ps.close();
            c.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD remove do category - " + ex);
            success = false;
        }
        
        return success;
    }
    
}
