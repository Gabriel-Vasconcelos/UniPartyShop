package model.sale;

import static config.ConfigDB.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.shoppingcart.ShoppingCartItem;

public class SaleDAO {

    /**
     * Método utilizado para registrar uma nova venda
     *
     * @param userId
     * @param items
     * @return
     */
    public boolean insertSale(int userId, List<ShoppingCartItem> items) {
        boolean success = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            c.setAutoCommit(false);
            PreparedStatement ps = c.prepareStatement("INSERT INTO sale (date_time, user_id) VALUES (NOW(), ?)", java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            if (ps.executeUpdate() != 1) {
                c.rollback();
                c.close();
                return false;
            }
            int saleId = -1;
            try (java.sql.ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
                saleId = keys.getInt(1);
            }
            ps.close();

            for (ShoppingCartItem item : items) {
                PreparedStatement psInsert = c.prepareStatement("INSERT INTO sale_product (sale_id, product_id, quantity) VALUES (?, ?, ?)");
                psInsert.setInt(1, saleId);
                psInsert.setInt(2, item.getProduct().getId());
                psInsert.setInt(3, item.getQuantify());
                if (psInsert.executeUpdate() != 1) {
                    c.rollback();
                    c.close();
                    return false;
                }
                psInsert.close();
                PreparedStatement psUpdate = c.prepareStatement("UPDATE product SET quantity = quantity - ? WHERE id = ?");
                psUpdate.setInt(1, item.getQuantify());
                psUpdate.setInt(2, item.getProduct().getId());
                if (psUpdate.executeUpdate() != 1) {
                    c.rollback();
                    c.close();
                    return false;
                }
                psUpdate.close();
            }
            c.commit();
            c.close();
            success = true;
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
        return success;
    }

}
