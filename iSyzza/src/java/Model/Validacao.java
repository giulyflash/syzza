/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author inf4m
 */
public class Validacao {
    private String login;
    private String senha;
    
    public Validacao(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
    
    public int validar() {
        if (login.equals("admin@admin.com") && senha.equals("senha"))
            return 1;
        else
            return 0;
    }
    
}
