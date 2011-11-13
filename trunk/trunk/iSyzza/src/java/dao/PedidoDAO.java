/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBConnection;
import entity.Pedido;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import util.PropertiesManager;

/**
 *
 * @author 0669105
 */
public class PedidoDAO {
    public static final int ID = 1;
    public static final int DATA_PEDIDO = 2;
    public static final int DATA_PRONTA = 3;
    public static final int DATA_ENTREGA = 4;
    public static final int CLIENTE = 5;
    private static HashMap dados;
    
    static {
        try {
            dados = new PropertiesManager("sql.properties").readPropertiesFile();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
    
    public static void inserir(Pedido pedido) {
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("Insert.Pedido");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
        } catch(SQLException ex) {
            
        } catch(ClassNotFoundException ex) {
            
        } catch(IOException ex) {
            
        }
    }
    
    public static String loadSQL(String key) {
        String sql = null;
        sql = (String) dados.get(key);
        return sql;
    }
}
