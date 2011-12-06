/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.PedidoAdicional;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 0669105
 */
public class CarregaSobremesaProcessor extends Processor{
    
        @Override
    public void execute() throws ServletException, IOException {
        PrintWriter out = getResponse().getWriter();
        HttpSession session = getRequest().getSession();
        ArrayList<PedidoAdicional> petiscos = (ArrayList<PedidoAdicional>)session.getAttribute("sobremesasPed");
        if (petiscos == null) {
            petiscos = new ArrayList<PedidoAdicional>();
        } else {
            System.out.println("Numeo de elem na lista: "+petiscos.size());
        }    
        int i=0;
        for (PedidoAdicional petisco : petiscos) {
            i++;
            System.out.println(i);
            out.print("<b>Sobremesa "+i+": </b><br />");
            out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nome: "+petisco.getAdicional().getNome()+"<br />");
            out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pre&ccedil;o: "+petisco.getAdicional().getPreco()+"<br />");
            out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Quantidade: "+petisco.getQtd()+"<br />");
        }
        if (petiscos.isEmpty()) {
            out.print("<p>Nenhuma sobremesa.</p>");
        }
    }
    
}
