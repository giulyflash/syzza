/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.AdminDAO;
import entity.Admin;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 *
 * @author Jonathan
 */
public class DeleteFuncionarioProcessor extends Processor {

    @Override
    public void execute() throws ServletException, IOException {
        Admin admin = AdminDAO.getAdminById(Integer.parseInt(getRequest().getParameter("id")));
        AdminDAO.deleteAdmin(admin);
    }
    
}
