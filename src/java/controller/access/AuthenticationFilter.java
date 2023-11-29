package controller.access;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.user.User;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/pages/my-profile", "/admin/*"})
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            // Usuário não está logado, redireciona para a página de login
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/pages/login-register/");
        } else {
            User user = (User) session.getAttribute("user");
            String requestURI = httpRequest.getRequestURI();

            if (!user.isAdmin() && requestURI.startsWith(httpRequest.getContextPath() + "/admin/")) {
                // Usuário não é um admin, redireciona para a página inicial
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/");
            } else {
                // Usuário está logado e tem permissão, continua com a solicitação
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Método init
    }

    @Override
    public void destroy() {
        // Método destroy
    }
}
