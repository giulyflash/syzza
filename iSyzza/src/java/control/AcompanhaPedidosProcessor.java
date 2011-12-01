/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.PedidoDAO;
import entity.Cliente;
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
public class AcompanhaPedidosProcessor extends Processor {

    @Override
    public void execute() throws ServletException, IOException {
        System.out.println(getRequest().getParameter("cmd"));
        PrintWriter out = getResponse().getWriter();
        HttpSession session = getRequest().getSession();
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        System.out.println("Id do cliente: "+cliente.getId());
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        pedidos = PedidoDAO.getPedidosByCliente(cliente);
        if (!pedidos.isEmpty()) {
            System.out.println("entrou aki");
            out.print("<table>");
            out.print("<tr>");
            out.print("<th>Cod</th>");
            out.print("<th>&Uacute;ltima Atualiza&ccedil;&atilde;o</th>");
            out.print("<th>Status</th>");
            out.print("<th></th>");
            out.print("</tr>");
        }
        for (Pedido pedido : pedidos) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String data;
            System.out.println("Status do pago: "+pedido.getPago());
            out.print("<tr>");
            out.print("<td>"+pedido.getId()+"</td>");
            if (pedido.getPago() == 0) {
                data = formato.format(pedido.getData_pedido());
                out.print("<td>"+data+"</td>");
                out.print("<td>"+"<span class=\"atencao\">"
                        + "<form method=\"post\" action=\"main.do?action=homec\">"
                        + "<input type=\"hidden\" name=\"id_pedido\" value=\""+pedido.getId()+"\">"
                        + "<input type=\"hidden\" name=\"cmd\" value=\"pagp\" />"
                        + "<input type=\"submit\" value=\"Fazer Pagamento\" />"
                        + "</form></td>");
            } else if (pedido.getData_pronta() == null) {
                data = formato.format(pedido.getData_pag());
                out.print("<td>"+data+"</td>");
                out.print("<td>"+"<span class=\"atencao\">Na cozinha"+"</td>");
            } else if (pedido.getData_entrega() == null) {
                data = formato.format(pedido.getData_pronta());
                out.print("<td>"+data+"</td>");
                out.print("<td>"+"<span class=\"atencao\">Ao destino"+"</td>");
            } else {
                data = formato.format(pedido.getData_entrega());
                out.print("<td>"+data+"</td>");
                out.print("<td>"+"<span class=\"atencao\">Pedido entregue</span>"+"</td>");
            }
            out.print("</tr>");
        }
        if (!pedidos.isEmpty()) {
            out.print("</table>");
        }
    } 
}
