/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jonathan
 */
public class LogoutProcessor extends Processor {

    @Override
    public void execute() throws ServletException, IOException {
        HttpSession session = getRequest().getSession();
        session.invalidate();
        getResponse().sendRedirect("index.jsp");
    }
    
}
