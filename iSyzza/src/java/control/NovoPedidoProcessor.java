/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.AdicionalDAO;
import dao.BordaDAO;
import dao.SaborDAO;
import dao.TamanhoDAO;
import entity.Adicional;
import entity.Borda;
import entity.Cliente;
import entity.Pedido;
import entity.Sabor;
import entity.Tamanho;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jonathan
 */
public class NovoPedidoProcessor extends Processor{

    @Override
    public void execute() throws ServletException, IOException {
        HttpSession session = getRequest().getSession();
        Cliente cliente = (Cliente)session.getAttribute("cliente");
        getRequest().setCharacterEncoding("utf-8");
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setId(1);
        pedido.setEndereco(cliente.getEndereco());
        pedido.setTelefone(cliente.getTelefone());
        session.setAttribute("pedido", pedido);
        
        ArrayList<Sabor> sabores = SaborDAO.getSabores();
        getRequest().setAttribute("sabores", sabores);
        
        ArrayList<Tamanho> tamanhos = TamanhoDAO.getTamanhos();
        getRequest().setAttribute("tamanhos", tamanhos);
        
        ArrayList<Borda> bordas = BordaDAO.getBordas();
        getRequest().setAttribute("bordas", bordas);
        
        ArrayList<Adicional> petiscos = AdicionalDAO.getAdicionaisByTipo(2);
        getRequest().setAttribute("petiscos", petiscos);
        
        ArrayList<Adicional> sobremesas = AdicionalDAO.getAdicionaisByTipo(3);
        getRequest().setAttribute("sobremesas", sobremesas);
        
        ArrayList<Adicional> bebidas = AdicionalDAO.getAdicionaisByTipo(1);
        getRequest().setAttribute("bebidas", bebidas);
        
        forward("newpedido.jsp");
    }
    
}
