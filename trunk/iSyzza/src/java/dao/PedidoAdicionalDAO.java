/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBConnection;
import entity.PedidoAdicional;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import util.PropertiesManager;

/**
 *
 * @author Jonathan
 */
public class PedidoAdicionalDAO {
    
    private static HashMap dados;

    static {
        try {
            dados = new PropertiesManager("sql.properties").readPropertiesFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public static void addPedidoAdicional(PedidoAdicional pa) {
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("Insert.PedidoAdicional");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, pa.getPedido().getId());
            pstmt.setInt(2, pa.getAdicional().getId());
            pstmt.setInt(3, pa.getQtd());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: " + ex.getMessage());
        }
    }
    
    public static String loadSQL(String key) {
        String sql = null;
        sql = (String) dados.get(key);
        return sql;
    }
    
}
