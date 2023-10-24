/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.sale;

import static config.ConfigDB.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author wiljo
 */
public class SaleDAO {
    public boolean insertSale(String data_hour) {
        boolean success = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("INSERT INTO sale (data_hour, is_admin) VALUES (?,  false)");
            ps.setString(1, data_hour);
            success = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
        return success;
    }
    
    public boolean selectSale(int id) {
        boolean success = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("SELECT * FROM sale WHERE id = ?");
            ps.setInt(1, id);
            success = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
        return success;
    }
    
     public boolean updateSale(int id, String data_hour){
        boolean success = false;
        
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("UPDATE product SET (id, data_hour, is_admin) VALUES (?, ?,  false)");
            ps.setInt(1, id);
            ps.setString(2, data_hour);
            success = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD update do product - " + ex);
            return false;
        }
        
        return success;
    }
    
    
}
