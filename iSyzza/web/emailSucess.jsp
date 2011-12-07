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
        <link rel="stylesheet" href="include/css/principal.css" type="text/css" />
        <script src="include/js/jquery-1.7.min.js"> </script>
        <script type="text/javascript">
        </script>
    </head>
    <body>
        <div id="tudo">
            <div class="desc-topo" id="secAdm"><a href="main.do?action=loginAdm">Administra&ccedil;&atilde;o</a></div>
            <div id="header">
                <h1>iSyzza - Sistema Gerenciador de Tele Entregas</h1>
            </div>
            <div id="conteudo">
                <h4>Email Enviado com Sucesso!!</h4>
                <p>Para voltar ao login <a href='main.do?action=logincli'>Clique aqui.</a></p>
            </div>
            <div class="footer">
                <span><a href="main.do?action=homec">Home</a></span> | 
                <span><a href="main.do?action=logincli">Login</a></span> | 
                <span><a href="main.do?action=profile">Profile</a></span>
            </div>
            <div class="footer">
                <span>iSyzza @Copyright 2011 - Todos os direitos reservados</span>
            </div>
        </div>
    </body>
</html>
