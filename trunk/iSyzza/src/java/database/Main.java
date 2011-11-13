/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.Hibernate;
import dao.Cliente2DAO;
import entity.Cliente;
import java.util.Date;

/**
 *
 * @author 0669105
 */
public class Main {
     /*public static void main(String[] args) {
         Hibernate h = new Hibernate();
         
        Cliente cliente = new Cliente();
        cliente.setNome("Jonathan");
        cliente.setEmail("jonathan.videira@gmail.com");
        cliente.setSenha("senha");
        cliente.setSalt(1);
        cliente.setQtd_pizzas(0);
        cliente.setTelefone("30243795");
        cliente.setEndereco("Rua Moysés Antunes da Cunha 55/814");
        cliente.setCpf("02834560005");
        cliente.setData_cadastro(new Date(System.currentTimeMillis()));
        Cliente2DAO c2 = new Cliente2DAO();
        h.beginTransaction();
        c2.inserir(cliente);
        h.endTransaction();
    }*/
}
