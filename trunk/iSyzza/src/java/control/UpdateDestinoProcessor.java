/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Pedido;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jonathan
 */
public class UpdateDestinoProcessor extends Processor {

    @Override
    public void execute() throws ServletException, IOException {
        HttpSession session = getRequest().getSession();
        Pedido pedido = (Pedido)session.getAttribute("pedido");
        pedido.setTelefone(getRequest().getParameter("telefone"));
        
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
        pedido.setEndereco(endereco);
        session.setAttribute("pedido", pedido);
    }
    
}
