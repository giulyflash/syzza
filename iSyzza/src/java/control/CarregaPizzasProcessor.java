/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Pedido;
import entity.Pizza;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jonathan
 */
public class CarregaPizzasProcessor extends Processor{

    @Override
    public void execute() throws ServletException, IOException {
        PrintWriter out = getResponse().getWriter();
        HttpSession session = getRequest().getSession();
        Pedido pedido = (Pedido)session.getAttribute("pedido");
        ArrayList<Pizza> pizzas = (ArrayList<Pizza>)session.getAttribute("pizzas");
        int i=0;
        for (Pizza pizza : pizzas) {
            i++;
            out.print("<b>Pizza"+ i+"</b><br />");
            out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tamanho: "+pizza.getTamanho()+"<br />");
            out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sabores: ");
            out.print(pizza.getSabores().get(0).getNome()+", "+
                    pizza.getSabores().get(1).getNome()+", "+
                    pizza.getSabores().get(2).getNome()+", "+
                    pizza.getSabores().get(3).getNome()+"<br />");
            out.print("Borda: "+pizza.getBorda().getNome()+"<br /><br />");
        }
        if (pizzas.isEmpty()) {
            out.print("<p>Nenhuma pizza.</p>");
        }
    }
    
}
