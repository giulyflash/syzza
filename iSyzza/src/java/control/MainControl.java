/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import database.DBConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.PropertiesManager;

/**
 *
 * @author Jonathan
 */
public class MainControl extends HttpServlet {
    
    private HashMap actions;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        actions = new HashMap();
        try {
            actions = new PropertiesManager("actions.properties").readPropertiesFile();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String view  = (String)actions.get(action);
        if (view == null || action == null) {
            System.out.println("Fudeu");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher(view);
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String cmd = request.getParameter("cmd");
        String action = request.getParameter("action");
        System.out.println("Action" + action);
        System.out.println("Cmd: " + cmd);
        String actionClass = (String)actions.get(cmd);
        try {
            //Cria a instância da classe utilizando introspecção
            Processor proc = (Processor) Class.forName(actionClass).newInstance();
            proc.setRequest(request);
            proc.setResponse(response);
            proc.setContext(getServletContext());
            proc.execute();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
}
