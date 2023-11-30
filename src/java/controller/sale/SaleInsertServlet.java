package controller.sale;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.shoppingcart.ShoppingCart;
import model.shoppingcart.ShoppingCartItem;
import model.user.User;
import model.sale.SaleDAO;

public class SaleInsertServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
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
        List<ShoppingCartItem> items = ShoppingCart.getCart(cookie.getValue());
        SaleDAO saleDAO = new SaleDAO();

        boolean success = saleDAO.insertSale(user.getId(), items);

        if (success) {
            cookie.setValue("");
            response.addCookie(cookie);
        }

        request.getRequestDispatcher("/").forward(request, response);
    }

}
