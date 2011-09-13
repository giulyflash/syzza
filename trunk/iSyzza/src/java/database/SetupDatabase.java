package database;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe responsável pela gerência do banco de dados
 * @author Jonathan
 */
public class SetupDatabase {
    
    /**
     * Método que define a localização física do banco de dados
     * @param dir Diretório onde ficará o banco de dados
     */
    public static void setDBSystemDir(String dir) {
        // Decide on the db system directory: <userhome>/.addressbook/
        String userDir = System.getProperty("user.dir", ".");
        String systemDir = userDir + "/"+dir;
        // create the db system directory
        File fileSystemDir = new File(systemDir);
        if (!fileSystemDir.exists()){
            fileSystemDir.mkdir();
        }
        // Set the db system directory.
        System.setProperty("derby.system.home", systemDir);
    }
    
    /**
     * Método que verifica se o banco de dados existe
     * @param dbName Nome da base de dados
     * @return Resultado da verificação
     */
    public static boolean dbExists(String dbName) {
        boolean bExists = false;
        String dbLocation = getDatabaseLocation(dbName);
        File dbFileDir = new File(dbLocation);
        if (dbFileDir.exists()) {
            bExists = true;
        }
        return bExists;
    }
    
    private static String getDatabaseLocation(String dbName) {
        String dbLocation = System.getProperty("derby.system.home") + "/" + dbName;
        System.out.println("Local do Bd" + dbLocation); 
        return dbLocation;
    }
    
    /**
     * Método responável pela criação dos arquivos da base de dados
     * @param sqlTabelas Coleção de objetos contendo o sql de criação das tabelas
     * @param conexao Objeto de conexão com o banco de dados
     * @throws java.sql.SQLException Exceção gerada em caso de problema de execução do sql de criação das tabelas.
     */
    public static void createDatabase(ArrayList sqlTabelas, Connection conexao) throws SQLException{
        Statement stmt = conexao.createStatement();
        Iterator it = sqlTabelas.iterator();
        while (it.hasNext()){
            String sql = (String) it.next();
            stmt.execute(sql);
        }
    }
    
}