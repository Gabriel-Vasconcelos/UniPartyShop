package controller.access;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.user.UserDAO;
import model.user.User;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        boolean success = userDAO.validateAccess(username, password);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (success) {
                HttpSession session = request.getSession(true);
                User user = userDAO.selectClient(username);
                session.setAttribute("user", user);
                
                if (user.isAdmin()) {
                    response.sendRedirect("./?login=admin");
                } else{
                    response.sendRedirect("./?login=user");
                }

            } else {
                response.sendRedirect("./pages/login-register/?action=login");
            }
        }
    }

}
