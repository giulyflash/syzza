
package entity;

import java.io.Serializable;

/**
 *
 * @author Jonathan
 */
public class Pizza implements Serializable{
    
    private int id;
    private int qtd;
    private Tamanho tamanho;
    private Borda borda;
    private Pedido pedido;

    public Pizza() {
    }

    public Borda getBorda() {
        return borda;
    }

    public void setBorda(Borda borda) {
        this.borda = borda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pizza other = (Pizza) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.qtd != other.qtd) {
            return false;
        }
        if (this.tamanho != other.tamanho && (this.tamanho == null || !this.tamanho.equals(other.tamanho))) {
            return false;
        }
        if (this.borda != other.borda && (this.borda == null || !this.borda.equals(other.borda))) {
            return false;
        }
        if (this.pedido != other.pedido && (this.pedido == null || !this.pedido.equals(other.pedido))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
        hash = 41 * hash + this.qtd;
        hash = 41 * hash + (this.tamanho != null ? this.tamanho.hashCode() : 0);
        hash = 41 * hash + (this.borda != null ? this.borda.hashCode() : 0);
        hash = 41 * hash + (this.pedido != null ? this.pedido.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Pizza{" + "id=" + id + ", qtd=" + qtd + ", tamanho=" + tamanho + ", borda=" + borda + ", pedido=" + pedido + '}';
    }
    
}
