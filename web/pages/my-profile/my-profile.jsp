<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/pages/my-profile/my-profile.css"/>
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
                    <form action="#">
                        <div class="manage-account-item">
                            <label>
                                <h4>Nome</h4>
                                <input type="text" value="Clayton Rasta da Silva Dele" disabled />
                            </label>
                            <button>
                                Alterar
                            </button>
                        </div>

                        <div class="manage-account-item">
                            <label>
                                <h4>Login</h4>
                                <input type="text" value="ClayRasta" disabled />
                            </label>
                            <button>
                                Alterar
                            </button>
                        </div>

                        <div class="manage-account-item">
                            <label>
                                <h4>Email</h4>
                                <input type="email" value="exemplodeemail@test.com" disabled />
                            </label>
                            <button>
                                Alterar
                            </button>
                        </div>

                        <div class="manage-account-item">
                            <label>
                                <h4>Endereço</h4>
                                <input type="text" value="Rua Bed- Stuy, 666, Brooklyn, New York" disabled />
                            </label>
                            <button>
                                Alterar
                            </button>
                        </div>

                        <div class="manage-account-item">
                            <label>
                                <h4>Senha</h4>
                                <input type="password" value="senha-teste" disabled />
                            </label>
                            <button>
                                Alterar
                            </button>
                        </div>
                    </form>

                    <div class="manage-account-item"> 
                        <p>Você tem certeza que deseja excluir sua conta? Essa ação não poderá ser desfeita</p>
                        <button>Deletar</button>
                    </div>
                </section>
            </main>
        </div>

    </body>
</html>
