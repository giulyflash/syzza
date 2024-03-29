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
import entity.Cliente;

/**
 *
 * @author Jonathan
 */
public class ClienteDAO {

    private static HashMap dados;

    static {
        try {
            dados = new PropertiesManager("sql.properties").readPropertiesFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public static void addCliente(Cliente cliente) {
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("Insert.Cliente");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getSenha());
            pstmt.setInt(4, cliente.getSalt());
            pstmt.setInt(5, cliente.getQtd_pizzas());
            pstmt.setString(6, cliente.getTelefone());
            pstmt.setTimestamp(7, new java.sql.Timestamp(cliente.getData_nasc().getTime()));
            pstmt.setString(8, cliente.getCpf());
            pstmt.setString(9, cliente.getEndereco());
            pstmt.setTimestamp(10, new java.sql.Timestamp(cliente.getData_cadastro().getTime()));
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: "+ex.getMessage());
        }
    }

    public static void setClientePessoais(Cliente cliente) {
       try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("UpdatePessoais.Cliente");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getSenha());
            pstmt.setInt(4, cliente.getSalt());
            pstmt.setString(5, cliente.getTelefone());
            pstmt.setDate(6, new java.sql.Date(cliente.getData_nasc().getTime()));
            pstmt.setString(7, cliente.getCpf());
            pstmt.setString(8, cliente.getEndereco());
            pstmt.setInt(9, cliente.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: "+ex.getMessage());
        }
    }
    
    public static void setClienteQtdPizzas(Cliente cliente) {
       try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("UpdateQtdPizzas.Cliente");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, cliente.getQtd_pizzas());
            pstmt.setInt(2, cliente.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: "+ex.getMessage());
        }
    }
    
    public static void removeCliente(Cliente cliente) {
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("Delete.Cliente");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, cliente.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: "+ex.getMessage());
        }
    
    }
    
    public static Cliente getClienteById(int id) {
        Cliente cliente = null;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectById.Cliente");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cliente = recoverData(rs);
            }
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: "+ex.getMessage());
        }
        return cliente;
    }

    public static Cliente getClienteByEmail(String email) {
        Cliente cliente = null;
        try {
            
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectByEmail.Cliente");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cliente = recoverData(rs);
            }
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: "+ex.getMessage());
        }
        return cliente;
    }

    public static Cliente getClienteByCpf(String cpf) {
        Cliente cliente = null;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectByCpf.Cliente");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cliente = recoverData(rs);
            }
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: "+ex.getMessage());
        }
        return cliente;
    }
        
    public static ArrayList<Cliente> getClientes() {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        Cliente cliente;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectAll.Cliente");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                cliente = recoverData(rs);
                lista.add(cliente);
            }
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: "+ex.getMessage());
        }
        return lista;
    }

    private static Cliente recoverData(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id_cliente"));
        cliente.setNome(rs.getString("nome"));
        cliente.setEmail(rs.getString("email"));
        cliente.setSenha(rs.getString("senha"));
        cliente.setSalt(rs.getInt("salt"));
        cliente.setQtd_pizzas(rs.getInt("qtd_pizzas"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setData_nasc(rs.getDate("data_nascimento"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setEndereco(rs.getString("endereco"));
        cliente.setData_cadastro(rs.getTimestamp("data_cadastro"));
        return cliente;
    }
    
    public static String loadSQL(String key) {
        String sql = null;
        sql = (String) dados.get(key);
        return sql;
    }
   
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        
    }
}
