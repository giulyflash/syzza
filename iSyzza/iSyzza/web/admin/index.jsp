<%-- 
    Document   : login
    Created on : 11/11/2011, 08:31:04
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
        <h1>&Aacute;rea Administrativa</h1>
        <p>Fa&ccedil;a login para acessar</p>
        <form id="ad-form" method="POST" action="">
            <p>
                <label for="login">Login: </label>
                <input type="text" name="login" id="login" value="" />
            </p>
            <p>
                <label for="senha">Senha: </label>
                <input type="password" name="senha" id="senha" value="" />
            </p>
            <input type="submit" value="Enviar" id="enviar" />
            <input type="reset" value="Limpar" id="limpar" />
        </form>
    </body>
</html>
