/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBConnection;
import entity.Sabor;
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
public class SaborDAO {
    
    private static HashMap dados;

    static {
        try {
            dados = new PropertiesManager("sql.properties").readPropertiesFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public static ArrayList<Sabor> getSabores() {
        ArrayList<Sabor> lista = new ArrayList<Sabor>();
        Sabor sabor;
        try {
            Connection conexao = DBConnection.getInstance();
            String sql = loadSQL("SelectAll.Sabor");
            System.out.println("chegou aki");
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("12345");
            while (rs.next()) {
                System.out.println("eiiii");
                sabor = recoverData(rs);
                lista.add(sabor);
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
    
    private static Sabor recoverData(ResultSet rs) throws SQLException {
        System.out.println("akiiiii");
        Sabor sabor = new Sabor();
        sabor.setId(rs.getInt("id_sabor"));
        sabor.setNome(rs.getString("nome"));
        System.out.println(sabor.toString());
        return sabor;
    }

    public static String loadSQL(String key) {
        String sql = null;
        sql = (String) dados.get(key);
        return sql;
    }
    
}
