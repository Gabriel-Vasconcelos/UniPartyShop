package controller.sale;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.sale.SaleProductDAO;


public class DeleteSaleProductsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int saleId = Integer.parseInt(request.getParameter("saleId"));
        int productId = Integer.parseInt(request.getParameter("productId"));

        SaleProductDAO saleProductDAO = new SaleProductDAO();

        boolean success = saleProductDAO.removeSaleProducts(saleId, productId);

        if (success) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
            dispatcher.forward(request, response);
        }

    }

}
