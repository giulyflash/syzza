
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Jonathan
 */

@Entity
@Table
@SequenceGenerator(name = "s_cliente", sequenceName = "s_cliente", allocationSize=1)
public class Cliente implements Serializable{
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="s_cliente")
    @Column(name="id_cliente")
    private int id;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String senha;
    @Column
    private int salt;
    @Column
    private int qtd_pizzas;
    @Column
    private String telefone;
    @Column
    private String endereco;
    @Column
    private String cpf;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_cadastro;

    public static final int ID = 1;
    public static final int NOME = 2;
    public static final int EMAIL = 3;
    public static final int SENHA = 4;
    public static final int SALT = 5;
    public static final int QTD_PIZZAS = 6;
    public static final int TELEFONE = 7;
    public static final int ENDERECO = 8;
    public static final int CPF = 9;
    public static final int DATA_CADASTRO = 10;
    
    public Cliente() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtd_pizzas() {
        return qtd_pizzas;
    }

    public void setQtd_pizzas(int qtd_pizzas) {
        this.qtd_pizzas = qtd_pizzas;
    }

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
        if ((this.senha == null) ? (other.senha != null) : !this.senha.equals(other.senha)) {
            return false;
        }
        if (this.salt != other.salt) {
            return false;
        }
        if (this.qtd_pizzas != other.qtd_pizzas) {
            return false;
        }
        if ((this.telefone == null) ? (other.telefone != null) : !this.telefone.equals(other.telefone)) {
            return false;
        }
        if ((this.endereco == null) ? (other.endereco != null) : !this.endereco.equals(other.endereco)) {
            return false;
        }
        if ((this.cpf == null) ? (other.cpf != null) : !this.cpf.equals(other.cpf)) {
            return false;
        }
        if (this.data_cadastro != other.data_cadastro && (this.data_cadastro == null || !this.data_cadastro.equals(other.data_cadastro))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.id;
        hash = 13 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 13 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 13 * hash + (this.senha != null ? this.senha.hashCode() : 0);
        hash = 13 * hash + this.salt;
        hash = 13 * hash + this.qtd_pizzas;
        hash = 13 * hash + (this.telefone != null ? this.telefone.hashCode() : 0);
        hash = 13 * hash + (this.endereco != null ? this.endereco.hashCode() : 0);
        hash = 13 * hash + (this.cpf != null ? this.cpf.hashCode() : 0);
        hash = 13 * hash + (this.data_cadastro != null ? this.data_cadastro.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", salt=" + salt + ", qtd_pedidos=" + qtd_pizzas + ", telefone=" + telefone + ", endereco=" + endereco + ", cpf=" + cpf + ", data_cadastro=" + data_cadastro + '}';
    }
    
}
