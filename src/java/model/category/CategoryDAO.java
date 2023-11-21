package model.category;

import static config.ConfigDB.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    
    /**
     * Método utilizado para inserir uma nova categoria
     *
     * @param name
     * 
     * @return
     */
    public boolean insertCategory(String name) {
        boolean success = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("INSERT INTO category (name) VALUES (?)");
            ps.setString(1, name);
            success = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD category insert - " + ex);   
            return false;
        }
        
        return success;
    }
    
    /**
     * Método utilizado para listar to
     * das as categorias
     * 
     * @param name
     * 
     * @return
     */
    public List<Category> listCategory(String name) {
        if (name == null || name.length() == 0) {
            name = "%";
        } else {
            name = "%" + name + "%";
        }
        List<Category> categories = new ArrayList();
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("SELECT id, name FROM category WHERE upper(name) LIKE upper(?)");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return new ArrayList();
        }
        return categories;
    }
    
    /**
     * Método utilizado para listar uma categoria existente
     *
     * @param id
     * 
     * @return
     */
    public Category selectCategory(int id) {
        Category category = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("SELECT * FROM category WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
            }
            rs.close();
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD category select - " + ex);            
            return null;
        }
        return category;
    }
    
    /**
     * Método utilizado para atualizar uma categoria existente
     *
     * @param id
     * @param name
     * 
     * @return
     */
    public boolean updateCategory(int id, String name){
        boolean success = false;
        
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("UPDATE category SET name = ? WHERE id = ?");
            ps.setString(1, name);
            ps.setInt(2, id);
            success = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD update do category - " + ex);
            return false;
        }
        
        return success;
    }
    
    /**
     * Método utilizado para excluir uma categoria existente
     *
     * @param id
     * 
     * @return
     */
    public boolean removeCategory(int id){
        boolean success = false;
        
        try{
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("DELETE FROM category WHERE id = ?");
            ps.setInt(1, id);
            
            success = (ps.executeUpdate() == 1);
            
            ps.close();
            c.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD remove category - " + ex);
            success = false;
        }
        
        return success;
    }
    
}
