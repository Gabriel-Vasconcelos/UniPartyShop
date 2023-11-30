<%@page import="model.user.User"%>
<%@page import="java.util.List"%>
<%@page import="model.shoppingcart.ShoppingCart"%>
<%@page import="model.shoppingcart.ShoppingCartItem"%>
<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Meu Carrinho</title>
    </head>
    <body>
        <jsp:include page="../../components/header/header.jsp"/>
        <main class="container">
            <h1>Carrinho</h1>

            <%
                Cookie cookie = null;
                Cookie[] cookies = request.getCookies();

                double total = 0; // total value

                DecimalFormat realFormat = new DecimalFormat("#,##0.00");

                if (cookies != null) {
                    for (Cookie c : cookies) {
                        if (c.getName().equals("unipartyshop.cart")) {
                            cookie = c;
                            break;
                        }
                    }
                }
            %>

            <section>
                <div>
                    <h2>Todos os itens (<%= (cookie == null || cookie.getValue().length() == 0) ? "0" : cookie.getValue().length() + 1%>)</h2>
                </div>
                <div id="shopping-cart-items-container">
                    <%
                        if (cookie != null && cookie.getValue().length() > 0) {

                            List<ShoppingCartItem> items = (List<ShoppingCartItem>) ShoppingCart.getCart(cookie.getValue());

                            if (items != null && !items.isEmpty()) {
                                for (int i = 0; i < items.size(); i++) {
                    %>

                    <div class="shopping-cart-item">
                        <div>
                            <img src="<%= request.getContextPath()%>/assets/images/caneca-preta-gorila.png" alt="alt"/>
                        </div>
                        <div>
                            <h3><%= items.get(i).getProduct().getTitle()%></h3>
                            <div>
                                <p>R$ <%= realFormat.format(items.get(i).getProduct().getPrice() * items.get(i).getQuantify())%></p>
                                <div>
                                    <div>
                                        <!--
                                        <a href="<%= request.getContextPath()%>/remove-add-product-quantify?productId=<%= items.get(i).getProduct().getId()%>&action=remove">-</a>
                                        -->
                                        <a href="">-</a>
                                        <span><%= items.get(i).getQuantify()%></span>
                                        <!--
                                        <a href="<%= request.getContextPath()%>/remove-add-product-quantify?productId=<%= items.get(i).getProduct().getId()%>&action=add">+</a>
                                        -->
                                        <a href="">+</a>
                                    </div>
                                    <a href="<%= request.getContextPath()%>/remove-product-shopping-cart?productId=<%= items.get(i).getProduct().getId()%> ">
                                        <i data-lucide="trash-2"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                                total += items.get(i).getProduct().getPrice() * items.get(i).getQuantify();
                            }
                        }

                    } else {
                    %>
                    <div class="">
                        <h3>Sem produtos no carrinho de compras</h3>
                    </div>
                    <%
                        }
                    %>
                </div>
            </section>
            <section>
                <h2>Resumo do Pedido</h2>
                <p>R$<%= realFormat.format(total)%></p>
                <%
                    User user = (User) session.getAttribute("user");
                    if (user != null && user instanceof User && !user.isAdmin()) {
                %>
                <a href="<%= request.getContextPath()%>/sale-insert" >
                    Comprar agora
                </a>
                <%
                    }
                    if (total == 0) {
                %>
                <p>Adicione algum produto no carrinho!</p>

                <%
                } else {
                %>
                <p>Você precisa ser um usuário para efetuar compras!</p>
                <%
                    }

                %>
            </section>
        </main>
    </body>
</html>

<!-- Development version -->
<script src="https://unpkg.com/lucide@latest/dist/umd/lucide.js"></script>

<!-- Production version -->
<script src="https://unpkg.com/lucide@latest"></script>
<script>
    lucide.createIcons();
</script>