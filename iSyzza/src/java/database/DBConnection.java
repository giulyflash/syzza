/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.*;
import javax.swing.*;
import java.sql.*; // para manipulação do JDBC
import java.util.Properties;

/**
 * Classe responsável pelo gerenciamento da conexão com o banco de dados
 * @author Karen Borges
 */
public class DBConnection {
    
    //Atributos para manipulação do BD
    private static String user;
    private static String password;
    private static String driver;
    private static String url;
    private static Connection conexao;
    
    /**
     * Método do padrão Singleton.
     * Se a conexão não existir, cria e retorna.
     * Caso contrário retorna o objeto de conexão já existente.
     * @param user Usuário de conexão com o banco de dados
     * @param password Senha de conexão com o banco de dados
     * @param driver Driver de conexão com o banco de dados
     * @param url URL de conexão com o banco de dados
     * @return Objeto de conexão
     * @throws java.sql.SQLException Exceção gerada em caso de problema de conexão com o banco de dados
     * @throws java.lang.ClassNotFoundException Exceção gerada em caso de problemas com o driver do banco de dados
     */
    public static Connection getInstance(String user, String password, String driver, String url) throws SQLException, ClassNotFoundException {
        if( conexao == null ) {
            setUser(user);
            setPassword(password);
            setDriver(driver);
            setUrl(url);
            new DBConnection();
        }
        return conexao;
    }
    
    /**
     * Método que retorna o objeto de conexão que já existe.
     * @return o objeto de conexão
     */
    public static Connection getInstance(){
        return conexao;
    }
    
    private DBConnection() throws SQLException, ClassNotFoundException {
        conexao = openConnection();
    }
    
    private Connection openConnection() throws SQLException, ClassNotFoundException  {
        Class.forName(driver); //carrega o drive. Pode gerar ClassNotFoundException
        conexao = DriverManager.getConnection(url, user, password); // abre a conexão para esta URL usando o driver carregado na linha anterior
        return conexao; //Conseguiu abrir a conexao
    }
    
    /**
     * Método que fecha a conexão.
     * @throws java.sql.SQLException Exceção gerada em caso de problema no momento de encerrar a conexão
     */
    public static void closeConnection() throws SQLException{
        conexao.close();
        conexao = null;
    }

    private static void setUser(String aUser) {
        user = aUser;
    }

    private static void setPassword(String aPassword) {
        password = aPassword;
    }

    private static void setDriver(String aDriver) {
        driver = aDriver;
    }

    private static void setUrl(String aUrl) {
        url = aUrl;
    }
  
}
