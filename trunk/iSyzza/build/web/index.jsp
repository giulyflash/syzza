<%-- 
    Document   : index
    Created on : 08/09/2011, 15:13:02
    Author     : Jonathan e Gabriel
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <title>iSyzza</title>
        <script src="http://www.google.com/jsapi"></script>
        <script type="text/javascript">
            google.setOnLoadCallback(function() {
 
                $(function(){
                    alert('Olá');
                });
 
            });
            $
        </script>
        <script type="text/javascript">
            $(document).ready(function(){
                alert('sdjsaidjias');
            })
        </script>
        <style type="text/css">
            #erro {
                color: red;
            }
            header {
                width: 1024px;
                margin: 0 auto;
                border-color: black;
                border-style: solid;
            }
            nav #menu ul {
                float: right;
            }
        </style>

    </head>
    <body>
        <header>
            <h1>Login</h1>
            <nav id="menu">
                <ul>
                    <li>Home</li>
                    <li>Pedido</li>
                    <li>Conta</li>
                </ul>
            </nav>
        </header>
        <section>
            <%
                Integer status = (Integer) request.getAttribute("status");

                if (status != null) {
                    out.println("<div id=\"erro\">");
                    out.print("<span>Login ou senha errados!</span>");
                    out.println("</div>");
                }
            %>
            <form id="form-login" name="form-login" method="post" action="logincli.do">
                <fieldset>
                    <legend>Faça seu Login</legend>
                    <p>
                        <label for="login">
                            <span>Email: </span>
                            <input type="text" id="email" value="" name="email" required>
                        </label>
                    </p>
                    <p>
                        <label for="senha">
                            <span>Senha: </span>
                            <input type="password" id="senha" value="" name="senha" required>
                        </label>
                    </p>
                    <p>Para se cadastrar <a href="cadcli.jsp">Clique aqui.</a></p>
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
