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
        <script src="include/js/jquery.meio.mask.min.js" type="text/javascript"> </script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#telefone').setMask({mask: '(99) 9999-9999', autoTab: false});
                $('#cep').setMask({mask: '99999-999', autoTab: false});
                $('#pf').setMask({mask: '999.999.999-99', autoTab: false});
                
                $('.inputs').hide();
                $('#final').hide();
                $('#canc').hide();
                $('#editar').click(function(){
                    changeState('nome');
                    changeState('data');
                    changeState('nome');
                    changeState('cpf');
                    changeState('cep');
                    changeState('log');
                    changeState('bairro');
                    changeState('cid');
                    changeState('num');
                    changeState('comp');
                    changeState('telefone');
                    $('#editar').hide();
                    $('#final').show();
                    $('#canc').show();
                });
                $('#canc').click(function(){
                    $('.inputs').hide();
                    $('#final').hide();
                    $('#canc').hide();
                    $('#editar').show();
                    $('.spans').show();
                });
                $('#final').click(function(){
                    $.post('main.do?action=profile', {cmd: 'upCliente',
                        cep: $('#cep').val(), 
                        log: $('#log').val(),
                        bairro: $('#bairro').val(),
                        cid: $('#cid').val(),
                        num: $('#num').val(),
                        comp: $('#comp').val(),
                        telefone: $('#telefone').val(),
                        nome: $('#nome').val(),
                        data: $('#data').val(), 
                        cpf: $('#cpf').val()
                    });
                    $('#span-cep').html($('#cep').val());
                    $('#span-log').html($('#log').val());
                    $('#span-bairro').html($('#bairro').val());
                    $('#span-cid').html($('#cid').val());
                    $('#span-num').html($('#num').val());
                    $('#span-comp').html($('#comp').val());
                    $('#span-telefone').html($('#telefone').val());
                    $('#span-nome').html($('#nome').val());
                    $('#span-data').html($('#data').val());
                    $('#span-cpf').html($('#cpf').val());
                    $('.inputs').hide();
                    $('#final').hide();
                    $('#canc').hide();
                    $('#editar').show();
                    $('.spans').show();
                });
            });
            function changeState(id) {
                var valor = $('#span-'+id).html();
                $('#'+id).show();
                $('#'+id).val(valor);
                $('#span-'+id).hide();
            }
            
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
                        <input type="button" value="Editar" id="editar" />
                        <input type="button" value="Finalizar" id="final" />
                        <input type="button" value="Cancelar" id="canc" />
                        <h3>Dados Pessoais</h3>
                        <div class="campos">
                            <label for="nome">Nome: </label><span id="span-nome" class="spans"><%=cliente.getNome()%></span><br />
                            <input type="text" id="nome" name="nome" value="" size="25" class="inputs" />
                            <div id="est-nome" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="telefone">Telefone: </label><span id="span-telefone" class="spans"><%=cliente.getTelefone()%></span><br />
                            <input type="text" id="telefone" name="telefone" value="" class="inputs" />
                            <div id="est-telefone" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="data">Data de Nascimento: </label><span id="span-data" class="spans"><%=data%></span><br />
                            <input type="text" id="data" name="data" value="" size="10" class="inputs" />
                            <div id="est-data" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="cpf">Cpf: </label><span id="span-cpf" class="spans"><%=cliente.getCpf()%></span><br />
                            <input type="text" id="cpf" name="cpf" value="" size="15" class="inputs" />
                            <div id="est-cpf" class="estados"></div>    
                        </div>
                    </fieldset>
                    <fieldset>
                        <h3>Dados de Acesso</h3>
                        <div class="campos">
                            <label for="email">Email: </label><span id="span-email" class="spans"><%=cliente.getEmail()%></span><br />
                            <input type="text" id="email" name="email" value="" size="10" class="inputs" />
                            <div id="est-email" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="senha">Senha: </label><span id="span-senha" class="spans"><%=cliente.getSenha()%></span><br />
                            <input type="text" id="senha" name="senha" value="" size="15" class="inputs" />
                            <div id="est-senha" class="estados"></div>    
                        </div>
                    </fieldset>
                    <fieldset>
                        <h3>Endere&ccedil;o</h3>
                        <div class="campos">
                            <label for="cep">Cep: </label><span id="span-cep" class="spans"><%=pedacos[3]%></span><br />
                            <input type="text" id="cep" name="cep" value="" size="15" class="inputs" />
                            <div id="est-cep" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="log">Logradouro:</label><span id="span-log" class="spans"><%=pedacos[0]%></span><br />
                            <input type="text" id="log" name="log" value="" size="30" class="inputs" />
                            <div id="est-log" class="estados"> </div>
                        </div>
                        <div class="campos">
                            <label for="bairro">Bairro: </label><span id="span-bairro" class="spans"><%=pedacos[1]%></span><br />
                            <input type="text" id="bairro" name="bairro" value="" size="20" class="inputs" />
                            <div id="est-bairro" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="cid">Cidade: </label><span id="span-cid" class="spans"><%=pedacos[2]%></span><br />
                            <input type="text" id="cid" name="cid" value="" size="20" class="inputs" />
                            <div id="est-cid" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="num">Nº: </label><span id="span-num" class="spans"><%=pedacos[4]%></span><br />
                            <input type="text" id="num" name="num" value="" size="5" class="inputs" />
                            <div id="est-num" class="estados"></div>
                        </div>
                        <div class="campos">
                            <label for="comp">Complemento: </label><span id="span-comp" class="spans"><%= (pedacos.length == 6) ? pedacos[5] : ""%></span><br />
                            <input type="text" id="comp" name="comp" value="" size="5" class="inputs" />
                            <div id="est-comp" class="estados"></div>
                        </div>
                    </fieldset>
                    <div class="campos">
                        <input type="hidden" name="cmd" value="updateclip" id="action" />
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
