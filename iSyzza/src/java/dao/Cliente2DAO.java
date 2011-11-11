/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Cliente;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.HibernateException;

/**
 *
 * @author 0669105
 */
public class Cliente2DAO implements OperacoesDAO {

    @Override
    public boolean inserir(Object obj) {
        Cliente cliente = (Cliente) obj;
        h.beginTransaction();
        try {
            h.saveOnly(cliente);
        } catch (HibernateException e) {
            System.out.println("chegou aki");
        }
        h.endTransaction();
        return true;
    }

    @Override
    public ArrayList pesquisar() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        h.beginTransaction();
        try {
            clientes = (ArrayList<Cliente>) h.list(Cliente.class);
        } catch (HibernateException e) {
            System.out.println("chegou aki");
        }
        h.endTransaction();
        return clientes;
    }

    @Override
    public boolean editar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean excluir(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
   
}
