<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <link rel="stylesheet"  href="../../global.css"/>
        <link rel="stylesheet"  href="login-register.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UniParty Shop</title>
    </head>
    <body class="login-register-body">
        <main class="login-register">
            <section class="image-section">
                <img src="../../assets/images/image-lexica.jpg" alt="alt" width="auto" height="auto"/>
            </section>
            <section class="forms-section container">
                <header class="header-login-register">
                    <a href="<%= request.getContextPath()%>/" title="Início" class="logo">UniPartyShop</a>
                    <div class="buttons">
                        <button id="loginButton" class="clicked">Login</button>
                        <button id="registerButton" class="">Cadastro</button>
                    </div>
                </header>
                <div class="login-register-forms">
                    <form action="<%= request.getContextPath()%>/login" method="POST" class="login">
                        <label for="username">
                            Username
                            <input type="text" id="username" name="username" required/>
                        </label>
                        <label for="password">
                            Password
                            <input type="password" id="password" name="password" required/>
                        </label>
                        <div class="forms-button">
                            <button type="submit" class="button">login</button>                            
                            <a href="<%= request.getContextPath()%>/" class="button">Voltar</a>
                        </div>
                    </form>
                    <form action="<%= request.getContextPath()%>/register" method="POST" class="register">
                        <label for="name">
                            Nome
                            <input type="text" id="name" name="name" required/>
                        </label>
                        <label for="username">
                            Username
                            <input type="text" id="username" name="username" required/>
                        </label>
                        <label for="email">
                            Email
                            <input type="email" id="email" name="email" required/>
                        </label>
                        <label for="address">
                            Endereço
                            <input type="text" id="address" name="address" required/>
                        </label>
                        <label for="password">
                            Senha
                            <input type="password" id="password" name="password" required/>
                        </label>
                        <div class="forms-button">
                            <button type="submit" class="button">cadastrar</button>                            
                            <a href="<%= request.getContextPath()%>/" class="button">voltar</a>
                        </div>
                    </form>
                </div>
            </section>
        </main>

        <script src="login-register.js" type="text/javascript"></script>

    </body>
</html>
