/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.AdminDAO;
import entity.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;

/**
 *
 * @author Jonathan
 */
public class CarregaFuncionarioProcessor extends Processor{

    @Override
    public void execute() throws ServletException, IOException {
        System.out.println("chegou aki");
        PrintWriter out = getResponse().getWriter();
        ArrayList<Admin> funcs = new ArrayList<Admin>();
        funcs = AdminDAO.getAdmins();
        if (!funcs.isEmpty()) {
            out.print("<table border=1>");
            out.print("<tr>");
            out.print("<th>Id</th>");
            out.print("<th>Nome</th>");
            out.print("<th>Email</th>");
            out.print("<th>Senha</th>");
            out.print("<th>Nivel</th>");
            out.print("</tr>");
        }
        for (Admin admin : funcs) {
            System.out.println(admin.toString());
            out.print("<tr>");
            out.print("<td>"+admin.getId()+"</td>");
            out.print("<td>"+admin.getNome()+"</td>");
            out.print("<td>"+admin.getEmail()+"</td>");
            out.print("<td>"+admin.getSenha()+"</td>");
            out.print("<td>"+admin.getNivel()+"</td>");
            out.print("<td>"+"<input type=\"button\" id=\"excluir-"+admin.getId()+"\" value=\"Excluir\" class=\"excluir\" />"+"</td>");
            out.print("</tr>");
        }
        if (funcs.isEmpty()) {
            out.print("<span>Nenhum funcionario cadastrado!</span>");
        } else {
            out.print("</table>");
        }
        out.print("<input type=\"button\" id=\"ins\" value=\"Inserir\" />");
        out.print("<div id=\"form-ins\">"
                + "Nome: <input type=\"text\" id=\"nome\" value=\"\" /><br />"
                + "Email: <input type=\"text\" id=\"email\" value=\"\" /><br />"
                + "Senha: <input type=\"password\" id=\"senha\" value=\"\" /><br />"
                + "Nivel: <input type=\"text\" id=\"nivel\" value=\"\" /><br />"
                + "<input type=\"button\" id=\"enviar\" value=\"Enviar\" />"
                + "<input type=\"button\" id=\"canc\" value=\"Cancelar\" /><br />"
                + "</div>");
    }
    
}
