
package entity;

import java.io.Serializable;

/**
 *
 * @author Jonathan
 */
public class Adicional implements Serializable{
    
    private int id;
    private String nome;
    private float preco;

    public Adicional() {
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

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Adicional other = (Adicional) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if (Float.floatToIntBits(this.preco) != Float.floatToIntBits(other.preco)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id;
        hash = 29 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 29 * hash + Float.floatToIntBits(this.preco);
        return hash;
    }

    @Override
    public String toString() {
        return "Adicional{" + "id=" + id + ", nome=" + nome + ", preco=" + preco + '}';
    }
    
}
