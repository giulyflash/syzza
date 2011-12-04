<%-- 
    Document   : autentica
    Created on : 03/12/2011, 18:07:42
    Author     : Jonathan
--%>

<%@page import="entity.Cliente"%>
<%
    session = request.getSession();
    if ((Cliente)session.getAttribute("cliente") == null) {
        response.sendRedirect("index.jsp");
    }
    
%>
