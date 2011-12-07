/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.PedidoDAO;
import entity.Pedido;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;

/**
 *
 * @author Jonathan
 */
public class PedidoProntoProcessor extends Processor {

    @Override
    public void execute() throws ServletException, IOException {
        String idPedido = getRequest().getParameter("id_pedido");
        Pedido pedido = PedidoDAO.getPedidoById(Integer.parseInt(idPedido));
        pedido.setData_pronta(new Date(System.currentTimeMillis()));
        PedidoDAO.setProntoPedido(pedido);
        forward("homeCozinha.jsp");
    }
    
}
