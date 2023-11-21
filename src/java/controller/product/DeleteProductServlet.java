package controller.product;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.product.ProductDAO;

@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/admin/delete-product"})
public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        ProductDAO productDAO = new ProductDAO();

        boolean success = productDAO.removeProduct(id);

        if (success) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
            dispatcher.forward(request, response);
        }

    }

}
