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
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>iSyzza</title>
        <link rel="shortcut icon" href="include/image/icon.ico" type="image/ico" />
        <script src="include/js/jquery-1.7.min.js"> </script>
        <script type="text/javascript">
            $(document).ready(function(){
                alert("Wooow");
            });
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
        <section>
            <%
                Integer status = (Integer) request.getAttribute("status");

                if (status != null) {
                    out.println("<div id=\"erro\">");
                    out.print("<span>Login ou senha errados!</span>");
                    out.println("</div>");
                }
            %>
            <form id="form-login" name="form-login" method="post" action="main.do">
                <fieldset>
                    <legend>Faça seu Login</legend>
                    <p>
                        <label for="login">
                            <span>Email: </span>
                        </label>
                        <input type="text" id="email" value="" name="email" />
                    </p>
                    <p>
                        <label for="senha">
                            <span>Senha: </span>
                        </label>
                        <input type="password" id="senha" value="" name="senha" />
                    </p>
                    <p>Para se cadastrar <a href="cadcli.jsp">Clique aqui.</a></p>
                    <p>
                        <input type="hidden" name="action" value="logincli" id="action" />
                        <input type="submit">
                        <input type="reset">
                    </p>
                </fieldset>
            </form>
        </section>
        <footer>
            <span>iSyzza @Copyright 2011</span>
        </footer>
    </body>
</html>
