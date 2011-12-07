<%-- 
    Document   : autenticaAdm
    Created on : 07/12/2011, 07:56:39
    Author     : Jonathan
--%>

<%@page import="entity.Admin"%>
<%
    session = request.getSession();
    if ((Admin)session.getAttribute("admin") == null) {
        response.sendRedirect("main.do?action=loginAdm");
    }
    
%>
