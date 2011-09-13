package database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Classe responsável por ler e criar os arquivos de propriedades
 * @author Karen Borges
 * @version 1.0
 */
public class PropertiesManager {
    
    private String fileName;
    
    /**
     * Método construtor. Seta o nome do arquivo de propriedades que será manipulado
     * @param fileName Nome do arquivo de propriedades que será manipulado
     */
    public PropertiesManager(String fileName){
        this.fileName = fileName;
    }
    
    /**
     * Método responsável pela criação do arquivo de properties
     * @param dados Informações a serem armazenadas no arquivo
     * @throws java.io.IOException Exceção gerada em caso de problema de criação do arquivo de properties
     */
    public void createPropertiesFile(HashMap dados) throws IOException   {
        Properties props = new Properties();
        Set valores = dados.entrySet();
        Iterator it = valores.iterator();
        while (it.hasNext()){
            Map.Entry me = (Map.Entry) it.next();
            String chave = (String)me.getKey();
            String valor = (String)me.getValue();
            props.setProperty(chave, valor);
        }
        // Salvamos para uma proxima execussao
        FileOutputStream out = new FileOutputStream(fileName);
        props.store(out,null);
        out.close();
    }
    
    /**
     * Método responsável pela leitura do arquivo de properties
     * @return coleção de objetos contendo os dados do arquivo
     * @throws java.io.IOException Exceção gerada em caso de problema de leitura do arquivo de properties
     */
    public HashMap readPropertiesFile() throws IOException   {
        Properties properties;
        StringTokenizer sToken;
        HashMap lista = new HashMap();
        int count=0;
        
        InputStream is = getClass().getResourceAsStream( fileName );
        properties = new Properties();
        properties.load( is );
        
        for (Enumeration list = properties.propertyNames(); list.hasMoreElements(); count++) {
            String entry = (String) list.nextElement();
            //System.out.println("entrada = " + entry);
            lista.put(entry, properties.getProperty(entry));
        }
        return lista;
    }
    
}