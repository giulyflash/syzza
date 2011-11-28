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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        pedidos = PedidoDAO.getPedidosById(cliente);
        System.out.println("chegou aki");
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
            SimpleDateFormat formato = new SimpleDateFormat("MMM dd/MM/yyyy HH:mm");
            String data;
            out.print("<tr>");
            out.print("<td>"+pedido.getId()+"</td>");
            if (pedido.getPago() == 0) {
                data = formato.format(pedido.getData_pedido());
                out.print("<td>"+data+"</td>");
                out.print("<td>"+"Fazer Pagamento"+"</td>");
            } else if (pedido.getData_pronta() == null) {
                data = formato.format(pedido.getData_pag());
                out.print("<td>"+data+"</td>");
                out.print("<td>"+"Na cozinha"+"</td>");
            } else if (pedido.getData_entrega() == null) {
                data = formato.format(pedido.getData_pronta());
                out.print("<td>"+data+"</td>");
                out.print("<td>"+"Ao destino"+"</td>");
            } else {
                data = formato.format(pedido.getData_entrega());
                out.print("<td>"+data+"</td>");
                out.print("<td>"+"Pedido entregue"+"</td>");
            }
            out.print("</tr>");
        }
        if (!pedidos.isEmpty()) {
            out.print("</table>");
        }
    } 
}
