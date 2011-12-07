/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.PedidoDAO;
import entity.Pedido;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jonathan
 */
public class PedidoAbertoProcessor extends Processor {

    @Override
    public void execute() throws ServletException, IOException {
        System.out.println(getRequest().getParameter("cmd"));
        PrintWriter out = getResponse().getWriter();
        //HttpSession session = getRequest().getSession();
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        pedidos = PedidoDAO.getPedidosByAberto();
        if (!pedidos.isEmpty()) {
            System.out.println("entrou aki");
            out.print("<table>");
            out.print("<tr>");
            out.print("<th>Cod</th>");
            out.print("<th>Data Pedido</th>");
            out.print("<th></th>");
            out.print("</tr>");
        }
        for (Pedido pedido : pedidos) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String data;
            out.print("<tr>");
            out.print("<td>" + pedido.getId() + "</td>");
            data = formato.format(pedido.getData_pag());
            out.print("<td>" + data + "</td>");
            out.print("<td>"
                    + "<form method=\"post\" action=\"main.do?action=homeCozinha\">"
                    + "<input type=\"hidden\" name=\"id_pedido\" value=\"" + pedido.getId() + "\">"
                    + "<input type=\"hidden\" name=\"cmd\" value=\"prontap\" />"
                    + "<input type=\"submit\" value=\"Pronta\" />"
                    + "</form></td>");
        }
        out.print("</tr>");
        if (!pedidos.isEmpty()) {
            out.print("</table>");
        }
    }
}
