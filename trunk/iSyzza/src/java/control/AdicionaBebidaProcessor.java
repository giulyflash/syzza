/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.AdicionalDAO;
import entity.Adicional;
import entity.Pedido;
import entity.PedidoAdicional;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Familia
 */
public class AdicionaBebidaProcessor extends Processor{

    @Override
    public void execute() throws ServletException, IOException {
        System.out.println("chegou aki");
        HttpSession session = getRequest().getSession();
        ArrayList<PedidoAdicional> petiscos = (ArrayList<PedidoAdicional>)session.getAttribute("bebidasPed");
        Pedido pedido = (Pedido)session.getAttribute("pedido");
        if (petiscos == null) {
            petiscos = new ArrayList<PedidoAdicional>();
        }
        System.out.println(getRequest().getParameter("bebida"));
        System.out.println(getRequest().getParameter("qtdBebida"));
        Adicional adicional = AdicionalDAO.getAdicionalById(Integer.parseInt(getRequest().getParameter("bebida")));
        int qtd = Integer.parseInt(getRequest().getParameter("qtdBebida"));
        PedidoAdicional pedidoAdicional = new PedidoAdicional();
        pedidoAdicional.setAdicional(adicional);
        pedidoAdicional.setPedido(pedido);
        pedidoAdicional.setQtd(qtd);
        petiscos.add(pedidoAdicional);
        session.setAttribute("bebidasPed", petiscos);
    }
    
}
