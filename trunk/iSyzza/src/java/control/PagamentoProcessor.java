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
import javax.servlet.http.HttpSession;

/**
 *
 * @author 0669105
 */
public class PagamentoProcessor extends Processor {

    @Override
    public void execute() throws ServletException, IOException {
        String idPedido = getRequest().getParameter("id_pedido");
        Pedido pedido = PedidoDAO.getPedidoById(Integer.parseInt(idPedido));
        pedido.setData_pag(new Date(System.currentTimeMillis()));
        PedidoDAO.setPagamentoPedido(pedido);
        forward("pagSucesso.jsp");
    }
    
}
