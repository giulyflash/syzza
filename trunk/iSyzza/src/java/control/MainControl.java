/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import database.DBConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String cmd = request.getParameter("action");
        System.out.println("Action: " + cmd);
        String actionClass = (String)actions.get(cmd);
        try {
            //Cria a instância da classe utilizando introspecção
            Processor action = (Processor) Class.forName(actionClass).newInstance();
            action.setRequest(request);
            action.setResponse(response);
            action.setContext(getServletContext());
            action.execute();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            DBConnection.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
