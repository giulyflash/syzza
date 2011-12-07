/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBConnection;
import entity.Pizza;
import entity.PizzaSabor;
import entity.Sabor;
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
public class PizzaSaborDAO {
    
    private static HashMap dados;

    static {
        try {
            dados = new PropertiesManager("sql.properties").readPropertiesFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public static void addPizzaSabor(PizzaSabor ps) {
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("Insert.PizzaSabor");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, ps.getPizza().getId());
            pstmt.setInt(2, ps.getSabor().getId());
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
    
    public static ArrayList<Sabor> getSaboresByPizza(Pizza pizza) {
        ArrayList<Sabor> lista= new ArrayList<Sabor>();
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectByPizza.PizzaSabor");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, pizza.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Sabor sabor = recoverData(rs);
                lista.add(sabor);
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
    
    private static Sabor recoverData(ResultSet rs) throws SQLException {
        Sabor sabor = null;
        sabor = SaborDAO.getSaborById(rs.getInt("id_sabor"));
        return sabor;
    }
    
    public static String loadSQL(String key) {
        String sql = null;
        sql = (String) dados.get(key);
        return sql;
    }
    
}
