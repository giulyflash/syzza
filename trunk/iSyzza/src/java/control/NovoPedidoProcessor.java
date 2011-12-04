/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.BordaDAO;
import dao.SaborDAO;
import dao.TamanhoDAO;
import entity.Borda;
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
        getRequest().setCharacterEncoding("utf-8");
        Pedido pedido = new Pedido();
        pedido.setId(1);
        session.setAttribute("pedido", pedido);
        
        ArrayList<Sabor> sabores = SaborDAO.getSabores();
        getRequest().setAttribute("sabores", sabores);
        
        ArrayList<Tamanho> tamanhos = TamanhoDAO.getTamanhos();
        getRequest().setAttribute("tamanhos", tamanhos);
        
        ArrayList<Borda> bordas = BordaDAO.getBordas();
        getRequest().setAttribute("bordas", bordas);
        
        forward("newpedido.jsp");
    }
    
}
