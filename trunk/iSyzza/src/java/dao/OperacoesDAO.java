/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.Hibernate;
import java.util.ArrayList;

/**
 *
 * @author 0669105
 */
public interface OperacoesDAO {
    
    Hibernate h = new Hibernate();
    
    public boolean inserir(Object obj);
    
    public ArrayList pesquisar();
    
    public boolean editar(Object obj);
    
    public boolean excluir(Object obj);
    
}
