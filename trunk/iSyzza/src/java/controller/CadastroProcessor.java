/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
public class CadastroProcessor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String repemail = request.getParameter("repemail");
        String senha = request.getParameter("senha");
        String repsenha = request.getParameter("repsenha");
        String telefone = request.getParameter("telefone");
        String endereco = request.getParameter("endereco");
        Date data = new Date(System.currentTimeMillis());
        ArrayList erros = new ArrayList();
        //Validações do campo nome
        if (nome == null || nome.equals("")) {
            erros.add(1);
        }
        else if (nome.length() < 8) {
            erros.add(2);
        }
        
        //Validação de email
        if (email == null || email.equals("")) {
            erros.add(3);
        }
        if (!email.equals(repemail)) {
            erros.add(4);
        }
        
        //Validação de senha
        if (senha == null || senha.equals("")) {
            erros.add(5);
        }
        if (!senha.equals(repsenha)) {
            erros.add(6);
        }
        
        //Valicação de telefone
        if (telefone == null || telefone.equals("")) {
            erros.add(7);
        }
                
        //Validação de endereco
        if (endereco == null || endereco.equals("")) {
            erros.add(8);
        }
        
        if (!erros.isEmpty()) {
            RequestDispatcher view;
            request.setAttribute("erros", erros);
            view = request.getRequestDispatcher("cadastro.jsp");
            view.forward(request, response);
        }
        else {
            Cliente cliente = new Cliente(nome, email, senha, telefone, endereco, data);
            ClienteDAO.inserirCliente(cliente);
        }
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
