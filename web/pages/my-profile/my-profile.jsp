<%@page import="model.product.ProductDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.user.User"%>
<%@page import="java.util.List"%>
<%@page import="model.sale.SaleDAO"%>
<%@page import="model.sale.SaleProduct"%>
<%@page import="model.sale.Sale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="my-profile.css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Actor&display=swap">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Megrim&display=swap">
        <title>Meu Perfil</title>
    </head>
    <body>
        <jsp:include page="../../components/header/header.jsp"/>
        <div id="my-profile" class="container">
            <h1 class="title">Centro Pessoal</h1>
            <main id="my-profile-content">
                <section id="dashboard-profile">
                    <h3>Minha conta</h3>
                    <ul>
                        <li>
                            <button id="manageAccountBtn">Gerenciar Conta</button>
                        </li>
                        <li>
                            <button id="myOrdersBtn">Meus Pedidos</button>
                        </li>
                        <!--                        
                        <li>
                            <button>Lista de Desejos</button>
                        </li>
                        -->
                    </ul>
                    <a href="<%= request.getContextPath()%>/logout" title="Logout">Sair</a>
                </section>

                <section id="manage-account">
                    <h2>Gerenciar conta</h2>
                    <form action="<%= request.getContextPath()%>/updateAccount" method="post">
                        <div class="manage-account-item">
                            <label>
                                <h4>Nome</h4>
                                <div class="input-container">
                                    <input type="text" name="name" id="name" name="name" value="${user.name}" readonly="true" />

                                    <div>
                                        <button type="button" class="editButton" onclick="enableEdit('name')">
                                            Alterar
                                        </button>
                                        <button type="submit" class="updateButton">
                                            Atualizar
                                        </button>
                                        <button type="button" class="cancelButton" onclick="cancelEdit('name')">
                                            Cancelar
                                        </button>
                                    </div>
                                </div>
                            </label>
                        </div>

                        <div class="manage-account-item">
                            <label>
                                <h4>Login</h4>
                                <div class="input-container">
                                    <input type="text"  id="username" name="username" value="${user.username}" readonly />

                                    <div>
                                        <button type="button" class="editButton" onclick="enableEdit('username')">
                                            Alterar
                                        </button>
                                        <button type="submit" class="updateButton">
                                            Atualizar
                                        </button>
                                        <button type="button" class="cancelButton" onclick="cancelEdit('username')">
                                            Cancelar
                                        </button>
                                    </div>   
                                </div>
                            </label>
                        </div>

                        <div class="manage-account-item">
                            <label>
                                <h4>Email</h4>
                                <div class="input-container">
                                    <input type="email" id="email" name="email" value="${user.email}" readonly />

                                    <div>
                                        <button type="button" class="editButton" onclick="enableEdit('email')">
                                            Alterar
                                        </button>
                                        <button type="submit" class="updateButton">
                                            Atualizar
                                        </button>
                                        <button type="button" class="cancelButton" onclick="cancelEdit('email')">
                                            Cancelar
                                        </button>
                                    </div>
                                </div>
                            </label>
                        </div>

                        <div class="manage-account-item">
                            <label>
                                <h4>Endereço</h4>
                                <div class="input-container">
                                    <input type="text" id="address" name="address" value="${user.address}" readonly />

                                    <div>
                                        <button type="button" class="editButton" onclick="enableEdit('address')">
                                            Alterar
                                        </button>
                                        <button type="submit" class="updateButton">
                                            Atualizar
                                        </button>
                                        <button type="button" class="cancelButton" onclick="cancelEdit('address')">
                                            Cancelar
                                        </button>
                                    </div>
                                </div> 
                            </label>        
                        </div>

                        <div class="manage-account-item">
                            <label>
                                <h4>Senha</h4>
                                <div class="input-container">
                                    <input type="password" id="password" name="password" value="${user.password}" readonly />

                                    <div>
                                        <button type="button" class="editButton" onclick="enableEdit('password')">
                                            Alterar
                                        </button>
                                        <button type="submit" class="updateButton">
                                            Atualizar
                                        </button>
                                        <button type="button" class="cancelButton" onclick="cancelEdit('password')">
                                            Cancelar
                                        </button>
                                    </div>
                                </div>
                            </label>
                        </div>
                    </form>

                    <div class="delete-content">
                        <p>Você tem certeza que deseja excluir sua conta? Essa ação não poderá ser desfeita</p>
                        <div class="deleteButton-container">
                            <button type="button" class="deleteButton" onclick="deleteProfile('<%= request.getContextPath()%>')">Deletar</button>
                        </div>
                    </div>
                </section>

                <section id="my-orders">
                    <%
                        User user = (User) session.getAttribute("user");
                        int userId = (user != null) ? user.getId() : -1;

                        SaleDAO saleDAO = new SaleDAO();
                        List<Sale> sales = saleDAO.getSalesByUserId(userId);

                        ProductDAO productDAO = new ProductDAO();
                    %>
                    <h2>Meus Pedidos</h2>
                    <table>
                        <tr>
                            <th>Produto</th>
                            <th>Status</th>
                            <th>Data</th>
                            <th>Hora</th>
                            <th>Quantidade</th>
                        </tr>
                        <% for (Sale sale : sales) { %>
                        <%
                            for (SaleProduct product : sale.getProducts()) {
                            String productTitle = productDAO.getTitleProduct(product.getProductId());
                        %>
                        <tr>
                            <td><%= productTitle%></td>
                            <td>Concluído</td>
                            <td><%= new SimpleDateFormat("dd-MM-yyyy").format(sale.getDateTime())%></td>
                            <td><%= new SimpleDateFormat("HH:mm").format(sale.getDateTime())%></td>                            
                            <td><%= product.getQuantity()%></td>
                        </tr>
                        <% } %>
                        <% } %>

                        <% if (sales.isEmpty()) { %>
                        <tr>
                            <td colspan="4">Nenhum compra efetuada</td>
                        </tr>
                        <% }%>
                    </table>
                </section>
            </main>
        </div>
        <script src="my-profile.js" type="text/javascript"></script>                        
    </body>
</html>
