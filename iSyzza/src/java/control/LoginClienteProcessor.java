/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.ClienteDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import entity.Cliente;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jonathan
 */
public class LoginClienteProcessor extends Processor {
    
    @Override
    public void execute() throws ServletException, IOException {       
        getRequest().setCharacterEncoding("UTF-8");
        String email = getRequest().getParameter("email");
        String senha = getRequest().getParameter("senha");
        Cliente cliente = ClienteDAO.getClienteByEmail(email);        
        
        if (cliente != null) {
            if (cliente.getSenha().equals(md5((senha + cliente.getSalt()) + cliente.getSalt()))) {      
                HttpSession session = getRequest().getSession(true);
                session.setAttribute("cliente", cliente);
                getResponse().sendRedirect("main.do?action=homec");
            }
        } else {
            getRequest().setAttribute("status", 1);
            forward("logincli.jsp");
        }
        
    }
    
    //Função para criar hash da senha informada  
    public static String md5(String senha) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
            sen = hash.toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CadastroClienteProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sen;
    }

}
