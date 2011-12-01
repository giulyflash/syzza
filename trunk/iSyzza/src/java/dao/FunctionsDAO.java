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
public class FunctionsDAO {
    
    public static int f_pedido_adicional_qtd() throws SQLException, ClassNotFoundException, IOException {
        Connection conexao = DBConnection.getInstance();
        CallableStatement clst = conexao.prepareCall("{? = call f_pedido_adicional_qtd(?)}");
        clst.registerOutParameter(1, Types.INTEGER);
        clst.setInt(2, 3);
        clst.execute();        
        return clst.getInt(1);
    }
    
    public static int f_pedido_adicional_report() throws SQLException, ClassNotFoundException, IOException {
        Connection conexao = DBConnection.getInstance();
        CallableStatement clst = conexao.prepareCall("{? = call f_pedido_adicional_report(?)}");
        clst.registerOutParameter(1, Types.INTEGER);
        clst.setInt(2, 1);
        clst.execute();        
        return clst.getInt(1);
    }
    
    public static void main(String[] args) {
        try {
            FunctionsDAO.f_pedido_adicional_qtd();
        } catch (SQLException ex) {
            Logger.getLogger(FunctionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FunctionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FunctionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            FunctionsDAO.f_pedido_adicional_report();
        } catch (SQLException ex) {
            Logger.getLogger(FunctionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FunctionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FunctionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
