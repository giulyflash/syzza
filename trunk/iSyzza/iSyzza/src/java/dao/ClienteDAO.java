package dao;

import database.DBConnection;
import util.PropertiesManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Cliente;
import java.util.Date;

/**
 *
 * @author Jonathan
 */
public class ClienteDAO {

    public static final int ID = 1;
    public static final int NOME = 2;
    public static final int EMAIL = 3;
    public static final int SENHA = 4;
    public static final int SALT = 5;
    public static final int QTD_PIZZAS = 6;
    public static final int TELEFONE = 7;
    public static final int ENDERECO = 8;
    public static final int CPF = 9;
    public static final int DATA_CADASTRO = 10;
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
            pstmt.setString(NOME - 1, cliente.getNome());
            pstmt.setString(EMAIL - 1, cliente.getEmail());
            pstmt.setString(SENHA - 1, cliente.getSenha());
            pstmt.setInt(SALT - 1, cliente.getSalt());
            pstmt.setInt(QTD_PIZZAS - 1, cliente.getQtd_pizzas());
            pstmt.setString(TELEFONE - 1, cliente.getTelefone());
            pstmt.setString(ENDERECO - 1, cliente.getEndereco());
            pstmt.setString(CPF - 1, cliente.getCpf());
            pstmt.setDate(DATA_CADASTRO - 1, new java.sql.Date(cliente.getData_cadastro().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Cliente pesquisarCliente(int id) {
        Cliente cliente = null;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectById.Cliente");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setSalt(rs.getInt("salt"));
                cliente.setQtd_pizzas(rs.getInt("qtd_pizzas"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setData_cadastro(rs.getDate("data_cadastro"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }

    public static Cliente pesquisarCliente(String aemail) {
        Cliente cliente = null;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectByEmail.Cliente");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, aemail);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setSalt(rs.getInt("salt"));
                cliente.setQtd_pizzas(rs.getInt("qtd_pizzas"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setData_cadastro(rs.getDate("data_cadastro"));
            }
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
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setSalt(rs.getInt("salt"));
                cliente.setQtd_pizzas(rs.getInt("qtd_pizzas"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setData_cadastro(rs.getDate("data_cadastro"));
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

        /*for (int i = 0; i < 100; i++) {
        int salt = (int) (Math.random() * 2147483647);
        System.out.println(salt);
        }*/

        /*
         *TESTANDO INSERCAO DE CLIENTES 
         */
        Cliente cliente = new Cliente();
        cliente.setNome("Jonathan");
        cliente.setEmail("jonathan.videira@gmail.com");
        cliente.setSenha("senha");
        cliente.setSalt(1);
        cliente.setQtd_pizzas(0);
        cliente.setTelefone("30243795");
        cliente.setEndereco("Rua Moysés Antunes da Cunha 55/814");
        cliente.setCpf("02834560005");
        cliente.setData_cadastro(new Date(System.currentTimeMillis()));
        inserirCliente(cliente);

        /*
         * TESTANDO PESQUISAR CLIENTE POR ID
         */
        Cliente cliente2 = pesquisarCliente(1);
        System.out.println(cliente2.toString());

        /*
         * TESTANDO PESQUISAR CLIENTE POR EMAIL
         */
        Cliente cliente3 = pesquisarCliente("jonathan.videira@gmail.com");
        System.out.println(cliente2.toString());

        /*
         * TESTANDO PESQUISAR TODOS OS CLIENTES
         */
        ArrayList<Cliente> clientes = pesquisarCliente();
        for (Cliente cliente4 : clientes) {
            System.out.println(cliente4.toString());
        }
        try {
            DBConnection.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
