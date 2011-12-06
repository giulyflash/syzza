<%-- 
    Document   : index
    Created on : 08/09/2011, 15:13:02
    Author     : Jonathan e Gabriel
--%>

<%@page import="java.util.List"%>
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
        <script src="include/js/jquery.meio.mask.min.js" type="text/javascript"> </script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#telefone').setMask({mask: '(99) 9999-9999', autoTab: false});
                //$('#data').setMask({mask: '99/99/9999', autoTab: false});
                //$('#cpf').setMask({mask: '999.999.999-99', autoTab: false});
                $('#cep').setMask({mask: '99999-999', autoTab: false});
                $('.inputs').hide();
                loadItens("Pizzas", "pizzas");
                loadItens("Petiscos", "petiscos");
                loadItens("Sobrem", "sobremesas");
                loadItens("Bebidas", "bebidas");
                $('#voltar').click(function(){
                    location.href = "main.do?action=newpedido";
                });
                
                $('#editar').click(function(){
                    changeState('cep');
                    changeState('log');
                    changeState('bairro');
                    changeState('cid');
                    changeState('num');
                    changeState('comp');
                    changeState('telefone');
                });
            });
            function changeState(id) {
                var valor = $('#span-'+id).html();
                $('#'+id).show();
                $('#'+id).val(valor);
                $('#span-'+id).hide();
            }
            
            function loadItens(item, id) {
                $.post("main.do?action=newpedido", {cmd: "load"+item}, function(data) {
                    $('#'+id).html(data);
                });
            }
        </script>
    </head>
    <body>
        <div id="tudo">
            <div class="desc-topo" id="secCliente">Voc&ecirc; est&aacute; logado como <a href="main.do?action=profile">${cliente.nome}</a>.&nbsp;&nbsp;
                <form id="flogout" method="post" action="main.do?action=homec" name="flogout" style="float: right;"><input type="hidden" name="cmd" value="logoutp" /> <a href="javascript:document.flogout.submit()">&nbsp;Sair&nbsp;</a></form></div>
            <div id="header">
                <h1>iSyzza - Sistema Gerenciador de Tele Entregas</h1>
            </div>
            <div id="conteudo">
                <h2>Confirma&ccedil;&atilde;o de Pedido</h2>
                <p>Por favor, confirme os itens adicionados ao pedido.</p>
                <h3>Pizzas</h3>
                <div id="pizzas"></div>
                <h3>Petiscos</h3>
                <div id="petiscos"></div>
                <h3>Sobremesas</h3>
                <div id="sobremesas"></div>
                <h3>Bebidas</h3>
                <div id="bebidas"></div>
                <hr />
                <form action="main.do?action=newpedido" method="post">
                    <%
                        Cliente cliente = (Cliente) session.getAttribute("cliente");
                        String[] pedacos = cliente.getEndereco().split("\\|");
                    %>
                    <h3>Dados para Entrega</h3>
                    <a href="#" id="editar">editar</a>
                    <div class="campos">
                        <label for="cep">Cep: </label><span id="span-cep"><%=pedacos[0]%></span><br />
                        <input type="text" id="cep" name="cep" value="" size="15" class="inputs" />
                        <div id="est-cep" class="estados"></div>
                    </div>
                    <div class="campos">
                        <label for="log">Logradouro: </label><span id="span-log"><%=pedacos[1]%></span><br />
                        <input type="text" id="log" name="log" value="" size="30" class="inputs" />
                        <div id="est-log" class="estados"> </div>
                    </div>
                    <div class="campos">
                        <label for="bairro">Bairro: </label><span id="span-bairro"><%=pedacos[2]%></span><br />
                        <input type="text" id="bairro" name="bairro" value="" size="20" class="inputs" />
                        <div id="est-bairro" class="estados"></div>
                    </div>
                    <div class="campos">
                        <label for="cid">Cidade: </label><span id="span-cid"><%=pedacos[3]%></span><br />
                        <input type="text" id="cid" name="cid" value="" size="20" class="inputs" />
                        <div id="est-cid" class="estados"></div>
                    </div>
                    <div class="campos">
                        <label for="num">Nº: </label><span id="span-num"><%=pedacos[4]%></span><br />
                        <input type="text" id="num" name="num" value="" size="5" class="inputs" />
                        <div id="est-num" class="estados"></div>
                    </div>
                    <div class="campos">
                        <label for="comp">Complemento: </label><span id="span-comp"><%= (pedacos.length == 6) ? pedacos[5] : "---"%></span><br />
                        <input type="text" id="comp" name="comp" value="" size="5" class="inputs" />
                        <div id="est-comp" class="estados"></div>
                    </div>
                    <div class="campos">
                        <label for="telefone">Telefone: </label><span id="span-telefone"><%=cliente.getTelefone()%></span><br />
                        <input type="text" id="telefone" name="telefone" value="" class="inputs" />
                        <div id="est-telefone" class="estados"></div>
                    </div>
                    <input type="hidden" name="cmd" value="confirmped" />
                    <input type="submit" value="Confirmar" />
                    <input type="button" value="Voltar" id="voltar" />
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
