/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.PedidoAdicionalDAO;
import dao.PedidoDAO;
import entity.Pedido;
import entity.PedidoAdicional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jonathan
 */
public class ConfirmaPedidoProcessor extends Processor{

    @Override
    public void execute() throws ServletException, IOException {
        HttpSession session = getRequest().getSession();
        Pedido pedido = (Pedido)session.getAttribute("pedido");
        ArrayList<PedidoAdicional> petiscos = (ArrayList<PedidoAdicional>)session.getAttribute("petiscosPed");
        ArrayList<PedidoAdicional> sobremesas = (ArrayList<PedidoAdicional>)session.getAttribute("sobremesasPed");
        ArrayList<PedidoAdicional> bebidas = (ArrayList<PedidoAdicional>)session.getAttribute("bebidasPed");
        Date data_pedido = new Date(System.currentTimeMillis());
        pedido.setData_pedido(data_pedido);
        PedidoDAO.addPedido(pedido);
        pedido = PedidoDAO.getPedidoByNova();
        for (PedidoAdicional petisco : petiscos) {
            petisco.setPedido(pedido);
            PedidoAdicionalDAO.addPedidoAdicional(petisco);
        }
        for (PedidoAdicional sobremesa : sobremesas) {
            sobremesa.setPedido(pedido);
            PedidoAdicionalDAO.addPedidoAdicional(sobremesa);
        }
        for (PedidoAdicional bebida : bebidas) {
            bebida.setPedido(pedido);
            PedidoAdicionalDAO.addPedidoAdicional(bebida);
        }
        session.removeAttribute("pedido");
        session.removeAttribute("petiscosPed");
        session.removeAttribute("sobremesasPed");
        session.removeAttribute("bebidasPed");
        getResponse().sendRedirect("main.do?action=homec");
    }
    
}
