/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.AdicionalDAO;
import entity.Adicional;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jonathan
 */
public class AdicionaPetiscoProcessor extends Processor{

    @Override
    public void execute() throws ServletException, IOException {
        HttpSession session = getRequest().getSession();
        ArrayList<Adicional> petiscos = (ArrayList<Adicional>)session.getAttribute("petiscosPed");
        if (petiscos == null) {
            System.out.println("ainda nao existia session petiscos");
            petiscos = new ArrayList<Adicional>();
        }
        Adicional adicional = AdicionalDAO.getAdicionalById(Integer.parseInt(getRequest().getParameter("petisco")));
        petiscos.add(adicional);
        session.setAttribute("petiscosPed", petiscos);
    }
    
}
