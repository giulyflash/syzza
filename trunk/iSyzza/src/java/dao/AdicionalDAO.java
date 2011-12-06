/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBConnection;
import entity.Adicional;
import entity.Borda;
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
public class AdicionalDAO {
    
    private static HashMap dados;

    static {
        try {
            dados = new PropertiesManager("sql.properties").readPropertiesFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public static ArrayList<Adicional> getAdicionais() {
        ArrayList<Adicional> lista = new ArrayList<Adicional>();
        Adicional adicional;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectAll.Adicional");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                adicional = recoverData(rs);
                lista.add(adicional);
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
    
    public static ArrayList<Adicional> getAdicionaisByTipo(int tipo) {
        ArrayList<Adicional> lista = new ArrayList<Adicional>();
        Adicional adicional;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectByTipo.Adicional");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, tipo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                adicional = recoverData(rs);
                lista.add(adicional);
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
    
    public static Adicional getAdicionalById(int id) {
        Adicional adicional = new Adicional();
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectById.Adicional");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                adicional = recoverData(rs);
            }
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: " + ex.getMessage());
        }
        return adicional;
    }
    
    private static Adicional recoverData(ResultSet rs) throws SQLException {
        Adicional adicional = new Adicional();
        adicional.setId(rs.getInt("id_adicional"));
        adicional.setNome(rs.getString("nome"));
        adicional.setPreco(rs.getFloat("preco"));
        adicional.setTipo(rs.getInt("tipo"));
        System.out.println(adicional.toString());
        return adicional;
    }

    public static String loadSQL(String key) {
        String sql = null;
        sql = (String) dados.get(key);
        return sql;
    }
    
}
