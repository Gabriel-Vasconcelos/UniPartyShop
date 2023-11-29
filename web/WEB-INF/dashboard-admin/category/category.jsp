<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <link rel="stylesheet"  href="<%= request.getContextPath()%>/global.css"/>
        <link rel="stylesheet"  href="<%= request.getContextPath()%>/assets/css/category.css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Actor&display=swap">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Megrim&display=swap">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nova Categoria</title>
    </head>
    <body class="new-category-body">
        <main class="new-category">
            <section class="image-section">
                <!-- <img src="../../assets/images/image-lexica.jpg" alt="alt" width="auto" height="auto"/>-->
            </section>
            <section class="forms-section container">
                <header class="header-new-category">
                    <a href="<%= request.getContextPath()%>/admin" title="Painel Admin" class="logo">Painel Admin</a>
                    <div class="buttons">
                        <button class="clicked">Categoria</button>
                    </div>
                </header>

                <form 
                    action="<%= (request.getAttribute("categoryName") != null
                            && !((String) request.getAttribute("categoryName")).isEmpty())
                            ? request.getContextPath() + "/admin/update-category" : request.getContextPath() + "/admin/insert-category"%>" 
                    method="POST" 
                    class="category-form">
                    <input type="hidden" name="id" value="<%= request.getParameter("id")%>"/>
                    <label for="name">
                        Nome
                        <input type="text" id="name" name="name" value="<%= request.getAttribute("categoryName")%>" required/>
                    </label>
                    <div class="forms-button">
                        <button type="submit" class="button">
                            <%= (request.getAttribute("categoryName") != null
                                    && !((String) request.getAttribute("categoryName")).isEmpty())
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