<%-- 
    Document   : index
    Created on : 08/09/2011, 15:13:02
    Author     : Jonathan e Gabriel
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@include file="autenticaAdm.jsp" %>
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
                loadPedidos();
            });
            function loadPedidos() {
                $.post('main.do?action=homeCozinha', {cmd: 'pedAberto'}, function(data){
                    $('#pedidos').html(data)
                });
            }
        </script>
    </head>
    <body>
        <div id="tudo">
            <div class="desc-topo" id="secCliente">Voc&ecirc; est&aacute; logado como <a href="main.do?action=profile">${admin.nome}</a>.&nbsp;&nbsp;
                <form id="flogout" method="post" action="main.do?action=homec" name="flogout" style="float: right;">
                    <input type="hidden" name="cmd" value="logoutp" /> 
                    <a href="javascript:document.flogout.submit()">&nbsp;Sair&nbsp;</a>
                </form>
            </div>
            <div id="header">
                <h1>iSyzza - Sistema Gerenciador de Tele Entregas</h1>
            </div>
            <div id="conteudo">
                <h3>Pedidos Abertos</h3>
                <div id="pedidos"></div>
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