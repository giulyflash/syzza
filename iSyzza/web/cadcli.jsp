<%-- 
    Document   : cadastro
    Created on : 20/09/2011, 13:36:46
    Author     : Jonathan
--%>

<%@page import="java.util.ArrayList"%>
<%
    ArrayList erros = (ArrayList) request.getAttribute("erros");
    if (erros == null) {
        erros = new ArrayList();
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iSyzza</title>
        <link rel="shortcut icon" href="include/image/icon.ico" type="image/ico" />
        <script src="include/js/jquery-1.7.min.js"> </script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#email').blur(function() {
                    $.post('main.do', {action: 'emailval'}, function(data) {
                        if (data==1) {
                            $('#est-senha').removeClass('erro').addClass('acerto').html('Email dispon&iacute.vel!');
                        }
                        else {
                            $('#est-senha').removeClass('acerto').addClass('erro').html('Email j&aacute em uso!');
                        }
                    });
                }); 
            });
        </script>
        <style type="text/css">
            .erro {
                color: red;
                display: inline;
            }
        </style>
    </head>
    <body>
        <h2>Cadastro Cliente</h2>
        <form action="main.do" method="POST" id="formcad" name="formcad" >
            <fieldset>
                <p>
                    <label for="nome">
                        <span>Nome: </span>
                    </label>
                    <input type="text" name="nome" id="nome" value />
                    <span id="est-nome">
                        <%
                            if (erros.contains(1)) {
                                out.println("<span class=\"erro\">Campo nome em branco.</span>");
                            } else if (erros.contains(2)) {
                                out.println("<span class=\"erro\">Campo nome com menos de 8 caracteres.</span>");
                            }
                        %>
                    </span>

                </p>
                <p>
                    <label for="email">
                        <span>Email: </span>
                    </label>
                    <input type="email" id="email" name="email" />
                    <%
                        if (erros.contains(3)) {
                            out.println("<span class=\"erro\">Campo email em branco.</span>");
                        }
                    %>
                </p>
                <p>
                    <label for="repemail">
                        <span>Repetir Email: </span>
                    </label>
                    <input type="email" id="repemail" name="repemail" />
                    <%
                        if (erros.contains(4)) {
                            out.println("<span class=\"erro\">Emails diferentes.</span>");
                        }
                    %>
                </p>
                <p>
                    <label for="senha">
                        <span>Senha: </span>
                    </label>
                    <input type="password" id="senha" name="senha" />
                    <%
                        if (erros.contains(5)) {
                            out.println("<span class=\"erro\">Campo senha em branco.</span>");
                        }
                    %>
                </p>
                <p>
                    <label for="repsenha">
                        <span>Repetir Senha: </span>
                    </label>
                    <input type="password" id="repsenha" name="repsenha" />
                    <%
                        if (erros.contains(6)) {
                            out.println("<span class=\"erro\">Senhas diferentes.</span>");
                        }
                    %>
                </p>
                <p>
                    <label for="telefone">
                        <span>Telefone: </span>
                    </label>
                    <input type="text" id="telefone" name="telefone" />
                    <%
                        if (erros.contains(7)) {
                            out.println("<span class=\"erro\">Campo telefone em branco.</span>");
                        }
                    %>
                </p>
                <p>
                    <label for="endereco">
                        <span>Endere&ccedil;o: </span>
                    </label>
                    <input type="text" id="endereco" name="endereco" />
                    <%
                        if (erros.contains(8)) {
                            out.println("<span class=\"erro\">Campo endereco em branco.</span>");
                        }
                    %>
                </p>
                <p>
                    <label for="cpf">
                        <span>CPF: </span>
                    </label>
                    <input type="text" id="cpf" name="cpf" />
                    <%
                        if (erros.contains(9)) {
                            out.println("<span class=\"erro\">Campo cpf em branco.</span>");
                        }
                    %>
                </p>
                <p>
                    <input type="hidden" name="action" value="cadcli" id="action" />
                    <input type="submit" value="Enviar" id="enviar">
                    <input type="reset" value="Limpar" id="limpar"
                </p>
            </fieldset>
        </form>

    </body>
</html>
