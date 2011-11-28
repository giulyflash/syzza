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
                $('#formlogin').submit(function(){
                    $('#erros').hide();
                    var ok = true;
                    if ($('#email').val() == "") {
                        ok = false;
                        var mens = $('<span class=\"erro\">Campo em branco!</span>');
                        $('#est-email').html(mens);
                    }
                    if ($('#senha').val() == "") {
                        ok = false;
                        var mens = $('<span class=\"erro\">Campo em branco!</span>');
                        $('#est-senha').html(mens);
                    }
                    if (!ok) {
                        var mens = 'Para fazer login, corrija os erros destacados em vermelho!';
                        $('#erros').html(mens).fadeIn(2000);
                        return false;
                    }
                });
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
                <form action="main.do?action=logincli" method="post" id="formlogin">
                    <fieldset>
                        <%
                            Integer status = (Integer) request.getAttribute("status");
                            System.out.println(status);
                            if (status != null) {
                                out.print("<span class=\"erro\">Login ou senha errados!</span>");
                            }
                        %>
                        <legend>Login</legend>
                        <div class="campos">
                            <label for="email">Email:<br /></label>
                            <input type="text" name="email" id="email" size="15" value="" />
                            <div id="est-email" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="senha">Senha:<br /></label>
                            <input type="password" name="senha" id="senha" size="15" value="" />
                            <div id="est-senha" class="estados"></div>
                        </div>
                        <div class="campos">
                            <input type="hidden" name="cmd" value="loginclip" id="cmd" />
                            <input type="submit" id="enviar" name="enviar" value="Enviar" />
                            <input type="reset" id="limpar" name="limpar" value="Limpar" />
                            <br />Ainda não é cadastrado? <a href="main.do?action=cadcli">Clique aqui.</a>
                        </div>
                    </fieldset>
                </form>
            </div>
            <div id="footer">
                <span>iSyzza @Copyright 2011 - Todos os direitos reservados</span>
            </div>
        </div>
    </body>
</html>
