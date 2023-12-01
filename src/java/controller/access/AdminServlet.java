package controller.access;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.product.ProductDAO;
import model.product.Product;
import model.category.Category;
import model.category.CategoryDAO;
import model.user.User;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        List<Product> products = productDAO.listAllProducts();
        List<Category> categories = categoryDAO.listCategory(null);

        request.setAttribute("products", products);
        request.setAttribute("categories", categories);

        User user = (User) request.getSession().getAttribute("user");

        if (user != null && user.isAdmin()) {
            // Usuário é um administrador
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
            dispatcher.forward(request, response);
        } else {
            // Usuário não é um administrador
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

}
