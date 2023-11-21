package controller.category;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.category.CategoryDAO;
import model.category.Category;

@WebServlet(name = "NewCategoryServlet", urlPatterns = {"/admin/new-category"})
public class NewCategoryServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoryId = request.getParameter("id");

        if (categoryId != null && !categoryId.isEmpty()) {
            // Se o ID estiver presente, obtenho o nome da categoria com base no ID
            CategoryDAO categoryDAO = new CategoryDAO();
            Category category = categoryDAO.selectCategory(Integer.parseInt(categoryId));

            if (category != null) {
                // Adicionar o nome da categoria como um atributo ao request
                request.setAttribute("categoryName", category.getName());
            }
        } else {
            request.setAttribute("categoryName", "");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/category/");
        dispatcher.forward(request, response);
    }

}
