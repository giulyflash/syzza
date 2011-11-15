/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.ClienteDAO;
import entity.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import util.Validacao;

/**
 *
 * @author Jonathan
 */
public class VerificaEmailProcessor extends Processor {

    @Override
    public void execute() throws UnsupportedEncodingException, IOException {
        getResponse().setContentType("text/html;charset=UTF-8");
        getRequest().setCharacterEncoding("UTF-8");
        String email = getRequest().getParameter("email");
        PrintWriter out = getResponse().getWriter();
        System.out.println("Email: " + email);
        try {
            if (!Validacao.checkEmail(email)) {
                out.println("2");
            } 
            else {
                Cliente cliente = ClienteDAO.pesquisarCliente(email);
                if (cliente != null) {
                    out.println("0");
                } else {
                    out.println("1");
                }
            }
        } finally {
            out.close();
        }
    }
}
