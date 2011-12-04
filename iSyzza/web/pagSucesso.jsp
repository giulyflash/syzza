<%-- 
    Document   : index
    Created on : 08/09/2011, 15:13:02
    Author     : Jonathan e Gabriel
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>

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
                $('#formlogin').submit(function(){
                    $('#erros').hide();
                    var ok = true;
                    if ($('#email').val() == "") {
                        ok = false;
                        var mens = $('<span class=\"erro\">Campo em branco!</span>');
                        $('#est-email').html(mens);
                    }
                    if ($('#senha').val() == "") {
                        ok = false;
                        var mens = $('<span class=\"erro\">Campo em branco!</span>');
                        $('#est-senha').html(mens);
                    }
                    if (!ok) {
                        var mens = 'Para fazer login, corrija os erros destacados em vermelho!';
                        $('#erros').html(mens).fadeIn(2000);
                        return false;
                    }
                });
        });

        </script>
    </head>
    <body>
        <div id="tudo">
            <div id="header">
                <h1>iSyzza - Sistema Gerenciador de Tele Entregas</h1>
            </div>
            <div id="conteudo">
                <h3>Pagamento efetuado com sucesso!</h3>
                <p><a href="main.do?action=homec">Clique aqui</a> para voltar para a Home.</p>
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
