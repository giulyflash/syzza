/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.ClienteDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Cliente;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jonathan
 */
public class LoginClienteProcessor extends Processor {
    
    @Override
    public void execute() throws ServletException, IOException {
        System.out.println("Pagina q fez a requisição: "+getRequest().getRequestURI());
        System.out.println(getRequest().getParameter("action"));
        getRequest().setCharacterEncoding("UTF-8");
        String email = getRequest().getParameter("email");
        String senha = getRequest().getParameter("senha");
        Cliente cliente = ClienteDAO.pesquisarCliente(email);
        // "compila"a expressão que será usada  
        Pattern p = Pattern.compile("/^[A-Za-z0-9_\\.-]{6,30}@[A-Za-z0-9]{2,30}(\\.[A-Za-z]{2,6})+$/");   
        // "pega" informações da strring que vc quer testar  
        Matcher m = p.matcher(email);
  
        // confere para ver se a senha passada bate exatamente com o padrão 
        if ( m.matches() ) {  
            System.out.println("Email ok");  
        } else {  
            System.out.println("Email não está ok");  
        }  
        
        
        if (cliente != null) {
            if (cliente.getSenha().equals(md5((senha + cliente.getSalt()) + cliente.getSalt()))) {      
                HttpSession session = getRequest().getSession(true);
                session.setAttribute("cliente", cliente);
                getResponse().sendRedirect("home.jsp");
            }
        }
        RequestDispatcher view;
        getRequest().setAttribute("status", 1);
        view = getRequest().getRequestDispatcher("logincli.jsp");
        view.forward(getRequest(), getResponse());
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
