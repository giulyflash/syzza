/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Classe respons�vel por ler e criar os arquivos de propriedades
 * @author Karen Borges
 * @version 1.0
 */
public class PropertiesManager {
    
    private String fileName;
    
    /**
     * M�todo construtor. Seta o nome do arquivo de propriedades que ser� manipulado
     * @param fileName Nome do arquivo de propriedades que ser� manipulado
     */
    public PropertiesManager(String fileName){
        this.fileName = fileName;
    }
    
    /**
     * M�todo respons�vel pela cria��o do arquivo de properties
     * @param dados Informa��es a serem armazenadas no arquivo
     * @throws java.io.IOException Exce��o gerada em caso de problema de cria��o do arquivo de properties
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
     * M�todo respons�vel pela leitura do arquivo de properties
     * @return cole��o de objetos contendo os dados do arquivo
     * @throws java.io.IOException Exce��o gerada em caso de problema de leitura do arquivo de properties
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