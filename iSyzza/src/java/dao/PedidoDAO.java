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
import java.sql.Timestamp;
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
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void addPedido(Pedido pedido) {
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("Insert.Pedido");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setTimestamp(1, new Timestamp(pedido.getData_pedido().getTime()));
            pstmt.setNull(2, java.sql.Types.TIMESTAMP);
            pstmt.setNull(3, java.sql.Types.TIMESTAMP);
            pstmt.setNull(4, java.sql.Types.TIMESTAMP);
            pstmt.setInt(5, pedido.getCliente().getId());
            pstmt.setString(6, pedido.getEndereco());
            pstmt.setString(7, pedido.getTelefone());
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
    
    public static void setPagamentoPedido(Pedido pedido) {
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("UpdatePagamento.Pedido");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setTimestamp(1, new Timestamp(pedido.getData_pag().getTime()));
            pstmt.setInt(2, pedido.getId());
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
    
    public static Pedido getPedidoById(int id) {
        Pedido pedido = null;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectById.Pedido");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                pedido = recoverData(rs);
            }
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: " + ex.getMessage());
        }

        return pedido;
    }
    
    public static ArrayList<Pedido> getPedidosByCliente(Cliente cliente) {
        ArrayList<Pedido> lista = new ArrayList<Pedido>();
        Pedido pedido;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectByCliente.Pedido");
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

    public static Pedido getPedidoByNova() {
        Pedido pedido = null;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectByNova.Pedido");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                pedido = recoverData(rs);
            }
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: " + ex.getMessage());
        }

        return pedido;
    }
    
    private static Pedido recoverData(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(rs.getInt("id_pedido"));
        pedido.setData_pedido(rs.getTimestamp("data_pedido"));
        pedido.setData_pag(rs.getTimestamp("data_pag"));
        pedido.setData_pronta(rs.getTimestamp("data_pronta"));
        pedido.setData_entrega(rs.getTimestamp("data_entrega"));
        pedido.setCliente(ClienteDAO.getClienteById(rs.getInt("id_cliente")));
        pedido.setPago(rs.getInt("pago"));
        pedido.setEndereco(rs.getString("endereco"));
        pedido.setTelefone(rs.getString("telefone"));
        return pedido;
    }

    public static String loadSQL(String key) {
        String sql = null;
        sql = (String) dados.get(key);
        return sql;
    }

    public static void main(String[] args) {
        Cliente cliente = ClienteDAO.getClienteById(3);
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setData_pedido(new Date(System.currentTimeMillis()));
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String data = formato.format(new Date(System.currentTimeMillis()));
        System.out.println(data);
        //System.out.println(cliente.getId());
        //ArrayList<Pedido> pedidos = getPedidosById(cliente);
        PedidoDAO.addPedido(pedido);
        //ArrayList<Pedido> pedidos = PedidoDAO.getPedidosById(cliente);
         //for (Pedido pedido2 : pedidos) {
           // System.out.println("Id do pedido: "+pedido2.getId()+"Data do pedido: "+pedido2.getData_pedido());
        //}
    }
}
