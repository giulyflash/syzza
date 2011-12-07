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

/**
 *
 * @author Jonathan
 */
public class AdicionaFuncionarioProcessor extends Processor {

    @Override
    public void execute() throws ServletException, IOException {
        String nome = getRequest().getParameter("nome");
        String email = getRequest().getParameter("email");
        String senha = getRequest().getParameter("senha");
        int nivel = Integer.parseInt(getRequest().getParameter("nivel"));
        int salt = (int) (1 + Math.random() * Integer.MAX_VALUE);
        senha = md5((senha + salt) + salt);
        Admin admin = new Admin();
        admin.setNome(nome);
        admin.setEmail(email);
        admin.setSenha(senha);
        admin.setSalt(salt);
        admin.setNivel(nivel);
        AdminDAO.addAdmin(admin);
    }
    
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
