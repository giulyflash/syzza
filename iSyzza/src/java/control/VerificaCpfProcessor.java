/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.ClienteDAO;
import entity.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import util.Validacao;

/**
 *
 * @author Jonathan
 */
public class VerificaCpfProcessor extends Processor {

    @Override
    public void execute() throws ServletException, IOException {
        String cpf = getRequest().getParameter("cpf");
        PrintWriter out = getResponse().getWriter();
        try {
            if (!Validacao.checkCpf(cpf)) {
                out.println("2");
            } 
            else {
                cpf = cpf.substring(0, 3)+cpf.substring(4, 7)+cpf.substring(8, 11)+cpf.substring(12);
                Cliente cliente = ClienteDAO.getClienteByCpf(cpf);
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
