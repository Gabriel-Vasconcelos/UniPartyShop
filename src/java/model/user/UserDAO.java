package model.user;

import static config.ConfigDB.*;
import java.sql.*;

public class UserDAO {

    /**
     * Método utilizado para verificar se o login e a senha do usuário são
     * válidos
     *
     * @param username
     * @param password
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
            System.out.println("model.user.UserDAO.validateAccess() " + ex);
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
            return false;
        }
        return success;
    }
}
