/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBConnection;
import entity.Cliente;
import entity.Pedido;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import util.PropertiesManager;

/**
 *
 * @author 0669105
 */
public class PedidoDAO {

    private static HashMap dados;

    static {
        try {
            dados = new PropertiesManager("sql.properties").readPropertiesFile();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static void addPedido(Pedido pedido) {
        try {
            Connection conexao = DBConnection.getInstance();
            PreparedStatement st = conexao.prepareStatement("ALTER SESSION SET NLS_DATE_FORMAT = 'DD-MON-YYYY HH24:MI'");
            String sql = loadSQL("Insert.Pedido");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setTimestamp(1, new java.sql.Timestamp(pedido.getData_pedido().getTime()));
            pstmt.setNull(2, java.sql.Types.DATE);
            pstmt.setNull(3, java.sql.Types.DATE);
            pstmt.setNull(4, java.sql.Types.DATE);
            pstmt.setInt(5, pedido.getCliente().getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: " + ex.getMessage());
        }
    }

    public static ArrayList<Pedido> getPedidosById(Cliente cliente) {
        ArrayList<Pedido> lista = new ArrayList<Pedido>();
        Pedido pedido;
        try {
            Connection conexao = DBConnection.getInstance();
            System.out.println("chegou na dao");
            String sql = loadSQL("SelectByIdCliente.Pedido");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, cliente.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pedido = recoverData(rs);
                lista.add(pedido);
            }  
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: " + ex.getMessage());
        }
        return lista;
    }

    public static ArrayList<Pedido> getPedidos() {
    ArrayList<Pedido> lista = new ArrayList<Pedido>();
        Pedido pedido;
    try {
        Connection conexao = DBConnection.getInstance();
        PreparedStatement st = conexao.prepareStatement("ALTER SESSION SET NLS_DATE_FORMAT = 'DD-MON-YYYY HH24:MI'");
        String sql = loadSQL("SelectAll.Pedido");
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            pedido = recoverData(rs);
            lista.add(pedido);
        }  
    } catch (SQLException ex) {
        System.out.println("Erro no SQL: " + ex.getMessage());
    } catch (ClassNotFoundException ex) {
        System.out.println("Classe nao achada: " + ex.getMessage());
    } catch (IOException ex) {
        System.out.println("Erro de IO: " + ex.getMessage());
    }
    return lista;
    }
     
    private static Pedido recoverData(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(rs.getInt("id_pedido"));
        pedido.setData_pedido(rs.getDate("data_pedido"));
        pedido.setData_pedido(rs.getTimestamp("data_pag"));
        System.out.println(rs.getTimestamp("data_pedido"));
        pedido.setData_pronta(rs.getDate("data_pronta"));
        pedido.setData_entrega(rs.getDate("data_entrega"));
        pedido.setCliente(ClienteDAO.getClienteById(rs.getInt("id_cliente")));
        pedido.setPago(rs.getInt("pago"));
        return pedido;
    }
    
    public static String loadSQL(String key) {
        String sql = null;
        sql = (String) dados.get(key);
        return sql;
    }
    
    public static void main(String[] args) {
        //Cliente cliente = ClienteDAO.getClienteById(1);
        Pedido pedido = new Pedido();
        pedido.setData_pedido(new java.util.Date(System.currentTimeMillis()));
        System.out.println(new java.util.Date(System.currentTimeMillis()));
        //pedido.setCliente(cliente);
        //PedidoDAO.addPedido(pedido);
        //ArrayList<Pedido> pedidos = PedidoDAO.getPedidosById(cliente);
       // for (Pedido pedido2 : pedidos) {
        //    System.out.println("Id do pedido: "+pedido2.getId()+"Data do pedido: "+pedido2.getData_pedido());
        //}
    }
}
