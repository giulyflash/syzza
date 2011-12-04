<%-- 
    Document   : pedidoenc
    Created on : 04/12/2011, 19:51:08
    Author     : Jonathan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="include/js/jquery-1.7.min.js"> </script>
        <script type="text/javascript">
            $(document).ready(function(){
                document.formenc.submit();
            });
            
        </script>
    </head>
    <body>
        <form name="formenc" id="formenc" action="main.do?action=newpedido" method="POST">
            <input type="hidden" name="cmd" value="newpedidop" id="teste" />
        </form>
    </body>
</html>
