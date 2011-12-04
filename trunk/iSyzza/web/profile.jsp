<%-- 
    Document   : index
    Created on : 08/09/2011, 15:13:02
    Author     : Jonathan e Gabriel
--%>

<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@include file="autentica.jsp" %>
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
                $('input').hide();
            });
        </script>
    </head>
    <body>
        <%
            Cliente cliente = (Cliente) session.getAttribute("cliente");
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String data = "";
            data = formato.format(cliente.getData_nasc());
            String[] pedacos = cliente.getEndereco().split("\\|");
            System.out.println(cliente.getEndereco());
            for (int i = 0; i < pedacos.length; i++) {
                System.out.println(pedacos[i]);
            }

        %>
        <div id="tudo">
            <div class="desc-topo" id="secCliente">Voc&ecirc; est&aacute; logado como <a href="main.do?action=contacli">${cliente.nome}</a>.&nbsp;&nbsp;
                <form id="flogout" method="post" action="main.do?action=homec" name="flogout" style="float: right;"><input type="hidden" name="cmd" value="logoutp" /> <a href="javascript:document.flogout.submit()">&nbsp;Sair&nbsp;</a></form>
            </div>
            <div id="header">
                <h1>iSyzza - Sistema Gerenciador de Tele Entregas</h1>
            </div>
            <div id="conteudo">
                <form id="form-up" action="main.do?action=profile" method="post">
                    <fieldset>
                        <h3>Dados Pessoais</h3>
                        <div class="campos">
                            <label for="nome">Nome: </label><span id="span-nome"><%=cliente.getNome()%></span><br />
                            <input type="text" id="nome" name="nome" value="" size="25" />
                            <div id="est-nome" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="telefone">Telefone: </label><span id="span-telefone"><%=cliente.getTelefone()%></span><br />
                            <input type="text" id="telefone" name="telefone" value="" />
                            <div id="est-telefone" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="data">Data de Nascimento: </label><span id="span-data"><%=data%></span><br />
                            <input type="text" id="data" name="data" value="" size="10" />
                            <div id="est-data" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="cpf">Cpf: </label><span id="span-cpf"><%=cliente.getCpf()%></span><br />
                            <input type="text" id="cpf" name="cpf" value="" size="15" />
                            <div id="est-cpf" class="estados"></div>    
                        </div>
                    </fieldset>
                    <fieldset>
                        <h3>Dados de Acesso</h3>
                        <div class="campos">
                            <label for="email">Email: </label><span id="span-email"><%=cliente.getEmail()%></span><br />
                            <input type="text" id="email" name="email" value="" size="10" />
                            <div id="est-email" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="senha">Senha: </label><span id="span-senha"><%=cliente.getSenha()%></span><br />
                            <input type="text" id="senha" name="senha" value="" size="15" />
                            <div id="est-senha" class="estados"></div>    
                        </div>
                    </fieldset>
                    <fieldset>
                        <h3>Endere&ccedil;o</h3>
                        <div class="campos">
                            <label for="cep">Cep: </label><span id="span-cep"><%=pedacos[0]%></span><br />
                            <input type="text" id="cep" name="cep" value="" size="15" />
                            <div id="est-cep" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="log">Logradouro:</label><span id="span-log"><%=pedacos[1]%></span><br />
                            <input type="text" id="log" name="log" value="" size="30" />
                            <div id="est-log" class="estados"> </div>
                        </div>
                        <div class="campos">
                            <label for="bairro">Bairro: </label><span id="span-bairro"><%=pedacos[2]%></span><br />
                            <input type="text" id="bairro" name="bairro" value="" size="20" />
                            <div id="est-bairro" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="cid">Cidade: </label><span id="span-cid"><%=pedacos[3]%></span><br />
                            <input type="text" id="cid" name="cid" value="" size="20" />
                            <div id="est-cid" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="num">Nº: </label><span id="span-num"><%=pedacos[4]%></span><br />
                            <input type="text" id="num" name="num" value="" size="5" />
                            <div id="est-num" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="comp">Complemento: </label><span id="span-comp"><%= (pedacos.length == 6) ? pedacos[5] : ""%></span><br />
                            <input type="text" id="comp" name="comp" value="" size="5" />
                            <div id="est-comp" class="estados"></div>
                        </div>
                    </fieldset>
                    <div class="campos">
                        <input type="hidden" name="cmd" value="updateclip" id="action" />
                        <input type="submit" value="Enviar" id="enviar" />
                        <input type="reset" value="Limpar" id="limpar" />
                        <a href="main.do?action=homec" id="back"><input type="button" value="Voltar" id="voltar" /></a>
                    </div>
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
