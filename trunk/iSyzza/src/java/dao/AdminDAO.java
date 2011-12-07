/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBConnection;
import entity.Admin;
import entity.Cliente;
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
public class AdminDAO {
    
    private static HashMap dados;

    static {
        try {
            dados = new PropertiesManager("sql.properties").readPropertiesFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public static void addAdmin(Admin admin) {
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("Insert.Admin");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, admin.getNome());
            pstmt.setString(2, admin.getEmail());
            pstmt.setString(3, admin.getSenha());
            pstmt.setInt(4, admin.getSalt());
            pstmt.setInt(5, admin.getNivel());
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
    
    public static ArrayList<Admin> getAdmins() {
        ArrayList<Admin> lista = new ArrayList<Admin>();
        Admin admin;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectAll.Admin");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                admin = recoverData(rs);
                lista.add(admin);
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
    
    public static Admin getAdminById(int id) {
        Admin admin = null;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectById.Admin");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                admin = recoverData(rs);
            }
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: "+ex.getMessage());
        }
        return admin;
    }

    public static Admin getAdminByEmail(String email) {
        Admin admin = null;
        try {
            
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectByEmail.Admin");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                admin = recoverData(rs);
            }
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: "+ex.getMessage());
        }
        return admin;
    }
    
    public static void deleteAdmin(Admin admin) {
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("Delete.Admin");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, admin.getId());
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
    
    private static Admin recoverData(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        admin.setId(rs.getInt("id_admin"));
        admin.setNome(rs.getString("nome"));
        admin.setEmail(rs.getString("email"));
        admin.setSenha(rs.getString("senha"));
        admin.setSalt(rs.getInt("salt"));
        admin.setNivel(rs.getInt("nivel"));
        return admin;
    }
    
    public static String loadSQL(String key) {
        String sql = null;
        sql = (String) dados.get(key);
        return sql;
    }
    
}
