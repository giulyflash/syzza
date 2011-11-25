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
        <link rel="stylesheet" href="include/css/principal.css" type="text/css" />
        <script src="include/js/jquery-1.7.min.js" type="text/javascript"> </script>
        <script src="include/js/jquery.meio.mask.min.js" type="text/javascript"> </script>
        <script type="text/javascript">
            //<![CDATA[
            $(document).ready(function(){
                $('#log, #bairro, #cid').attr('disabled', 'disabled');
                //Mascaras
                $('#telefone').setMask({mask: '(99) 9999-9999', autoTab: false});
                $('#data').setMask({mask: '99/99/9999', autoTab: false});
                $('#cpf').setMask({mask: '999.999.999-99', autoTab: false});
                $('#cep').setMask({mask: '99999-999', autoTab: false});
                
                //Mudando o background dos inputs
                $('input[type=text], input[type=password]').focus(function(){
                    $(this).css('backgroundColor', '#ffc0c9');
                }).blur(function(){
                    $(this).css('backgroundColor', '#fff');
                });
                
                $('#email').change(function() {
                    var iconCarregando = $('<img src="include/image/ajax-loader.gif" class="icon" />');
                    $('#est-email').html(iconCarregando);
                    $.post('main.do?action=cadcli', {cmd: 'emailvalp', email: $('#email').val()}, function(data) {
                        var espera = function(){
                            if (data==1) {
                                var mens = $('<span class="acerto">Ok</span>');
                                $('#est-email').html(mens);
                            }
                            else if(data==0) {
                                var mens = $('<span class="erro">Email j&aacute; em uso!</span>');
                                $('#est-email').html(mens);
                            }
                            else if(data==2){
                                var mens = $('<span class="erro">Email inv&aacute;lido!</span>');
                                $('#est-email').html(mens);
                            }
                        }
                        setTimeout(espera, 2000);
                    });                   
                });
                
                $('#cep').change(function() {
                    var er = new RegExp(/^[0-9]{5}-[0-9]{3}$/);
                    if (!er.test($(this).val())) {
                        var mens = $('<span class="erro">Cep inv&aacute;lido!</span>');
                        $('#est-cep').html(mens);
                    } else {
                        var iconCarregando = $('<img src="include/image/ajax-loader.gif" class="icon" />');
                        $('#est-cep, #est-log, #est-bairro, #est-cid').html(iconCarregando);
                        $.getScript('http://cep.republicavirtual.com.br/web_cep.php?formato=javascript&cep="'+$("#cep").val(), function() {
                            if (unescape(resultadoCEP['resultado']) == 1) {
                                $('#log').val(unescape(resultadoCEP['tipo_logradouro']) + ' ' + unescape(resultadoCEP['logradouro']));
                                $('#bairro').val(unescape(resultadoCEP['bairro']));
                                $('#cid').val(unescape(resultadoCEP['cidade']));
                                var mens = $('<span class="acerto">Ok</span>');
                                $('#est-cep, #est-log, #est-bairro, #est-cid').html(mens);
                            }
                            else {
                                var mens = $('<span class="erro">Cep n&atilde;o encontrado!</span>');
                                $('#est-cep').html(mens);
                                $('#est-log, #est-num, #est-bairro, #est-cid').children().remove();
                                $('#log, #bairro, #cid').val('');
                            }
                        });
                    }
                });
                //Validando o nome
                $('#nome').change(function(){
                    if ($(this).val().length < 8) {
                        var mens = $('<span class="erro">Campo nome deve ter pelo menos 8 caracteres!</span>');
                    } else {
                        var mens = $('<span class="acerto">Ok</span>');
                    }
                    $('#est-nome').html(mens);
                });
                //Validando o repetir email
                $('#repemail').change(function(){
                    if ($(this).val() != $('#email').val()) {
                        var mens = $('<span class="erro">Emails n&atilde; coincidem!</span>');
                    } else {
                        var mens = $('<span class="acerto">Ok</span>');
                    }
                    $('#est-repemail').html(mens);
                });
                //Validando a senha
                $('#senha').change(function(){
                    if ($(this).val().length < 8) {
                        var mens = $('<span class="erro">Senha deve ter entre 8 e 25 caracteres!</span>');
                    } else {
                        var mens = $('<span class="acerto">Ok</span>');
                    }
                    $('#est-senha').html(mens);
                });
                //Validando repetir senha
                $('#repsenha').change(function(){
                    if ($(this).val() != $('#senha').val()) {
                        var mens = $('<span class="erro">Senhas n&atilde;o coincidem!</span>');
                    } else {
                        var mens = $('<span class="acerto">Ok</span>');
                    }
                    $('#est-repsenha').html(mens);
                });
                //Validando telefone
                $('#telefone').change(function(){
                    var er = new RegExp(/^\([0-9]{2}\) [0-9]{4}-[0-9]{4}$/);
                    if (!er.test($(this).val())) {
                        var mens = $('<span class="erro">Telefone inv&aacute;lido!</span>');
                    } else {
                        var mens = $('<span class="acerto">Ok</span>');
                    }
                    $('#est-telefone').html(mens);
                });
                //Validando data de nascimento
                $('#data').change(function(){
                    var er = new RegExp(/^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/);
                    if (!er.test($(this).val())) {
                        var mens = $('<span class="erro">Data de nascimento inv&aacute;lida!</span>');
                    } else {
                        var dataAtual = new Date();
                        var aux = $(this).val().split('/');
                        var dia = aux[0];
                        var mes = aux[1]; 
                        var ano = aux[2];
                        var status = true;
                        if (mes < 01 || mes > 12 || dia < 01 || dia > 31 || ano < 1900 || ano > dataAtual.getFullYear()) {
                            status = false;
                        } else {
                            switch(mes) {
                                case '04':
                                case '06':
                                case '09':
                                case '11':
                                    if (dia > 30) {
                                        status = false;
                                    }
                                    break;
                                case '02':
                                    if (((!(ano % 4)) && (ano % 100)) || (!(ano % 400))) {
                                        if (dia > 29) {
                                            status = false;
                                        }
                                    } else {
                                        if (dia > 28) {
                                            status = false;
                                        }
                                    }
                                    break;
                            }
                        }
                        if (!status) {
                            var mens = $('<span class="erro">Data de nascimento inv&aacute;lida!</span>');
                        } else if(ano > dataAtual.getFullYear()-16) {
                            var mens = $('<span class="erro">Proibido para menores de 16 anos!</span>');
                        } else {
                            var mens = $('<span class="acerto">Ok</span>');
                        }
                    }
                    $('#est-data').html(mens);
                });
                //Validando cpf
                $('#cpf').change(function(){
                    var er = new RegExp(/^[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}$/);
                    if (!er.test($(this).val())) {
                        var mens = $('<span class="erro">Cpf inv&aacute;lido!</span>');
                    } else {
                        var aux1 = $(this).val().split('.');
                        var aux2 = aux1[2].split('-');
                        var cpf = aux1[0] + aux1[1] + aux2[0] + aux2[1];
                        for (var t = 9; t < 11; t++) {
                            var sum, i;
                            for (sum = 0, i = 0; i < t; i++) {
                                sum += Number(cpf.substr(i,1)) * ((t + 1) - i);
                            }
                            sum = ((10 * sum) % 11) % 10;
                            if (Number(cpf.substr(t,1)) != sum) {
                                var mens = $('<span class="erro">Cpf inv&aacute;lido!</span>');
                            } else {
                                var mens = $('<span class="acerto">Ok</span>');
                            }
                        }
                    }
                    $('#est-cpf').html(mens);
                });
                //Validando numero
                $('#num').change(function(){
                    var er = new RegExp(/^[0-9]+$/);
                    if (!er.test($(this).val())) {
                        var mens = $('<span class="erro">Somente n&uacute;meros!</span>');
                    } else {
                        var mens = $('<span class="acerto">Ok</span>');
                    }
                    $('#est-num').html(mens);
                });
                //Validando complemento
                $('#comp').change(function(){
                    if ($(this).val().length > 10) {
                        var mens = $('<span class="erro">Camplemento inv&aacute;lido!</span>');
                    } else {
                        var mens = $('<span class="acerto">Ok</span>');
                    }
                    $('#est-comp').html(mens);
                });
                $('#cad').submit(function() {
                    $("#erros").hide();
                    status1 = isEmpty('nome');
                    status2 = isEmpty('email');
                    status3 = isEmpty('repemail');
                    status4 = isEmpty('senha');
                    status5 = isEmpty('repsenha');
                    status6 = isEmpty('telefone');
                    status7 = isEmpty('data');
                    status8 = isEmpty('cpf');
                    status9 = isEmpty('cep');
                    status10 = isEmpty('log');
                    status11 = isEmpty('num');
                    status12 = isEmpty('comp');
                    status13 = isEmpty('bairro');
                    status14 = isEmpty('cid');
                    if ($('input:text, input:password').children().hasClass('erro') || !status1 || !status2 ||
                        !status3 || !status4 || !status5 || !status6 || !status7 || !status8 || !status9 ||
                        !status10 || !status11 || !status12 || !status13 || !status14) {
                        var erro = "Para submeter o cadastro, corrija os erros destacados em vermelho";
                        $('#erros').fadeIn(2000).html(erro);
                        return false;
                    }
                }); 
            });
            function isEmpty(campo) {
                alert($('#'+campo).val())
                if ($('#'+campo).val() == "") {
                    var mens = $('<span class="erro">Campo em branco!</span>');
                    $('#est-'+campo).html(mens);
                    return false;
                }
                return true;
            }
            // ]]>
        </script>
        <style type="text/css">
            .erro {
                color: red;
            }
            .acerto {
                color: green;
            }
        </style>
    </head>
    <body>
        <div id="tudo">
            <div id="header">
                <h1>iSyzza - Sistema Gerenciador de Tele Entregas</h1>
            </div>
            <div id="conteudo">
                <form id="cad" action="main.do?action=cadcli" method="post">
                    <fieldset>
                        <legend>Cadastro</legend>
                        <div id="erros"></div>
                        <div class="campos">
                            <label for="nome" class="labels">Nome Completo:<br /></label>
                            <input type="text" name="nome" id="nome" value="" size="25" />
                            <div id="est-nome" class="estados">
                                <%
                                    if (erros.contains(1)) {
                                        out.print("<span class=\"erro\">Campo em branco!</span>");
                                    } else if (erros.contains(2)) {
                                        out.print("<span class=\"erro\">Nome deve ter pelo menos 8 caracteres!</span>");
                                    }
                                %>
                            </div>
                        </div>
                        <div class="campos">
                            <label for="email">Email:<br /></label>
                            <input type="text" id="email" name="email" value="" size="25" />
                            <div id="est-email" class="estados">
                                <%
                                    if (erros.contains(3)) {
                                        out.print("<span class=\"erro\">Campo em branco!</span>");
                                    } else if (erros.contains(4)) {
                                        out.print("<span class=\"erro\">Email inv&aacute;!</span>");
                                    } else if (erros.contains(5)) {
                                        out.print("<span class=\"erro\">Email j&aacute; em uso!</span>");
                                    }
                                %>
                            </div>
                        </div>
                        <div class="campos">
                            <label for="repemail">Repetir Email:<br /></label>
                            <input type="text" id="repemail" name="repemail" value="" size="25" />
                            <div id="est-repemail" class="estados">
                                <%
                                    if (erros.contains(6)) {
                                        out.print("<span class=\"erro\">Campo em branco!</span>");
                                    } else if (erros.contains(7)) {
                                        out.print("<span class=\"erro\">Emails n&atilde; coincidem!</span>");
                                    }
                                %>
                            </div>
                        </div>
                        <div class="campos">
                            <label for="senha">Senha:<br /></label>
                            <input type="password" id="senha" name="senha" size="15"/>
                            <div id="est-senha" class="estados">
                                <%
                                    if (erros.contains(8)) {
                                        out.print("<span class=\"erro\">Campo senha em branco!</span>");
                                    } else if (erros.contains(9)) {
                                        out.print("<span class=\"erro\">Senha deve ter entre 8 e 25 caracteres!</span>");
                                    }
                                %>
                            </div>
                        </div>
                        <div class="campos">
                            <label for="repsenha">Repetir Senha:<br /></label>
                            <input type="password" id="repsenha" name="repsenha" size="15" />
                            <div id="est-repsenha" class="estados">
                                <%
                                    if (erros.contains(10)) {
                                        out.print("<span class=\"erro\">Campo em branco!</span>");
                                    } else if (erros.contains(11)) {
                                        out.print("<span class=\"erro\">Senhas n&atilde; coincidem!</span>");
                                    }
                                %>
                            </div>
                        </div>
                        <div class="campos">
                            <label for="telefone">Telefone:<br /></label>
                            <input type="text" id="telefone" name="telefone" />
                            <div id="est-telefone" class="estados">
                                <%
                                    if (erros.contains(12)) {
                                        out.print("<span class=\"erro\">Campo em branco!</span>");
                                    } else if (erros.contains(13)) {
                                        out.print("<span class=\"erro\">Telefone inv&aacute;lido!</span>");
                                    }
                                %>
                            </div>
                        </div>
                        <div class="campos">
                            <label for="data">Data de Nascimento:<br /></label>
                            <input type="text" id="data" name="data" />
                            <div id="est-data" class="estados">
                                <%
                                    if (erros.contains(14)) {
                                        out.print("<span class=\"erro\">Campo em branco!</span>");
                                    } else if (erros.contains(15)) {
                                        out.print("<span class=\"erro\">Data inv&aacute;lida!</span>");
                                    } else if (erros.contains(16)) {
                                        out.print("<span class=\"erro\">Proibido para menores de 16 anos!</span>");
                                    }
                                %>
                            </div>
                        </div>
                        <div class="campos">
                            <label for="cpf">Cpf:<br /></label>
                            <input type="text" id="cpf" name="cpf" value="" size="15" />
                            <div id="est-cpf" class="estados">
                                <%
                                    if (erros.contains(17)) {
                                        out.print("<span class=\"erro\">Campo em branco!</span>");
                                    } else if (erros.contains(18)) {
                                        out.print("<span class=\"erro\">Cpf inv&aacute;lido!</span>");
                                    } else if(erros.contains(19)) {
                                        out.print("<span class=\"erro\">Cpf j&aacute; em uso!</span>");
                                    }
                                %>
                            </div>    
                        </div>
                        <div class="campos">
                            <label for="cep">Cep:<br /></label>
                            <input type="text" id="cep" name="cep" value="" size="15" />
                            <div id="est-cep" class="estados">
                                <%
                                    if (erros.contains(20)) {
                                        out.print("<span class=\"erro\">Campo em branco!</span>");
                                    } else if (erros.contains(21)) {
                                        out.print("<span class=\"erro\">Cep inv&aacute;lido!</span>");
                                    }
                                %>
                            </div>
                        </div>
                        <div class="campos">
                            <label for="log">Logradouro<br /></label>
                            <input type="text" id="log" name="log" value="" size="30" />
                            <div id="est-log" class="estados">
                                <%
                                    if (erros.contains(22)) {
                                        out.print("<span class=\"erro\">Campo em branco!</span>");
                                    }
                                %>
                            </div>
                        </div>
                        <div class="campos">
                            <label for="num">Nº:<br /></label>
                            <input type="text" id="num" name="num" value="" size="5" />
                            <div id="est-num" class="estados">
                                <%
                                    if (erros.contains(23)) {
                                        out.print("<span class=\"erro\">Campo em branco!</span>");
                                    } else if(erros.contains(24)) {
                                        out.print("<span class=\"erro\">N&uacute;mero inv&aacute;lido!</span>");
                                    }
                                %>
                            </div>
                        </div>
                        <div class="campos">
                            <label for="comp">Complemento:<br /></label>
                            <input type="text" id="comp" name="comp" value="" size="5" />
                            <div id="est-comp" class="estados">
                                <%
                                    if (erros.contains(25)) {
                                        out.print("<span class=\"erro\">Campo em branco!</span>");
                                    }
                                %>
                            </div>
                        </div>
                        <div class="campos">
                            <label for="bairro">Bairro:<br /></label>
                            <input type="text" id="bairro" name="bairro" value="" size="20" />
                            <div id="est-bairro" class="estados">

                            </div>
                        </div>
                        <div class="campos">
                            <label for="cid">Cidade:<br /></label>
                            <input type="text" id="cid" name="cid" value="" size="20" />
                            <div id="est-cid" class="estados">

                            </div>
                        </div>
                        <div class="campos">
                            <input type="hidden" name="cmd" value="cadclip" id="action" />
                            <input type="submit" value="Enviar" id="enviar" />
                            <input type="reset" value="Limpar" id="limpar" />
                        </div>
                    </fieldset>
                </form>
            </div>
            <div id="footer">
                <span>iSyzza @Copyright 2011 - Todos os direitos reservados</span>
            </div>
        </div>
    </body>
</html>
