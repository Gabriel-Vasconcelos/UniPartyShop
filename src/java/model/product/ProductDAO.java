package model.product;

import static config.ConfigDB.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    /**
     * Método utilizado para inserir um novo produto
     *
     * @param description
     * @param title
     * @param price
     * @param photo
     * @param quantity
     * @param category_id
     *
     * @return
     */
    public boolean insertProdut(String description, String title, double price, String photo, int quantity, int category_id) {
        boolean success = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("INSERT INTO product (description, title, price, photo, quantity, category_id) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, description);
            ps.setString(2, title);
            ps.setDouble(3, price);
            ps.setString(4, photo);
            ps.setInt(5, quantity);
            ps.setInt(6, category_id);

            success = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD product insert - " + ex);
            return false;
        }
        return success;
    }

    /**
     * Método utilizado para listar um produto existente
     *
     * @param id
     *
     * @return
     */
    public Product selectProduct(int id) {
        Product product = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("SELECT * FROM product WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setDescription(rs.getString("description"));
                product.setTitle(rs.getString("title"));
                product.setPhoto(rs.getString("photo"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategory_id(rs.getInt("category_id"));
                product.setPrice(rs.getDouble("price"));
            }
            rs.close();
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD product select - " + ex);
            return null;
        }

        return product;
    }

    /**
     * Método utilizado para trazer o título do produto existente
     *
     * @param id
     *
     * @return
     */
    public String getTitleProduct(int id) {
        String product = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("SELECT title FROM product WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product = rs.getString("title");
            }
            rs.close();
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD product getTitleProduct - " + ex);
            return null;
        }

        return product;
    }

    /**
     * Método utilizado para listar todos os produtos em estoque
     *
     * @return
     */
    public List<Product> listProducts() {
        List<Product> products = new ArrayList();
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("SELECT * FROM product WHERE quantity > 0");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setDescription(rs.getString("description"));
                product.setTitle(rs.getString("title"));
                product.setPhoto(rs.getString("photo"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategory_id(rs.getInt("category_id"));
                product.setPrice(rs.getDouble("price"));
                products.add(product);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD list products - " + ex);
            return new ArrayList();
        }
        return products;
    }

    /**
     * Método utilizado para atualizar um novo produto
     *
     * @param id
     * @param description
     * @param title
     * @param price
     * @param photo
     * @param quantity
     * @param category_id
     *
     * @return
     */
    public boolean updateProduct(int id, String description, String title, double price, String photo, int quantity, int category_id) {
        boolean success = false;

        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("UPDATE product SET description = ?, title = ?, price = ?, photo = ?, quantity = ?, category_id = ? WHERE id = ?");
            ps.setString(1, description);
            ps.setString(2, title);
            ps.setDouble(3, price);
            ps.setString(4, photo);
            ps.setInt(5, quantity);
            ps.setInt(6, category_id);
            ps.setInt(7, id);
            success = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD product update - " + ex);
            return false;
        }

        return success;
    }

    /**
     * Método utilizado para deletar um produto existente
     *
     * @param id
     *
     * @return
     */
    public boolean removeProduct(int id) {
        boolean success = false;

        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("DELETE FROM product WHERE id = ?");
            ps.setInt(1, id);

            success = (ps.executeUpdate() == 1);

            ps.close();
            c.close();

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD product remove - " + ex);
            success = false;
        }

        return success;
    }

}
