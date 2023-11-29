package controller.product;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.product.Product;
import model.product.ProductDAO;

@WebServlet(name = "NewProductServlet", urlPatterns = {"/admin/new-product"})
public class NewProductServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productId = request.getParameter("id");

        if (productId != null && !productId.isEmpty()) {
            // Se o ID estiver presente, obtenho as informações do produto com base no ID
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.selectProduct(Integer.parseInt(productId));

            if (product != null) {
                // Adicionar as informações do produto como atributos ao request
                request.setAttribute("productTitle", product.getTitle());
                request.setAttribute("productDescription", product.getDescription());
                request.setAttribute("productPrice", product.getPrice());
                request.setAttribute("productQuantity", product.getQuantity());
                request.setAttribute("productCategoryId", product.getCategory_id());
            }
        } else {
            request.setAttribute("productTitle", "");
            request.setAttribute("productDescription", "");
            request.setAttribute("productPrice", "");
            request.setAttribute("productQuantity", "");
            request.setAttribute("productCategoryId", 0);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product/");
        dispatcher.forward(request, response);
    }

}
