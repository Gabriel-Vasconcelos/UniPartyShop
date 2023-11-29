package controller.category;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.category.CategoryDAO;
import model.user.User;

@WebServlet(name = "UpdateCategoryServlet", urlPatterns = {"/admin/update-category"})
public class UpdateCategoryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String categoryId = request.getParameter("id");

        User user = (User) request.getSession().getAttribute("user");

        CategoryDAO categoryDAO = new CategoryDAO();

        if (user != null && user.isAdmin()) {
            if (categoryId != null && !categoryId.isEmpty()) { // Verifique se categoryId não é nulo nem vazio
                if (categoryDAO.updateCategory(Integer.parseInt(categoryId), name)) {
                    response.sendRedirect(request.getContextPath() + "/admin");
                } else {
                    response.sendRedirect(request.getContextPath() + "/admin/new-category?id=" + categoryId);
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/new-category");
            }
        } else {
            // Usuário não é um administrador
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
