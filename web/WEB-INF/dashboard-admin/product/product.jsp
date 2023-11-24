<%@page import="java.util.List"%>
<%@page import="model.category.CategoryDAO"%>
<%@page import="model.category.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <link rel="stylesheet"  href="<%= request.getContextPath()%>/global.css"/>
        <link rel="stylesheet"  href="<%= request.getContextPath()%>/assets/css/product.css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Actor&display=swap">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Megrim&display=swap">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo Produto</title>
    </head>
    <body class="new-product-body">
        <main class="new-product">
           <!-- <section class="image-section">-->
                <!-- <img src="../../assets/images/image-lexica.jpg" alt="alt" width="auto" height="auto"/>-->
            <!--</section>-->
            <section class="forms-section container">
                <header class="header-new-product">
                    <a href="<%= request.getContextPath()%>/admin" title="Painel Admin" class="logo">Painel Admin</a>
                    <div class="buttons">
                        <button class="clicked">Produto</button>
                    </div>
                </header>

                <form 
                    action="<%= (request.getAttribute("productTitle") != null
                            && !((String) request.getAttribute("productTitle")).isEmpty())
                            ? request.getContextPath() + "/admin/update-product" : request.getContextPath() + "/admin/insert-product"%>" 
                    method="POST" 
                    class="product-form">
                    <input type="hidden" name="id" value="<%= request.getParameter("id")%>"/>
                    <label for="title">
                        Título
                        <input type="text" id="title" name="title" value="<%= request.getAttribute("productTitle")%>" required/>
                    </label>
                    <label for="description">
                        Descrição
                        <input type="text" id="description" name="description" value="<%= request.getAttribute("productDescription")%>" required/>
                    </label>
                    <label for="price">
                        Preço
                        <input type="number" id="price" name="price" value="<%= request.getAttribute("productPrice")%>" required/>
                    </label>
                    <label for="quantity">
                        Quantidade
                        <input type="number" id="quantity" name="quantity" value="<%= request.getAttribute("productQuantity")%>" required/>
                    </label>
                    <label for="category">
                        Categoria
                        <%
                            CategoryDAO categoryDAO = new CategoryDAO();

                        %>

                        <select name="categoryId">
                            <%        
                                List<Category> categories = categoryDAO.listCategory(null);
                                for (Category category : categories) {
                                    if (category.getId() == (int) request.getAttribute("productCategoryId")) {
                            %>
                            <option selected="" value="<%= category.getId()%>"><%= category.getName()%></option>
                            <%
                            } else {
                            %>
                            <option value="<%= category.getId()%>"><%= category.getName()%></option>
                            <%
                                    }
                                }
                            %>
                        </select>


                    </label>
                    <div class="forms-button">
                        <button type="submit" class="button">
                            <%= (request.getAttribute("productTitle") != null
                                    && !((String) request.getAttribute("productTitle")).isEmpty())
                                    ? "Atualizar" : "Cadastrar"%>   
                        </button>                            
                        <a href="<%= request.getContextPath()%>/admin" class="button">Voltar</a>
                    </div>
                </form>
                <!--</div>-->
            </section>
        </main>
    </body>
</html>