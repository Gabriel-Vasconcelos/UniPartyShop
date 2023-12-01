<%@page import="model.user.User"%>
<head>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/global.css"/>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/components/header/header.css"/>
</head>
<header class="header-body container">
    <div class="header-logo">
        <a href="<%= request.getContextPath()%>/">UniPartyShop</a>
    </div>
    <nav class="header-menu">
        <ul>
            <li>
                <a href="<%= request.getContextPath()%>/" title="Início">Início</a>
            </li>
            <li>
                <a href="url">Produtos</a>
            </li>
            <li>
                <a href="url">Quem Somos</a>
            </li>
            <li>
                <a href="url">Contato</a>
            </li>
        </ul>
    </nav>
    <ul class="header-icons">
        <li>
            <button class="profile">
                <i data-lucide="user-2"></i>
                <i data-lucide="chevron-down" class="chevron-down"></i>
                <i data-lucide="chevron-up" class="chevron-up"></i>
                <div class="profile-options">
                    <ul>
                        <%
                            User user = (User) session.getAttribute("user");
                            if (user != null && user instanceof User && user.isAdmin()) {
                        %>
                        <li>
                            <a href="<%= request.getContextPath()%>/admin" title="Painel Admin">Painel Admin</a>
                        </li>
                        <li>
                            <a href="<%= request.getContextPath()%>/admin/missing-product-report" title="Relatório">Gerar Relatório</a>
                        </li>
                        <%
                            }
                        %>
                        <%
                            if (user != null && user instanceof User) {
                        %>
                        <li>
                            <a href="<%= request.getContextPath()%>/pages/my-profile" title="Meu Perfil">Meu Perfil</a>
                        </li>
                        <li>
                            <a href="<%= request.getContextPath()%>/logout" title="Logout">Sair</a>
                        </li>
                        <%
                        } else {
                        %>
                        <li>
                            <a href="<%= request.getContextPath()%>/pages/login-register/?action=login" title="Login">Login</a>
                        </li>
                        <li>
                            <a href="<%= request.getContextPath()%>/pages/login-register/?action=register" title="Cadastro">Cadastre-se</a>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </button>
        </li>
        <li>
            <a href="<%= request.getContextPath()%>/pages/shopping-cart" title="Meu Carrinho" id="shopping-cart">
                <i data-lucide="shopping-cart"></i>
            </a>
        </li>
    </ul>
</header>
<!-- Development version -->
<script src="https://unpkg.com/lucide@latest/dist/umd/lucide.js"></script>

<!-- Production version -->
<script src="https://unpkg.com/lucide@latest"></script>
<script>
    lucide.createIcons();
</script>
