/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Cliente;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jonathan
 */
public class UpdateClienteProcessor extends Processor{

    @Override
    public void execute() throws ServletException, IOException {
        HttpSession session = getRequest().getSession();
        Cliente cliente = (Cliente)session.getAttribute("cliente");
        cliente.setNome(getRequest().getParameter("nome"));
        cliente.setTelefone(getRequest().getParameter("telefone"));
        DateFormat df1 = DateFormat.getDateInstance();
        try {
            cliente.setData_nasc(df1.parse(getRequest().getParameter("cpf")));
        } catch (ParseException ex) {
            Logger.getLogger(UpdateClienteProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        String log = getRequest().getParameter("log");
        String bairro = getRequest().getParameter("bairro");
        String cid = getRequest().getParameter("cid");
        String cep = getRequest().getParameter("cep");
        String num = getRequest().getParameter("num");
        String comp = getRequest().getParameter("comp");
        String endereco = "";
        if (comp == null || comp.equals("")) {
            endereco = log+"|"+bairro+"|"+cid+"|"+cep+"|"+num;
        } else {
            endereco = log+"|"+bairro+"|"+cid+"|"+cep+"|"+num+"|"+comp;
        }
        cliente.setEndereco(endereco);
        
    }
    
}
