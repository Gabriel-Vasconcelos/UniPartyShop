package controller.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.product.ProductDAO;

@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/admin/update-product"})
public class UpdateProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        ProductDAO productDAO = new ProductDAO();

        if (productId != 0) {
            if (productDAO.updateProduct(productId, description, title, price, "", quantity, categoryId)) {
                response.sendRedirect(request.getContextPath() + "/admin");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/new-product?id=" + categoryId);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/new-product");
        }
    }
}
