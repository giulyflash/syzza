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
    
    public static int f_pedido_adicional_qtd(int id_pedido) throws SQLException, ClassNotFoundException, IOException {
        Connection conexao = DBConnection.getInstance();
        CallableStatement clst = conexao.prepareCall("{? = call f_pedido_adicional_qtd(?)}");
        clst.registerOutParameter(1, Types.INTEGER);
        clst.setInt(2, id_pedido);
        clst.execute();   
        int total = clst.getInt(1);
        clst.close();
        return total;
    }
    
    public static int f_pedido_adicional_report(int id_adicional) throws SQLException, ClassNotFoundException, IOException {
        Connection conexao = DBConnection.getInstance();
        CallableStatement clst = conexao.prepareCall("{? = call f_pedido_adicional_report(?)}");
        clst.registerOutParameter(1, Types.INTEGER);
        clst.setInt(2, id_adicional);
        clst.execute();
        int total = clst.getInt(1);
        clst.close();
        return total;
    }
    
    public static void main(String[] args) {
        try {
            System.out.println("Quantidade de adicional no pedido: "+FunctionsDAO.f_pedido_adicional_qtd(6));
        } catch (SQLException ex) {
            Logger.getLogger(FunctionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FunctionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FunctionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println("Quantidade de pedidos que pediram o adicional: "+FunctionsDAO.f_pedido_adicional_report(6));
        } catch (SQLException ex) {
            Logger.getLogger(FunctionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FunctionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FunctionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
