<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="my-profile.css"/>
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
                            <button>Gerenciar Conta</button>
                        </li>
                        <li>
                            <button>Meu Pedidos</button>
                        </li>
                        <li>
                            <button>Lista de Desejos</button>
                        </li>
                    </ul>
                    <a href="<%= request.getContextPath()%>/logout" title="Logout">Sair</a>
                </section>

                <section id="manage-account">
                    <h2>Gerenciar conta</h2>
                    <form action="<%= request.getContextPath()%>/updateAccount" method="post">
                        <div class="manage-account-item">
                            <label>
                                <h4>Nome</h4>
                                <input type="text" name="name" id="name" name="name" value="${user.name}" readonly="true" />
                            </label>
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

                        <div class="manage-account-item">
                            <label>
                                <h4>Login</h4>
                                <input type="text"  id="username" name="username" value="${user.username}" readonly />
                            </label>
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

                        <div class="manage-account-item">
                            <label>
                                <h4>Email</h4>
                                <input type="email" id="email" name="email" value="${user.email}" readonly />
                            </label>
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

                        <div class="manage-account-item">
                            <label>
                                <h4>Endereço</h4>
                                <input type="text" id="address" name="address" value="${user.address}" readonly />
                            </label>
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

                        <div class="manage-account-item">
                            <label>
                                <h4>Senha</h4>
                                <input type="password" id="password" name="password" value="${user.password}" readonly />
                            </label>
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
                    </form>

                    <div class="manage-account-item"> 
                        <p>Você tem certeza que deseja excluir sua conta? Essa ação não poderá ser desfeita</p>
                        <form action="<%= request.getContextPath()%>/deleteAccount" method="post">
                            <button type="submit">Deletar</button>
                        </form>
                    </div>
                </section>
            </main>
        </div>
        <script src="my-profile.js" type="text/javascript"></script>                        
    </body>
</html>
