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
import java.text.SimpleDateFormat;
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
            Connection conexao = DBConnection.getInstance();
            PreparedStatement pstmt = conexao.prepareStatement("ALTER SESSION SET NLS_DATE_FORMAT = 'dd/mm/yyyy hh24:mi'");
            pstmt.execute();    
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void addPedido(Pedido pedido) {
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("Insert.Pedido");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setDate(1, new Date(pedido.getData_pedido().getTime()));
            pstmt.setNull(2, java.sql.Types.DATE);
            pstmt.setNull(3, java.sql.Types.DATE);
            pstmt.setNull(4, java.sql.Types.DATE);
            pstmt.setInt(5, pedido.getCliente().getId());
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

    public static ArrayList<Pedido> getPedidosById(Cliente cliente) {
        ArrayList<Pedido> lista = new ArrayList<Pedido>();
        Pedido pedido;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectByIdCliente.Pedido");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, cliente.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pedido = recoverData(rs);
                lista.add(pedido);
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

    public static ArrayList<Pedido> getPedidos() {
        ArrayList<Pedido> lista = new ArrayList<Pedido>();
        Pedido pedido;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectAll.Pedido");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pedido = recoverData(rs);
                lista.add(pedido);
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

    private static Pedido recoverData(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(rs.getInt("id_pedido"));
        pedido.setData_pedido(rs.getDate("data_pedido"));
        SimpleDateFormat formato = new SimpleDateFormat("MMM dd/MM/yyyy HH:mm");
        String data = formato.format(rs.getDate("data_pedido"));
        System.out.println(data);
        pedido.setData_pedido(rs.getDate("data_pag"));
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
        Cliente cliente = ClienteDAO.getClienteById(2);
        System.out.println(cliente.getId());
        ArrayList<Pedido> pedidos = getPedidosById(cliente);
        //PedidoDAO.addPedido(pedido);
        //ArrayList<Pedido> pedidos = PedidoDAO.getPedidosById(cliente);
         for (Pedido pedido2 : pedidos) {
            System.out.println("Id do pedido: "+pedido2.getId()+"Data do pedido: "+pedido2.getData_pedido());
        }
    }
}
