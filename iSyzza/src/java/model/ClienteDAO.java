/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.DBConnection;
import database.PropertiesManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonathan
 */
public class ClienteDAO {
    
    public static final int ID = 1;
    public static final int NOME = 2;
    public static final int LOGIN = 3;
    public static final int SENHA = 4;
    public static final int ENDERECO = 5;
    public static final int DATA = 6;
    
    private static HashMap dados;
    
    static {
        try {
            dados = new PropertiesManager("sql.properties").readPropertiesFile();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
    
    public static void inserirCliente(Cliente cliente) {
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("Insert.Cliente");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getLogin());
            pstmt.setString(3, cliente.getSenha());
            pstmt.setString(4, cliente.getEndereco());
            pstmt.setDate(5, new java.sql.Date(cliente.getData().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Cliente pesquisarCliente(int aid) {
        Cliente cliente = null;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectById.Cliente");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, aid);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int id = rs.getInt("idcliente");
            String nome = rs.getString("nome");
            String login = rs.getString("login");
            String senha = rs.getString("senha");
            String endereco = rs.getString("endereco");
            Date data = rs.getDate("dataCadastro");
            cliente = new Cliente(id, nome, login, senha, endereco, data);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }
    
    public static Cliente pesquisarCliente(String alogin) {
        Cliente cliente = null;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectByLogin.Cliente");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, alogin);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int id = rs.getInt("idcliente");
            String nome = rs.getString("nome");
            String login = rs.getString("login");
            String senha = rs.getString("senha");
            String endereco = rs.getString("endereco");
            Date data = rs.getDate("dataCadastro");
            cliente = new Cliente(id, nome, login, senha, endereco, data);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }
    
    public static Cliente pesquisarCliente(String alogin, String asenha) {
        Cliente cliente = null;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectByLoginSenha.Cliente");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, alogin);
            pstmt.setString(2, asenha);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int id = rs.getInt("idcliente");
            String nome = rs.getString("nome");
            String login = rs.getString("login");
            String senha = rs.getString("senha");
            String endereco = rs.getString("endereco");
            Date data = rs.getDate("dataCadastro");
            cliente = new Cliente(id, nome, login, senha, endereco, data);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }
    
    public static ArrayList<Cliente> pesquisarCliente() {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        Cliente cliente;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectAll.Cliente");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("idcliente");
                String nome = rs.getString("nome");
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                String endereco = rs.getString("endereco");
                Date data = rs.getDate("dataCadastro");
                cliente = new Cliente(id, nome, login, senha, endereco, data);
                lista.add(cliente);
         }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public static String loadSQL(String key) {
        String sql = null;
        sql = (String) dados.get(key);
        return sql;
    }
    
    public static void main(String[] args) {
        Cliente cliente1 = pesquisarCliente(1);
        System.out.println(cliente1.toString());
        Cliente cliente2 = pesquisarCliente("ilusion__");
        System.out.println(cliente2.toString());
        Cliente cliente3 = pesquisarCliente("jonnyav", "12345");
        System.out.println(cliente3.toString());
        System.out.println("\n\n");
        ArrayList<Cliente> lista = pesquisarCliente();
        for (Cliente cliente : lista) {
            System.out.println(cliente.toString());
        }
        try {
            DBConnection.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
