<%@page import="java.util.List"%>
<%@page import="model.product.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  href="assets/css/home.css"/>
        <title>UniParty Shop</title>
    </head>
    <body>
        <jsp:include page="components/header/header.jsp"/>
        <main class=""> 
            <section class="container products-section">
                <h1 class="title-section">Produtos</h1>
                <%
                    List<Product> products = (List<Product>) request.getAttribute("productsInStock");
                    if (products != null && !products.isEmpty()) {
                %>
                <div class="product-cards-container">
                    <%
                        for (int i = 0; i < Math.min(products.size(), 6); i++) {
                            Product product = products.get(i);
                    %>

                    <div class="product-card">
                        <div class="product-card-image">
                            <img src="assets/images/caneca-preta-gorila.png" alt="Imagem de uma caneca preta com um gorila na frente" title="<%= product.getTitle()%>"/>
                        </div>
                        <div class="product-card-body">
                            <div class="product-card-title-price">
                                <h3 class="product-card-title"><%= product.getTitle()%></h3>
                                <h4 class="product-card-price">R$<%= product.getPrice()%></h4>
                            </div>
                            <p class="product-card-description"><%= product.getDescription()%></p>
                        </div>
                        <div class="product-card-footer">
                            <a class="button product-card-button" href="./add-product-shopping-cart?productId=<%= product.getId()%>">Adicionar ao Carrinho</a>
                        </div>
                    </div>

                    <%
                        }
                    %>
                </div>
                <%
                    }
                %>
                <a class="button product-section-button" href="pages/all-products">
                    Veja mais produtos
                </a>
            </section>
        </main>
    </body>
</html>
