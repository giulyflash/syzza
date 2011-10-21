/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.ClienteDAO;

/**
 *
 * @author inf4m
 */
public class LoginProcessor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Cliente cliente = ClienteDAO.pesquisarCliente(email);
        RequestDispatcher view;
        if (cliente == null) {
            request.setAttribute("status", 1);
            view = request.getRequestDispatcher("index.jsp");
        } else {
            request.setAttribute("cliente", cliente);
            view = request.getRequestDispatcher("home.jsp");
        }
        view.forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
