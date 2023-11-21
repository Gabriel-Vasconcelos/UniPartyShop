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

@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/deleteAccount"})
public class DeleteAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            UserDAO userDAO = new UserDAO();
            if (userDAO.removeClient(user.getId())) {
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/");
            } else {
                response.sendRedirect(request.getContextPath() + "/my-profile");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login-register");
        }
    }
}
