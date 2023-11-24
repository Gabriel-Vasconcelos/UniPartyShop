<%@page import="java.util.List"%>
<%@page import="model.product.Product"%>
<%@page import="model.category.Category"%>
<%@page import="model.category.CategoryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/assets/css/dashboard-admin.css"/>
         <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Actor&display=swap">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Megrim&display=swap">
        <title>Painel do Admin</title>
    </head>
    <body>
        <jsp:include page="/components/header/header.jsp"/>
        <main class="" id="dashboard-admin">
            <section id="dashboard-header" class="container">
                
                <div id="dashboard-buttons">
                    <h1>Painel Admin</h1>
        <button id="btnProducts"  onclick="showProducts()">Produtos</button>
        <button id="btnCategories" onclick="showCategories()">Categorias</button>
    </div>
            </section>
            <section id="dashboard-body" class="container">
                <div id="dashboard-products">
                    <div>
                        <h2>Painel Admin > Produtos</h2>
                        <a href="<%= request.getContextPath()%>/admin/new-product" class="btn-new-product">Novo Produto</a>
                    </div>
                    <div>
                        <table>
                            <%
                                List<Product> products = (List<Product>) request.getAttribute("products");

                                CategoryDAO categoryDAO = new CategoryDAO();

                                if (products == null || products.isEmpty()) {
                            %>
                            <div>Não há produtos a serem listadas.</div>
                            <%
                            } else {
                            %>
                            <tr>
                                <td>ID</td>
                                <td>Título</td>
                                <td>Descrição</td>
                                <td>Preço</td>
                                <td>Quantidade</td>
                                <td>Categoria</td>
                                <td>Ação</td>
                            </tr>
                            <%
                                for (Product product : products) {
                            %>
                            <tr>
                                <td><%= product.getId()%></td>
                                <td><%= product.getTitle()%></td>
                                <td><%= product.getDescription()%></td>
                                <td><%= product.getPrice()%></td>
                                <td><%= product.getQuantity()%></td>
                                <td>
                                    <% Category productCategory = categoryDAO.selectCategory(product.getCategory_id());%>
                                    <%= productCategory.getName()%>
                                </td>
                                <td>
                                    <a href="<%= request.getContextPath()%>/admin/new-product?id=<%= product.getId()%>" class="edit-button">Editar</a>
                                    <button onclick="deleteProduct('<%= request.getContextPath()%>', <%= product.getId()%>)" class="delete-button">Deletar</button>
                                </td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </table>
                    </div>
                </div>

                <!-- Quando o usuário clicar no botão 'categorias' deve mostrar a <div> abaixo-->
                <div id="dashboard-categories">
                    <div>
                        <h2>Painel Admin > Categorias</h2>
                        <a href="<%= request.getContextPath()%>/admin/new-category" class="btn-new-category">Nova Categoria</a>
                    </div>
                    <div>
                        <table>
                            <%
                                List<Category> categories = (List<Category>) request.getAttribute("categories");
                                if (categories == null || categories.isEmpty()) {
                            %>
                            <div>Não há categorias a serem listadas.</div>
                            <%
                            } else {
                            %>
                            <tr>
                                <td>ID</td>
                                <td>Nome</td>
                                <td>Ação</td>
                            </tr>
                            <%
                                for (Category category : categories) {
                            %>
                            <tr>
                                <td><%= category.getId()%></td>
                                <td><%= category.getName()%></td>
                                <td>
                                    <a href="<%= request.getContextPath()%>/admin/new-category?id=<%= category.getId()%>" class="edit-button">Editar</a>
                                    <button onclick="deleteCategory('<%= request.getContextPath()%>', <%= category.getId()%>)" class="delete-button">Deletar</button>
                                </td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </table>
                    </div>
                </div>
            </section>
        </main>
        <script src="<%= request.getContextPath()%>/assets/js/dashboard-admin.js"></script>

    </body>
</html>
