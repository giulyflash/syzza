/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.PedidoAdicionalDAO;
import dao.PedidoDAO;
import dao.PizzaDAO;
import dao.PizzaSaborDAO;
import entity.Pedido;
import entity.PedidoAdicional;
import entity.Pizza;
import entity.PizzaSabor;
import entity.Sabor;
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
        System.out.println("chegou aki!");
        HttpSession session = getRequest().getSession();
        Pedido pedido = (Pedido)session.getAttribute("pedido");
        ArrayList<PedidoAdicional> petiscos = (ArrayList<PedidoAdicional>)session.getAttribute("petiscosPed");
        ArrayList<PedidoAdicional> sobremesas = (ArrayList<PedidoAdicional>)session.getAttribute("sobremesasPed");
        ArrayList<PedidoAdicional> bebidas = (ArrayList<PedidoAdicional>)session.getAttribute("bebidasPed");
        ArrayList<Pizza> pizzas = (ArrayList<Pizza>)session.getAttribute("pizzas");
        Date data_pedido = new Date(System.currentTimeMillis());
        pedido.setData_pedido(data_pedido);
        PedidoDAO.addPedido(pedido);
        pedido = PedidoDAO.getPedidoByNova();
        for (PedidoAdicional petisco : petiscos) {
            System.out.println("entrou em petiscos");
            petisco.setPedido(pedido);
            PedidoAdicionalDAO.addPedidoAdicional(petisco);
        }
        for (PedidoAdicional sobremesa : sobremesas) {
            System.out.println("entrou em sobremesas");
            sobremesa.setPedido(pedido);
            PedidoAdicionalDAO.addPedidoAdicional(sobremesa);
        }
        for (PedidoAdicional bebida : bebidas) {
            System.out.println("entrou em bebidas");
            bebida.setPedido(pedido);
            PedidoAdicionalDAO.addPedidoAdicional(bebida);
        }
        for (Pizza pizza : pizzas) {
            System.out.println("entrou em pizzas");
            pizza.setPedido(pedido);
            PizzaDAO.addPizza(pizza);
            Pizza pizza2 = PizzaDAO.getPizzaByNova();
            for (Sabor sabor : pizza.getSabores()) {
                System.out.println("pelo menos veio aki");
                PizzaSabor ps = new PizzaSabor();
                ps.setPizza(pizza2);
                ps.setSabor(sabor);
                PizzaSaborDAO.addPizzaSabor(ps);
            }
            PizzaDAO.setNovaPizza(pizza2);
        }
        PedidoDAO.setNovaPedido(pedido);
        session.removeAttribute("pedido");
        session.removeAttribute("petiscosPed");
        session.removeAttribute("sobremesasPed");
        session.removeAttribute("bebidasPed");
        session.removeAttribute("pizzas");
        getResponse().sendRedirect("main.do?action=pedidosuc");
    }
    
}
