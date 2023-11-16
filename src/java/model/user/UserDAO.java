package model.user;

import static config.ConfigDB.*;
import java.sql.*;

public class UserDAO {
    
    /**
     * Método utilizado para recuperar um usuário pelo login
     * 
     * @param username
     * 
     * @return 
     */
    public User selectClient(String username) {
        User user = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("SELECT id, name, address, email, username, password, is_admin FROM client WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAdmin(rs.getBoolean("is_admin"));
            }
            rs.close();
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD select client " + ex);
            return null;
        }
        return user;
    }
    
    /**
     * Método utilizado para verificar se o login e a senha do usuário são
     * válidos
     *
     * @param username
     * @param password
     * 
     * @return
     */
    public boolean validateAccess(String username, String password) {
        boolean success = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("SELECT * FROM client WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                success = true;
            }
            rs.close();
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD client validate access " + ex);
            return false;
        }
        return success;
    }

    /**
     * Método utilizado para inserir um novo cliente
     *
     * @param name
     * @param address
     * @param email
     * @param username
     * @param password
     * @return
     */
    public boolean insertClient(String name, String address, String email, String username, String password) {
        boolean success = false;
        
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("INSERT INTO client (name, address, email, username, password, is_admin) VALUES (?, ?, ?, ?, ?, false)");
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, email);
            ps.setString(4, username);
            ps.setString(5, password);
            success = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD insert client " + ex);
            return false;
        }
        
        return success;
    }
    
    /**
     * Método utilizado para atualizar um usuário
     *
     * @param id
     * @param name
     * @param address
     * @param email
     * @param username
     * @param password
     * 
     * @return
     */
    public boolean updateClient(int id, String name, String address, String email, String username, String password){
        boolean success = false;
        
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("UPDATE client SET name = ?, address = ?, email = ?, username = ?, password = ? WHERE id = ?");
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, email);
            ps.setString(4, username);
            ps.setString(5, password);
            ps.setInt(6, id);
            
            success = (ps.executeUpdate() == 1);
            
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD update client - " + ex);
            return false;
        }
        
        return success;
    }
    
    
    /**
     * Método utilizado para deletar um usuário
     *
     * @param id
     * 
     * @return
     */
    public boolean removeClient(int id){
        boolean success = false;
        
        try{
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("DELETE FROM client WHERE id = ?");
            ps.setInt(1, id);
            success = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD remove client" + ex);
            return false;
        }
        
        return success;
    }
}
