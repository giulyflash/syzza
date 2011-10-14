<%-- 
    Document   : cadastro
    Created on : 20/09/2011, 13:36:46
    Author     : Jonathan
--%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList erros = (ArrayList)request.getAttribute("erros");
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
        <style type="text/css">
            .erro {
                color: red;
                display: inline;
            }
        </style>
    </head>
    <body>
        <h2>Cadastro Cliente</h2>
        <form action="cadcli.do" method="POST" id="formcad" name="formcad" >
            <fieldset>
                <p>
                    <label for="nome">
                        <span>Nome: </span>
                        <input type="text" name="nome" id="nome" value>
                    </label>
                    <%
                        if (erros.contains(1)) {
                            out.println("<span class=\"erro\">Campo nome em branco.</span>");
                        }
                        else if (erros.contains(2)) {
                            out.println("<span class=\"erro\">Campo nome com menos de 8 caracteres.</span>");
                        }
                    %>
                </p>
                <p>
                    <label for="email">
                        <span>Email: </span>
                        <input type="email" id="email" name="email">
                    </label>
                    <%
                        if (erros.contains(3)) {
                            out.println("<span class=\"erro\">Campo email em branco.</span>");
                        }
                    %>
                </p>
                <p>
                    <label for="repemail">
                        <span>Repetir Email: </span>
                        <input type="email" id="repemail" name="repemail">
                    </label>
                    <%
                        if (erros.contains(4)) {
                            out.println("<span class=\"erro\">Emails diferentes.</span>");
                        }
                    %>
                </p>
                <p>
                    <label for="senha">
                        <span>Senha: </span>
                        <input type="password" id="senha" name="senha">
                    </label>
                    <%
                        if (erros.contains(5)) {
                            out.println("<span class=\"erro\">Campo senha em branco.</span>");
                        }
                    %>
                </p>
                <p>
                    <label for="repsenha">
                        <span>Repetir Senha: </span>
                        <input type="password" id="repsenha" name="repsenha">
                    </label>
                    <%
                        if (erros.contains(6)) {
                            out.println("<span class=\"erro\">Senhas diferentes.</span>");
                        }
                    %>
                </p>
                <p>
                    <label for="telefone">
                        <span>Telefone: </span>
                        <input type="text" id="telefone" name="telefone">
                    </label>
                    <%
                        if (erros.contains(7)) {
                            out.println("<span class=\"erro\">Campo telefone em branco.</span>");
                        }
                    %>
                </p>
                <p>
                    <label for="endereco">
                        <span>Endere&ccedil;o: </span>
                        <input type="text" id="endereco" name="endereco">
                    </label>
                    <%
                        if (erros.contains(8)) {
                            out.println("<span class=\"erro\">Campo endereco em branco.</span>");
                        }
                    %>
                </p>
                <p>
                    <input type="submit" value="Enviar" id="enviar">
                    <input type="reset" value="Limpar" id="limpar"
                </p>
            </fieldset>
        </form>

    </body>
</html>
