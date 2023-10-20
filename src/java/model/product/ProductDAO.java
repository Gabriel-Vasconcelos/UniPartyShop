/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.product;

import static config.ConfigDB.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author wiljo
 */
public class ProductDAO {
    public boolean insertProdut(String description, String price, String photo, String quantity) {
        boolean success = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("INSERT INTO product (description, price, photo, quantity, is_admin) VALUES (?, ?, ?, ?,  false)");
            ps.setString(1, description);
            ps.setString(2, price);
            ps.setString(3, photo);
            ps.setString(4, quantity);
            success = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
        return success;
    }
    
    public boolean selectProduct(int id) {
        boolean success = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("SELECT * FROM product WHERE id = ?");
            ps.setInt(1, id);
            /**
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, email);
            ps.setString(4, username);
            ps.setString(5, password);
            * */
            success = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
        return success;
    }
}
