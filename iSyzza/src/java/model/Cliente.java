/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Jonathan
 */
public class Cliente {
    
    private int id;
    private String nome;
    private String login;
    private String senha;
    private String endereco;
    private Date data;

    public static final int ID = 1;
    public static final int NOME = 2;
    public static final int LOGIN = 3;
    public static final int SENHA = 4;
    public static final int ENDERECO = 5;
    public static final int DATA = 6;
    
    public Cliente(int id, String nome, String login, String senha, String endereco, Date data) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.endereco = endereco;
        this.data = data;
    }

    public Cliente(String nome, String login, String senha, String endereco, Date data) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.endereco = endereco;
        this.data = data;
    }

    public Cliente() {
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if ((this.senha == null) ? (other.senha != null) : !this.senha.equals(other.senha)) {
            return false;
        }
        if ((this.endereco == null) ? (other.endereco != null) : !this.endereco.equals(other.endereco)) {
            return false;
        }
        if (this.data != other.data && (this.data == null || !this.data.equals(other.data))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        hash = 79 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 79 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 79 * hash + (this.senha != null ? this.senha.hashCode() : 0);
        hash = 79 * hash + (this.endereco != null ? this.endereco.hashCode() : 0);
        hash = 79 * hash + (this.data != null ? this.data.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + ", login=" + login + ", senha=" + senha + ", endereco=" + endereco + ", data=" + data + '}';
    }
    
}
