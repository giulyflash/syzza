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
                var iconCarregando = $('<img src="include/image/ajax-loader.gif" class="icon" />');
                $('#pizzas').html(iconCarregando);
                $('#petiscos').html(iconCarregando);
                $('#sobremesas').html(iconCarregando);
                $('#bebidas').html(iconCarregando);
                $('#novaPizza').hide();
                $('#novaPetisco').hide();
                $('#novaSobremesa').hide(); 
                $('#novaBebida').hide(); 
                loadPizzas();
                loadPetiscos();
                loadSobremesas();
                loadBebidas();
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
                        qtdPizza: $('#qtdPizza').val(),
                        sabor1: $('#sabor1').val(), 
                        sabor2: $('#sabor2').val(), 
                        sabor3: $('#sabor3').val(), 
                        sabor4: $('#sabor4').val()
                    }, function(){
                        loadPizzas();
                    });
                    $('#novaPizza').hide();
                    $('#addPizza').show();
                    
                });
                $('#addPetisco').click(function(){
                    $(this).hide();
                    $('#novaPetisco').show();
                });
                $('#cancPetisco').click(function(){
                    $('#addPetisco').show();
                    $('#novaPetisco').hide();
                });
                $('#envPetisco').click(function(){
                    $.post("main.do?action=newpedido", {cmd: "addPetiscos", 
                        petisco: $('#petisco').val(), 
                        qtd: $('#qtdPetisco').val()
                    }, function(){
                        loadPetiscos();
                    });
                    $('#novaPetisco').hide();
                    $('#addPetisco').show();
                    
                });
                $('#addSobremesa').click(function(){
                    $(this).hide();
                    $('#novaSobremesa').show();
                });
                $('#cancSobremesa').click(function(){
                    $('#addSobremesa').show();
                    $('#novaSobremesa').hide();
                });
                $('#envSobremesa').click(function(){
                    $.post("main.do?action=newpedido", {cmd: "addSobrem", 
                        sobremesa: $('#sobremesa').val(), 
                        qtdSobremesa: $('#qtdSobremesa').val()
                    }, function(){
                        loadSobremesas();
                    });
                    $('#novaSobremesa').hide();
                    $('#addSobremesa').show();
                });
                $('#addBebida').click(function(){
                    $(this).hide();
                    $('#novaBebida').show();
                });
                $('#cancBebida').click(function(){
                    $('#addBebida').show();
                    $('#novaBebida').hide();
                });
                $('#envBebida').click(function(){
                    $.post("main.do?action=newpedido", {cmd: "addBebidas", 
                        bebida: $('#bebida').val(), 
                        qtdBebida: $('#qtdBebida').val()
                    }, function(){
                        loadBebidas();
                    });
                    $('#novaBebida').hide();
                    $('#addBebida').show();
                });
            });
            function loadPizzas() {
                $.post("main.do?action=newpedido", {cmd: "loadPizzas"}, function(data) {
                    $('#pizzas').html(data);
                });
            }
            function loadPetiscos() {
                $.post("main.do?action=newpedido", {cmd: "loadPetiscos"}, function(data) {
                    $('#petiscos').html(data);
                });
            }
            function loadSobremesas() {
                $.post("main.do?action=newpedido", {cmd: "loadSobrem"}, function(data) {
                    $('#sobremesas').html(data);
                });
            }
            function loadBebidas() {
                $.post("main.do?action=newpedido", {cmd: "loadBebidas"}, function(data) {
                    $('#bebidas').html(data);
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
                <form id="formped" action="main.do?action=confirmped" method="post">
                    <fieldset>
                        <h3>Pizzas</h3>
                        <div id="pizzas"></div>
                        <input type="button" value="Adicionar Pizza" id="addPizza" />
                        <div id="novaPizza">
                            <fieldset>
                                <h3>Nova Pizza</h3>
                                <h4>Tamanho</h4>
                                <select name="tamanho" id="tamanho">
                                    <c:forEach items="${tamanhos}" var="tamanho">
                                        <option value="<c:out value="${tamanho.id}" />">
                                            <c:out value="${tamanho.nome}" />
                                            <c:out value=" = R$ ${tamanho.preco}" />
                                        </option>
                                    </c:forEach>
                                </select>
                                <h4>Sabores</h4>
                                <label for="sabor1">1/4: </label>
                                <select name="sabor1" id="sabor1">
                                    <c:forEach items="${sabores}" var="sabor">
                                        <option value="<c:out value="${sabor.id}" />">
                                            <c:out value="${sabor.nome}" />
                                        </option>
                                    </c:forEach>
                                </select>
                                <label for="sabor2">1/4: </label>
                                <select name="sabor2" id="sabor2">
                                    <c:forEach items="${sabores}" var="sabor">
                                        <option value="<c:out value="${sabor.id}" />">
                                            <c:out value="${sabor.nome}" />
                                        </option>
                                    </c:forEach>
                                </select>
                                <label for="sabor3">1/4: </label>
                                <select name="sabor3" id="sabor3">
                                    <c:forEach items="${sabores}" var="sabor">
                                        <option value="<c:out value="${sabor.id}" />">
                                            <c:out value="${sabor.nome}" />
                                        </option>
                                    </c:forEach>
                                </select>
                                <label for="sabor4">1/4: </label>
                                <select name="sabor4" id="sabor4">
                                    <c:forEach items="${sabores}" var="sabor">
                                        <option value="<c:out value="${sabor.id}" />">
                                            <c:out value="${sabor.nome}" />
                                        </option>
                                    </c:forEach>
                                </select>
                                <h4>Borda</h4>
                                <select name="borda" id="borda">
                                    <c:forEach items="${bordas}" var="borda">
                                        <option value="<c:out value="${borda.id}" />">
                                            <c:out value="${borda.nome}" />
                                            <c:out value=" = R$ ${borda.preco}" />
                                        </option>
                                    </c:forEach>
                                </select>
                                <br />
                                <label for="qtdPetisco">Qtd: </label>
                                <select id="qtdPizza" name="qtdPizza">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                                <br /><br />
                                <input type="button" id="envPizza" value="Adicionar" />
                                <input type="button" id="cancPizza" value="Cancelar" />
                            </fieldset>
                        </div>
                    </fieldset>
                    <fieldset>
                        <h3>Petiscos</h3>
                        <div id="petiscos"></div>
                        <input type="button" value="Adicionar" id="addPetisco" />
                        <div id="novaPetisco">
                            <fieldset>
                                <h3>Novo Petisco</h3>
                                <label for="petisco">Petisco: </label>
                                <select name="petisco" id="petisco">
                                    <c:forEach items="${petiscos}" var="petisco">
                                        <option value="<c:out value="${petisco.id}" />">
                                            <c:out value="${petisco.nome}" />
                                            <c:out value=" = R$ ${petisco.preco}" />
                                        </option>
                                    </c:forEach>
                                </select>
                                <label for="qtdPetisco">Qtd: </label>
                                <select id="qtdPetisco" name="qtdPetisco">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                                <br /><br />
                                <input type="button" id="envPetisco" value="Adicionar" />
                                <input type="button" id="cancPetisco" value="Cancelar" />
                            </fieldset>
                        </div>
                    </fieldset>
                    <fieldset>
                        <h3>Sobremesas</h3>
                        <div id="sobremesas"></div>
                        <input type="button" value="Adicionar" id="addSobremesa" />
                        <div id="novaSobremesa">
                            <fieldset>
                                <h3>Nova Sobremesa</h3>
                                <label for="sobremesa">Sobremesa: </label>
                                <select name="sobremesa" id="sobremesa">
                                    <c:forEach items="${sobremesas}" var="sobremesa">
                                        <option value="<c:out value="${sobremesa.id}" />">
                                            <c:out value="${sobremesa.nome}" />
                                            <c:out value=" = R$ ${sobremesa.preco}" />
                                        </option>
                                    </c:forEach>
                                </select>
                                <label for="qtdSobremesa">Qtd: </label>
                                <select id="qtdSobremesa" name="qtdSobremesa">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                                <br /><br />
                                <input type="button" id="envSobremesa" value="Adicionar" />
                                <input type="button" id="cancSobremesa" value="Cancelar" />
                            </fieldset>
                        </div>
                    </fieldset>
                    <fieldset>
                        <h3>Bebidas</h3>
                        <div id="bebidas"></div>
                        <input type="button" value="Adicionar" id="addBebida" />
                        <div id="novaBebida">
                            <fieldset>
                                <h3>Nova Bebida</h3>
                                <label for="bebida">Bebida: </label>
                                <select name="bebida" id="bebida">
                                    <c:forEach items="${bebidas}" var="bebida">
                                        <option value="<c:out value="${bebida.id}" />">
                                            <c:out value="${bebida.nome}" />
                                            <c:out value=" = R$ ${bebida.preco}" />
                                        </option>
                                    </c:forEach>
                                </select>
                                <label for="qtdBebida">Qtd: </label>
                                <select id="qtdBebida" name="qtdBebida">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                                <br /><br />
                                <input type="button" id="envBebida" value="Adicionar" />
                                <input type="button" id="cancBebida" value="Cancelar" />
                            </fieldset>
                        </div>
                    </fieldset>
                    <input type="hidden" name="cmd" value="envPedidop" /><br />
                    <input type="submit" value="Avan&ccedil;ar" id="envPedido" />
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
