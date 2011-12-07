/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.ClienteDAO;
import entity.Cliente;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import org.apache.commons.lang.RandomStringUtils;
import util.MailSender;

/**
 *
 * @author 0445100
 */
public class RecuperaSenhaProcessor extends Processor {

    @Override
    public void execute() throws ServletException, IOException {
        
       
        String email = getRequest().getParameter("email");
        System.out.println(email);
        String newpass = RandomStringUtils.randomAlphanumeric(8);
        Cliente cliente = ClienteDAO.getClienteByEmail(email);
        cliente.setSenha(newpass);
        ClienteDAO.setClientePessoais(cliente);
        try {
            MailSender.sendMail(newpass, email);
        } catch (MessagingException ex) {
            Logger.getLogger(RecuperaSenhaProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        forward("emailSucess.jsp");
    }
    
}
