package controller.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.user.User;
import model.user.UserDAO;

@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/updateAccount"})
public class UpdateAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserDAO userDAO = new UserDAO();
            if (userDAO.updateClient(user.getId(), name, address, email, username, password)) {
                // Atualizar os dados do usuário na sessão
                User updatedUser = userDAO.selectClient(username);
                session.setAttribute("user", updatedUser);

                // Atualização bem-sucedida
                response.sendRedirect(request.getContextPath() + "/pages/my-profile");
            } else {
                // Tratamento para falha na atualização
                response.sendRedirect(request.getContextPath() + "/pages/my-profile");
            }
        } else {
            // Usuário não autenticado
            response.sendRedirect(request.getContextPath() + "pages//login-register");
        }
    }
}
