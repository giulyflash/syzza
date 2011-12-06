/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Adicional;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jonathan
 */
public class CarregaPetiscoProcessor extends Processor {

    @Override
    public void execute() throws ServletException, IOException {
        PrintWriter out = getResponse().getWriter();
        HttpSession session = getRequest().getSession();
        ArrayList<Adicional> petiscos = (ArrayList<Adicional>)session.getAttribute("petiscosPed");
        if (petiscos == null) {
            petiscos = new ArrayList<Adicional>();
        } else {
            System.out.println("Numeo de elem na lista: "+petiscos.size());
        }    
        int i=0;
        for (Adicional petisco : petiscos) {
            i++;
            System.out.println(i);
            out.print("<b>Petisco "+i+": </b><br />");
            out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nome: "+petisco.getNome()+"<br />");
            out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pre&ccedil;o: "+petisco.getPreco()+"<br />");
        }
        if (petiscos.isEmpty()) {
            out.print("<p>Nenhum petisco.</p>");
        }
    }
    
}
