/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBConnection;
import entity.Tamanho;
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
public class TamanhoDAO {
     
    private static HashMap dados;

    static {
        try {
            dados = new PropertiesManager("sql.properties").readPropertiesFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public static ArrayList<Tamanho> getTamanhos() {
        ArrayList<Tamanho> lista = new ArrayList<Tamanho>();
        Tamanho tamanho;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectAll.Tamanho");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                tamanho = recoverData(rs);
                lista.add(tamanho);
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
    
    public static Tamanho getTamanhoById(int id) {
        Tamanho tamanho = new Tamanho();
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectById.Tamanho");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                tamanho = recoverData(rs);
            }
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro no SQL: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao achada: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de IO: " + ex.getMessage());
        }
        return tamanho;
    }
    
    private static Tamanho recoverData(ResultSet rs) throws SQLException {
        Tamanho tamanho = new Tamanho();
        tamanho.setId(rs.getInt("id_tamanho"));
        tamanho.setNome(rs.getString("nome"));
        tamanho.setPreco(rs.getFloat("preco"));
        return tamanho;
    }

    public static String loadSQL(String key) {
        String sql = null;
        sql = (String) dados.get(key);
        return sql;
    }
}
