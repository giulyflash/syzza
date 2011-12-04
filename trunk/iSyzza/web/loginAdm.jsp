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
            $(document).ready(function(){
                
            });
        </script>
    </head>
    <body>
        <div id="tudo">
            <div id="header">
                <h1>iSyzza - Sistema Gerenciador de Tele Entregas</h1>
            </div>
            <div id="conteudo">
                <div id="erros">

                </div>
                <form id="ad-form" method="post" action="main.do?action=loginAdm">
                    <fieldset>
                        <legend>Administra&ccedil;&atilde;o</legend>
                        <div class="campos">
                            <label for="email">Login:<br /></label>
                            <input type="text" name="email" id="email" size="15" value="" />
                            <div id="est-email" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="senha">Senha:<br /></label>
                            <input type="password" name="senha" id="senha" size="15" value="" />
                            <div id="est-senha" class="estados"></div>
                        </div>
                        <div class="campos">
                            <input type="hidden" name="cmd" value="loginAdmp" id="cmd" />
                            <input type="submit" id="enviar" name="enviar" value="Enviar" />
                            <input type="reset" id="limpar" name="limpar" value="Limpar" />
                        </div>
                    </fieldset>
                </form>
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
