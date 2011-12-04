/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import util.PropertiesManager;

/**
 *
 * @author Jonathan
 */
public class PizzaDAO {
    
    private static HashMap dados;

    static {
        try {
            dados = new PropertiesManager("sql.properties").readPropertiesFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public static ArrayList<PizzaDAO> getPizzasByIdPedido() {
        ArrayList<PizzaDAO> lista = new ArrayList<PizzaDAO>();
        PizzaDAO pizza;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectAll.Borda");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                //pizza = recoverData(rs);
                //lista.add(pizza);
            }
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: " + ex.getMessage());
        }
        return lista;
    }
    
    /*private static Pizza recoverData(ResultSet rs) throws SQLException {
        Pizza borda = new Borda();
        borda.setId(rs.getInt("id_borda"));
        borda.setNome(rs.getString("nome"));
        borda.setPreco(rs.getFloat("preco"));
        return borda;
    }*/

    public static String loadSQL(String key) {
        String sql = null;
        sql = (String) dados.get(key);
        return sql;
    }
    
}
