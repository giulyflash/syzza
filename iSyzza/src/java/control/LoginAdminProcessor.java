/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.AdminDAO;
import entity.Admin;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jonathan
 */
public class LoginAdminProcessor extends Processor {

    @Override
    public void execute() throws ServletException, IOException {
        System.out.println(" TA AKI CARALHO");
        getRequest().setCharacterEncoding("UTF-8");
        String email = getRequest().getParameter("email");
        String senha = getRequest().getParameter("senha");
        Admin admin = AdminDAO.getAdminByEmail(email);
        System.out.println(admin.toString());

        if (admin != null) {
            if (admin.getSenha().equals(md5((senha + admin.getSalt()) + admin.getSalt()))) {
                HttpSession session = getRequest().getSession();
                session.setAttribute("admin", admin);
                switch (admin.getNivel()) {
                    case 1:
                        getResponse().sendRedirect("main.do?action=homeAdmin");
                        break;
                    case 2:
                        getResponse().sendRedirect("main.do?action=homeCozinha");
                        break;
                    case 3:
                        getResponse().sendRedirect("main.do?action=homeEntrega");
                        break;
                }
            } else {
                getRequest().setAttribute("status", 1);
        forward("loginAdm.jsp");
            }
        } else {
            getRequest().setAttribute("status", 1);
        forward("loginAdm.jsp");
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
