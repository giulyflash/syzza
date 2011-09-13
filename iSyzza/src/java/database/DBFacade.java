/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Classe do padrão Facade.
 * Interface com a camada de controle do banco de dados
 * @author Karen Borges
 */
public class DBFacade {
    
    private static String driver;
    private static String url;
    private static String database;
    private static String dir;
    
    /**
     * Método responsável pela criação do objeto de conexão.
     * Se o banco de dados ainda não existe então cria.
     * @param user Usuário de conexão com o banco de dados
     * @param password Senha de conexão com o banco de dados
     * @throws java.sql.SQLException Exceção gerada em caso de problemas no sql de criação das tabelas do banco de dados
     * @throws java.io.IOException Exceção gerada em caso de problema de leitura dos arquivos de propriedades
     * @throws java.lang.ClassNotFoundException Exceção gerada em caso de problemas com o driver do banco de dados
     */
    public static void getConnection(String user, String password) throws SQLException, IOException, ClassNotFoundException{
        getConnectionInformation();
        ArrayList sqlTabelas = getCreateTableInformation();
        SetupDatabase.setDBSystemDir(dir);
        if (!SetupDatabase.dbExists(database)){
            javax.swing.JOptionPane.showMessageDialog(null, "O Banco de Dados Será Criado. \nAguarde um momento ... ");
            url=url+";create=true";
            Connection conexao = DBConnection.getInstance(user, password, driver, url);
            SetupDatabase.createDatabase(sqlTabelas, conexao);
            javax.swing.JOptionPane.showMessageDialog(null, "Banco criado com sucesso!");
        } else {
            Connection conexao = DBConnection.getInstance(user, password, driver, url);
        }
    }
    
    /**
     * Método responsável pela obtenção dos dados de conexão com o banco de dados
     * @throws java.io.IOException Exceção gerada em caso de problemas com a leitura do arquivo db.properties
     */
    private static void getConnectionInformation() throws IOException {
        PropertiesManager pm = new PropertiesManager("db.properties");
        HashMap dados = pm.readPropertiesFile();
        driver = (String) dados.get("conexao.driver");
        //System.out.println("driver = " + driver);
        url = (String) dados.get("conexao.url");
        //System.out.println("url = "+ url);
        database = (String) dados.get("conexao.database");
        //System.out.println("database = " + database);
        dir = (String) dados.get("conexao.dir");
        //System.out.println("dir = "+ dir);
    }
    
    /**
     * Método responsável pela obtenção do sql de criação das tabelas do banco de dados
     * @return Coleção de objetos contendo o sql de criação das tabelas do banco de dados
     * @throws java.io.IOException Exceção gerada em caso de problemas de leitura do arquivo createTable.properties
     */
    private static ArrayList getCreateTableInformation() throws IOException {
        PropertiesManager pm = new PropertiesManager("createTable.properties");
        HashMap dados = pm.readPropertiesFile();
        Set chaves = dados.keySet(); // Recupera as chaves
        Object[] lista = chaves.toArray(); // transforma de set para array
        // Tem que ordenar porque o HashMap não coloca as chaves em ordem
        Arrays.sort(lista); // ordena o array
        ArrayList sqls = new ArrayList();
        for (int i=0; i<lista.length; i++){
            String chave = (String) lista[i];
            sqls.add(dados.get(chave));
        }
        return sqls;
    }
}
