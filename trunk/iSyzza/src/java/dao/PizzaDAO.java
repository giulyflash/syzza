/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBConnection;
import entity.Pedido;
import entity.Pizza;
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
    
    public static void addPizza(Pizza pizza) {
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("Insert.Pizza");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, pizza.getQtd());
            pstmt.setInt(2, pizza.getTamanho().getId());
            pstmt.setInt(3, pizza.getBorda().getId());
            pstmt.setInt(4, pizza.getPedido().getId());
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
    
    
    
    public static ArrayList<Pizza> getPizzasByPedido(Pedido pedido) {
        ArrayList<Pizza> lista = new ArrayList<Pizza>();
        Pizza pizza;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectByPedido.Pizza");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, pedido.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pizza = recoverData(rs);
                lista.add(pizza);
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
    
    public static Pizza getPizzaByNova() {
        Pizza pizza=null;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectByNova.Pizza");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pizza = recoverData(rs);
            }
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: " + ex.getMessage());
        }
        return pizza;
    }
    
    public static void setNovaPizza(Pizza pizza) {
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("UpdateNova.Pizza");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, pizza.getId());
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
    
    private static Pizza recoverData(ResultSet rs) throws SQLException {
        Pizza pizza = new Pizza();
        pizza.setId(rs.getInt("id_pizza"));
        pizza.setQtd(rs.getInt("qtd"));
        pizza.setTamanho(TamanhoDAO.getTamanhoById(rs.getInt("id_tamanho")));
        pizza.setBorda(BordaDAO.getBordaById(rs.getInt("id_borda")));
        pizza.setPedido(PedidoDAO.getPedidoById(rs.getInt("id_pedido")));
        pizza.setSabores(PizzaSaborDAO.getSaboresByPizza(pizza));
        return pizza;
    }

    public static String loadSQL(String key) {
        String sql = null;
        sql = (String) dados.get(key);
        return sql;
    }
    
}
