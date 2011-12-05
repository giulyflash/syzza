/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.BordaDAO;
import dao.SaborDAO;
import dao.TamanhoDAO;
import entity.Borda;
import entity.Pizza;
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
public class AdicionaPizzaProcessor extends Processor {

    @Override
    public void execute() throws ServletException, IOException {
        HttpSession session = getRequest().getSession();
        ArrayList<Pizza> pizzas = (ArrayList<Pizza>)session.getAttribute("pizzas");
        if (pizzas == null) {
            System.out.println("ainda nao existia session pizzas");
            pizzas = new ArrayList<Pizza>();
        }
        Pizza pizza = new Pizza();
        pizza.setSabores(new ArrayList<Sabor>());
        Tamanho tamanho = TamanhoDAO.getTamanhoById(Integer.parseInt(getRequest().getParameter("tamanho")));
        pizza.setTamanho(tamanho);
        Borda borda = BordaDAO.getBordaById(Integer.parseInt(getRequest().getParameter("borda")));
        pizza.setBorda(borda);
        for (int i=1;i<=4;i++) {
            Sabor sabor = SaborDAO.getSaborById(Integer.parseInt(getRequest().getParameter("sabor"+i)));
            pizza.getSabores().add(sabor);
        }
        pizzas.add(pizza);
        session.setAttribute("pizzas", pizzas);
        ArrayList<Pizza> pizz = (ArrayList<Pizza>)session.getAttribute("pizzas");
        System.out.println(pizz.get(0).toString());
    }
    
}
