/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBConnection;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 0669105
 */
public class ProceduresDAO {
    
    public static void f_pedido_adicional_qtd(String adicional, int id_pedido, int qtd) throws SQLException, ClassNotFoundException, IOException {
        Connection conexao = DBConnection.getInstance();
        CallableStatement clst = conexao.prepareCall("{call p_add_adicional(?, ?, ?)}");
        clst.setString(1, adicional);
        clst.setInt(2, id_pedido);
        clst.setInt(3, qtd);
        clst.execute();
        
        clst.close();
    }
    
    public static int pedido_total(int id_pedido) throws SQLException, ClassNotFoundException, IOException {
        Connection conexao = DBConnection.getInstance();
        CallableStatement clst = conexao.prepareCall("{call pedido_total(?, ?)}");
        clst.registerOutParameter(2, Types.INTEGER);
        clst.setInt(1, id_pedido);
        clst.execute();
        int total = clst.getInt(2);
        clst.close();
        return total;
    }
    
    public static void main(String[] args) {
        
        try {
            ProceduresDAO.f_pedido_adicional_qtd("sorvete", 6, 2);
        } catch (SQLException ex) {
            Logger.getLogger(ProceduresDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProceduresDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProceduresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            System.out.println("Total do pedido: "+ProceduresDAO.pedido_total(6));
        } catch (SQLException ex) {
            Logger.getLogger(ProceduresDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProceduresDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProceduresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
