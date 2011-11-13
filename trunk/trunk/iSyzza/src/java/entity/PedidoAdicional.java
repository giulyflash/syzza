
package entity;

import java.io.Serializable;

/**
 *
 * @author Jonathan
 */
public class PedidoAdicional implements Serializable{
    
    private Pedido pedido;
    private Adicional adicional;
    private int qtd;
    
    public PedidoAdicional() {
    }

    public Adicional getAdicional() {
        return adicional;
    }

    public void setAdicional(Adicional adicional) {
        this.adicional = adicional;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PedidoAdicional other = (PedidoAdicional) obj;
        if (this.pedido != other.pedido && (this.pedido == null || !this.pedido.equals(other.pedido))) {
            return false;
        }
        if (this.adicional != other.adicional && (this.adicional == null || !this.adicional.equals(other.adicional))) {
            return false;
        }
        if (this.qtd != other.qtd) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.pedido != null ? this.pedido.hashCode() : 0);
        hash = 61 * hash + (this.adicional != null ? this.adicional.hashCode() : 0);
        hash = 61 * hash + this.qtd;
        return hash;
    }

    @Override
    public String toString() {
        return "PedidoAdicional{" + "pedido=" + pedido + ", adicional=" + adicional + ", qtd=" + qtd + '}';
    }
    
}
