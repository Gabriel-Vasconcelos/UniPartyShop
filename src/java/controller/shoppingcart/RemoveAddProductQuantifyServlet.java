package controller.shoppingcart;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.shoppingcart.ShoppingCart;

public class RemoveAddProductQuantifyServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));
        String action = request.getParameter("action");

        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("unipartyshop.cart")) {
                    cookie = c;
                    break;
                }
            }
        }

        if (cookie != null && cookie.getValue() != null) {
            String newCookieString = ShoppingCart.removeAddProductQuantify(cookie.getValue(), productId, action);

            cookie.setValue(newCookieString);
            cookie.setMaxAge(Integer.MAX_VALUE);

            response.addCookie(cookie);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/shopping-cart/");
        dispatcher.forward(request, response);
    }

}
