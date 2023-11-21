package controller.category;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.category.CategoryDAO;

@WebServlet(name = "InsertCategoryServlet", urlPatterns = {"/admin/insert-category"})
public class InsertCategoryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");

        CategoryDAO categoryDAO = new CategoryDAO();

        boolean success = categoryDAO.insertCategory(name);

        if (success) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/new-category");
            dispatcher.forward(request, response);
        }

    }
}
