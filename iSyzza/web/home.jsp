<%-- 
    Document   : home
    Created on : 08/09/2011, 19:52:41
    Author     : 0669105
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header><h1>Pagina Inicial</h1></header>
        <nav>
            <ul>
                <li><a href="teste.jsp">teste</a></li>
            </ul>
        </nav>
        <section>Bem vindo <% out.print(request.getAttribute("login")); %></section>
        <footer>iSyzza @Copyright 2011</footer>
    </body>
</html>
