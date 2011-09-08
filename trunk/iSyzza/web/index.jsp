<%-- 
    Document   : index
    Created on : 08/09/2011, 15:13:02
    Author     : Jonathan e Gabriel
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header><h1>Login</h1></header>
        <section>
            <% 
                Integer status = (Integer)request.getAttribute("status");
                
                if (status != null) {
                    out.println(status + 1);
                    out.println("<div id=\"erro\">");
                    out.print("<span>Login ou senha errados!</span>");
                    out.println("</div>");
                }
            %>
            <form id="form-login" name="form-login" method="post" action="vl.do">
                <fieldset>
                    <legend>Faça seu Login</legend>
                    <p>
                        <label for="login">
                            <span>Login: </span>
                            <input type="email" id="login" value="" name="login" required>
                        </label>
                    </p>
                    <p>
                        <label for="senha">
                            <span>Senha: </span>
                            <input type="password" id="senha" value="" name="senha" required>
                        </label>
                    </p>
                    <p>
                        <input type="submit">
                        <input type="reset">
                    </p>
                </fieldset>
            </form>
        </section>
        <footer><span>iSyzza @Copyright 2011</span></footer>
    </body>
</html>
