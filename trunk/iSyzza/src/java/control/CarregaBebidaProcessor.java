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
 * @author Familia
 */
public class CarregaBebidaProcessor extends Processor{

    @Override
    public void execute() throws ServletException, IOException {
        PrintWriter out = getResponse().getWriter();
        HttpSession session = getRequest().getSession();
        ArrayList<PedidoAdicional> bebidas = (ArrayList<PedidoAdicional>)session.getAttribute("bebidasPed");
        if (bebidas == null) {
            bebidas = new ArrayList<PedidoAdicional>();
        } else {
            System.out.println("Numeo de elem na lista: "+bebidas.size());
        }    
        int i=0;
        for (PedidoAdicional bebida : bebidas) {
            i++;
            System.out.println(i);
            out.print("<b>Bebida "+i+": </b><br />");
            out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nome: "+bebida.getAdicional().getNome()+"<br />");
            out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pre&ccedil;o: "+bebida.getAdicional().getPreco()+"<br />");
            out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Quantidade: "+bebida.getQtd()+"<br /><br />");
        }
        if (bebidas.isEmpty()) {
            out.print("<p>Nenhuma bebida.</p>");
        }
    }
    
}
