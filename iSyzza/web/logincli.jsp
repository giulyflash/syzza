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
                $('#enviar').attr('disabled', 'disabled');
                $('#email').keyup(function(){
                    if ($(this).val().length < 3) {
                        $('#est-email').removeClass('acerto').addClass('erro').html('Email inv&aacute;lido');
                    }
                    else {
                        $('#est-email').removeClass('erro').addClass('acerto').html('Email v&aacute;lido');
                        ativaEnviar();
                    }
                });
                $('#senha').keyup(function(){
                    if ($(this).val().length < 5) {
                        $('#est-senha').removeClass('acerto').addClass('erro').html('A senha deve ter pelo menos 5 caracteres!');
                    }
                    else {
                        $('#est-senha').removeClass('erro').addClass('acerto').html('Senha v&aacute;lida');
                        ativaEnviar();
                    }
                });
                
                
            });
            function ativaEnviar() {
                if ($('#est-email').hasClass('acerto') && $('#est-senha').hasClass('acerto')) {
                    $('#enviar').removeAttr('disabled');
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
                    out.println("<div class=\"erro\">");
                    out.print("<span>Login ou senha errados!</span>");
                    out.println("</div>");
                }
            %>
            <form id="form-login" name="form-login" method="post" action="main.do">
                <fieldset>
                    <legend>Faça seu Login</legend>
                    <p>
                        <label for="email">
                            <span>Email: </span>
                        </label>
                        <input type="text" id="email" value="" name="email" />
                        <span id="est-email"></span>
                    </p>
                    <p>
                        <label for="senha">
                            <span>Senha: </span>
                        </label>
                        <input type="password" id="senha" value="" name="senha" />
                        <span id="est-senha"></span>
                    </p>
                    <p>Para se cadastrar <a href="cadcli.jsp">Clique aqui.</a></p>
                    <p>
                        <input type="hidden" name="action" value="logincli" id="action" />
                        <input type="submit" id="enviar" name="enviar" value="Enviar" />
                        <input type="reset" id="limpar" name="limpar" value="Limpar" />
                    </p>
                </fieldset>
            </form>
        </section>
        <footer>
            <span>iSyzza @Copyright 2011</span>
        </footer>
    </body>
</html>
