package model.sale;

import static config.ConfigDB.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleProductDAO {

    public List<SaleProduct> getProductsBySaleId(int saleId) {
        List<SaleProduct> products = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = c.prepareStatement("SELECT * FROM sale_product WHERE sale_id = ?");
            ps.setInt(1, saleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SaleProduct product = new SaleProduct();
                product.setSaleId(rs.getInt("sale_id"));
                product.setProductId(rs.getInt("product_id"));
                product.setQuantity(rs.getInt("quantity"));
                products.add(product);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD getProductsBySaleId - " + ex);
        }
        return products;
    }

    /**
     * Método utilizado para deletar os produtos de uma venda
     *
     * @param saleId
     * @param productId
     *
     * @return
     */
    public boolean removeSaleProducts(int saleId, int productId) {
        boolean success = false;

        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Deletar a linha da tabela sale_product
            PreparedStatement ps = c.prepareStatement("DELETE FROM sale_product WHERE sale_id = ? and product_id = ?");
            ps.setInt(1, saleId);
            ps.setInt(2, productId);
            success = (ps.executeUpdate() == 1);
            ps.close();

            // Verificar se não há mais linhas para o sale_id específico
            ps = c.prepareStatement("SELECT COUNT(*) FROM sale_product WHERE sale_id = ?");
            ps.setInt(1, saleId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int rowCount = rs.getInt(1);

            rs.close();
            ps.close();
            c.close();

            // Se não houver mais linhas, remover a venda correspondente na tabela sale
            if (rowCount == 0) {
                SaleDAO saleDAO = new SaleDAO();
                saleDAO.removeSale(saleId);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("METHOD removeSaleProducts" + ex);
            return false;
        }

        return success;
    }

}
