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
                $('#enviar').attr('disabled', 'disabled');
                $('#email').change(function() {
                    var er = new RegExp(/^[A-Za-z0-9_\.-]{6,30}@[A-Za-z0-9]{2,30}(\.[A-Za-z]{2,6})+$/);
                    if (er.test($(this).val())) {
                        $('#est-email').removeClass('erro').addClass('acerto').html('Email v&aacute;lido');
                    } else {
                        $('#est-email').removeClass('acerto').addClass('erro').html('Email inv&aacute;lido');
                    }
                });
                $('#senha').change(function() {
                    var er = new RegExp(/^[A-Za-z0-9_\.-]{8,15}$/);
                    if (er.test($(this).val())) {
                        $('#est-senha').removeClass('erro').addClass('acerto').html('Senha v&aacute;lida');
                    } else {
                        $('#est-senha').removeClass('acerto').addClass('erro').html('A senha deve ter entre 8 e 15 caracteres!');
                    }
                });
                $('#email,#senha').blur(ativaEnviar);
            });
            function ativaEnviar() {
                if ($('#est-email').hasClass('acerto') && $('#est-senha').hasClass('acerto')) {
                    $('#enviar').removeAttr('disabled');
                } else {
                    $('#enviar').attr('disabled', 'disabled');
                }
            }
        </script>
        <style type="text/css">
            .erro {
                color: red;
            }
            .acerto {
                color: green;
            }
        </style>
    </head>
    <body>
        <div id="tudo">
            <div id="header">
                <h1>iSyzza - Sistema Gerenciador de Tele Entregas</h1>
            </div>
            <div id="conteudo">
                <form action="main.do?action=login" method="post">
                    <fieldset>
                        <legend>Login</legend>
                        <%
                            Integer status = (Integer) request.getAttribute("status");
                            if (status != null) {
                                out.println("<div class=\"erro\">");
                                out.print("<span>Login ou senha errados!</span>");
                                out.println("</div>");
                            }
                        %>
                        <p>
                            <label for="email">Email:<br /></label>
                            <input type="text" name="email" id="email" size="15" value="" />
                            <span id="est-email"></span>
                        </p>
                        <p>
                            <label for="senha">Senha:<br /></label>
                            <input type="password" name="senha" id="senha" size="15" value="" />
                            <span id="est-senha"></span>
                        </p>
                        <p>
                            <input type="hidden" name="cmd" value="loginclip" id="cmd" />
                            <input type="submit" id="enviar" name="enviar" value="Enviar" />
                            <input type="reset" id="limpar" name="limpar" value="Limpar" />
                            <br />Ainda não é cadastrado? <a href="main.do?action=cadcli">Clique aqui.</a>
                        </p>
                        <div id="erros">

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
