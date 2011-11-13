
package database;

import util.PropertiesManager;
import java.io.*;
import java.sql.*; // para manipulação do JDBC
import java.util.HashMap;

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
     * M�todo do padr�o Singleton.
     * Se a conex�o n�o existir, cria e retorna.
     * Caso contr�rio retorna o objeto de conex�o j� existente.
     * @param user Usu�rio de conex�o com o banco de dados
     * @param password Senha de conex�o com o banco de dados
     * @param driver Driver de conex�o com o banco de dados
     * @param url URL de conex�o com o banco de dados
     * @return Objeto de conex�o
     * @throws java.sql.SQLException Exce��o gerada em caso de problema de conex�o com o banco de dados
     * @throws java.lang.ClassNotFoundException Exce��o gerada em caso de problemas com o driver do banco de dados
     */
    public static Connection getInstance() throws SQLException, ClassNotFoundException, IOException {
        if( conexao == null ) {
            getConnectionInformation();
            new DBConnection();
        }
        return conexao;
    }
    
    
    private DBConnection() throws SQLException, ClassNotFoundException {
        conexao = openConnection();
    }
    
    private Connection openConnection() throws SQLException, ClassNotFoundException  {
        Class.forName(driver); //carrega o drive. Pode gerar ClassNotFoundException
        conexao = DriverManager.getConnection(url, user, password); // abre a conex�o para esta URL usando o driver carregado na linha anterior
        return conexao; //Conseguiu abrir a conexao
    }
    
    /**
     * M�todo que fecha a conex�o.
     * @throws java.sql.SQLException Exce��o gerada em caso de problema no momento de encerrar a conex�o
     */
    public static void closeConnection() throws SQLException{
        conexao.close();
        conexao = null;
    }

    private static void getConnectionInformation() throws IOException {
        PropertiesManager pm = new PropertiesManager("db.properties");
        HashMap dados = pm.readPropertiesFile();
        user = (String) dados.get("conexao.user");
        password = (String) dados.get("conexao.senha");
        driver = (String) dados.get("conexao.driver");
        url = (String) dados.get("conexao.url");
    }
  
}