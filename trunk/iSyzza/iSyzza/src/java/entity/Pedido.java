
package entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Jonathan
 */
public class Pedido implements Serializable{
    
    private int id;
    private Date data_pedido;
    private Date data_pronta;
    private Date data_entrega;
    Cliente cliente;

    public Pedido() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(Date data_entrega) {
        this.data_entrega = data_entrega;
    }

    public Date getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(Date data_pedido) {
        this.data_pedido = data_pedido;
    }

    public Date getData_pronta() {
        return data_pronta;
    }

    public void setData_pronta(Date data_pronta) {
        this.data_pronta = data_pronta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.data_pedido != other.data_pedido && (this.data_pedido == null || !this.data_pedido.equals(other.data_pedido))) {
            return false;
        }
        if (this.data_pronta != other.data_pronta && (this.data_pronta == null || !this.data_pronta.equals(other.data_pronta))) {
            return false;
        }
        if (this.data_entrega != other.data_entrega && (this.data_entrega == null || !this.data_entrega.equals(other.data_entrega))) {
            return false;
        }
        if (this.cliente != other.cliente && (this.cliente == null || !this.cliente.equals(other.cliente))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.id;
        hash = 83 * hash + (this.data_pedido != null ? this.data_pedido.hashCode() : 0);
        hash = 83 * hash + (this.data_pronta != null ? this.data_pronta.hashCode() : 0);
        hash = 83 * hash + (this.data_entrega != null ? this.data_entrega.hashCode() : 0);
        hash = 83 * hash + (this.cliente != null ? this.cliente.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", data_pedido=" + data_pedido + ", data_pronta=" + data_pronta + ", data_entrega=" + data_entrega + ", cliente=" + cliente + '}';
    }
    
}
