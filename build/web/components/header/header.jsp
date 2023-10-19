<head>
    <link rel="stylesheet" type="text/css" href="./components/header/header.css"/>
</head>
<header class="header-body container">
    <div class="header-logo">
        <a href="">UniPartyShop</a>
    </div>
    <nav class="header-menu">
        <ul>
            <li>
                <a href="url" title="Início">Início</a>
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
                        <li>
                            <a href="<%= request.getContextPath()%>/pages/login-register/?action=login" title="Login">Login</a>
                        </li>
                        <li>
                            <a href="<%= request.getContextPath()%>/pages/login-register/?action=register" title="Cadastro">Cadastre-se</a>
                        </li>
                    </ul>
                </div>
            </button>
        </li>
        <li>
            <button>
                <i data-lucide="shopping-cart"></i>
            </button>
        </li>
    </ul>
</header>
