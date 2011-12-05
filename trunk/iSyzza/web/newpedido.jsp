<%--
    Document   : index
    Created on : 08/09/2011, 15:13:02
    Author     : Jonathan e Gabriel
--%>

<%@page import="entity.Pedido"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                $('#novaPizza').hide();
                loadPizzas();
                $('#addPizza').click(function(){
                    $(this).hide();
                    $('#novaPizza').show();
                });
                $('#cancPizza').click(function(){
                    $('#addPizza').show();
                    $('#novaPizza').hide();
                });
                
                $('#envPizza').click(function(){
                    $.post("main.do?action=newpedido", {cmd: "addPizzas", 
                        tamanho: $('#tamanho').val(),
                        borda: $('#borda').val(), 
                        sabor1; });
                });
            });
            function loadPizzas() {
                var iconCarregando = $('<img src="include/image/ajax-loader.gif" class="icon" />');
                $('#pizzas').html(iconCarregando);
                $.post("main.do?action=newpedido", {cmd: "loadPizzas"}, function(data) {
                    $('#pizzas').html(data);
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
                <h2>Novo Pedido</h2>
                <form id="formped" action="main.do?action=pedidop" method="post">
                    <fieldset>
                        <h3>Pizzas Adicionadas</h3>
                        <div id="pizzas"></div>
                        <input type="button" value="Adicionar" id="addPizza" />
                        <div id="novaPizza">
                            <fieldset>
                                <h3>Nova Pizza</h3>
                                <h4>Tamanho</h4>
                                <select name="tamanho" id="tamanho">
                                    <c:forEach items="${tamanhos}" var="tamanho">
                                        <option value="<c:out value="${tamanho.id}" />"><c:out value="${tamanho.nome}" /><c:out value=" = R$ ${tamanho.preco}" /></option>
                                    </c:forEach>
                                </select>
                                <h4>Sabores</h4>
                                <label for="sabor1">1/4: </label>
                                <select name="sabor1" id="sabor1">
                                    <c:forEach items="${sabores}" var="sabor">
                                        <option value="<c:out value="${sabor.id}" />"><c:out value="${sabor.nome}" /></option>
                                    </c:forEach>
                                </select>
                                <label for="sabor2">1/4: </label>
                                <select name="sabor2" id="sabor2">
                                    <c:forEach items="${sabores}" var="sabor">
                                        <option value="<c:out value="${sabor.id}" />"><c:out value="${sabor.nome}" /></option>
                                    </c:forEach>
                                </select>
                                <label for="sabor3">1/4: </label>
                                <select name="sabor3" id="sabor3">
                                    <c:forEach items="${sabores}" var="sabor">
                                        <option value="<c:out value="${sabor.id}" />"><c:out value="${sabor.nome}" /></option>
                                    </c:forEach>
                                </select>
                                <label for="sabor4">1/4: </label>
                                <select name="sabor4" id="sabor4">
                                    <c:forEach items="${sabores}" var="sabor">
                                        <option value="<c:out value="${sabor.id}" />"><c:out value="${sabor.nome}" /></option>
                                    </c:forEach>
                                </select>
                                <h4>Borda</h4>
                                <select name="borda" id="borda">
                                    <c:forEach items="${bordas}" var="borda">
                                        <option value="<c:out value="${borda.id}" />"><c:out value="${borda.nome}" /><c:out value=" = R$ ${borda.preco}" /></option>
                                    </c:forEach>
                                </select>
                                <br /><br />
                                <input type="button" id="envPizza" value="Adicionar" />
                                <input type="button" id="cancPizza" value="Cancelar" />
                            </fieldset>
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
