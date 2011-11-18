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
                $('#email').change(function() {
                    $('#est-email').ajaxStart(function() {
                        var iconCarregando = $('<img src="include/image/ajax-loader.gif"/>');
                        $(this).html(iconCarregando);
                    });
                    $.post('main.do', {action: 'emailval', email: $('#email').val()}, function(data) {
                        var espera = function(){
                            if (data==1) {
                                $('#est-email').removeClass('erro').addClass('acerto').html('Email dispon&iacute;vel!');
                            }
                            else if(data==0) {
                                $('#est-email').removeClass('acerto').removeClass('erro').addClass('erro').html('Email j&aacute; em uso!');
                            }
                            else if(data==2){
                                $('#est-email').removeClass('acerto').addClass('erro').html('Email inv&aacute;lido!');
                            }
                        }
                        setTimeout(espera, 3000);
                    });                   
                }); 
            });
        </script>
        <style type="text/css">
            .erro {
                color: red;
                display: inline;
            }
            .acerto {
                color: green;
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
                    <input type="text" name="nome" id="nome" value="" />
                    <span id="est-nome"
                        <%
                            if (erros.contains(1)) {
                                out.println(" class=\"erro\">Campo nome em branco!");
                            } else if (erros.contains(2)) {
                                out.println(" class=\"erro\">Campo nome com menos de 8 caracteres!");
                            } else {
                                out.println(">");
                            }
                        %>
                    </span>
            </p>
            <p>
                <label for="email">
                    <span>Email: </span>
                </label>
                <input type="text" id="email" name="email" value="" />
                <span id="est-email"
                      <%
                          if (erros.contains(3)) {
                              out.print(" class=\"erro\">Campo email em branco.");
                          } else {
                              out.print(">");
                          }
                      %>
            </span>
        </p>
        <p>
            <label for="repemail">
                <span>Repetir Email: </span>
            </label>
            <input type="text" id="repemail" name="repemail" value="" />
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
