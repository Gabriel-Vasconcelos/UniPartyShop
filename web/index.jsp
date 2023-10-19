<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <link rel="stylesheet" href="global.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UniParty Shop</title>
    </head>
    <body>
        <jsp:include page="components/header/header.jsp"/>
        <h1>Hello World!</h1>
    </body>

    <!-- Development version -->
    <script src="https://unpkg.com/lucide@latest/dist/umd/lucide.js"></script>

    <!-- Production version -->
    <script src="https://unpkg.com/lucide@latest"></script>
    <script>
        lucide.createIcons();
    </script>
</html>
