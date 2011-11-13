/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class TestaConexao {

    public static void main(String[] args) {

        Connection conexao;
        try {
            conexao = DBConnection.getInstance();
            if (conexao != null) {
                System.out.println("Conexão realizada com sucesso ");
            } 
        } catch (SQLException ex) {
            System.out.println("Conexão falhou " + "\n" + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Conexão falhou " + "\n" + ex);
        } catch (IOException ex) {
            System.out.println("Conexão falhou " + "\n" + ex);
        } finally {
            try {
                DBConnection.closeConnection();
                System.out.println("Conexão encerrada");
            } catch (SQLException ex) {
                Logger.getLogger(TestaConexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
}
