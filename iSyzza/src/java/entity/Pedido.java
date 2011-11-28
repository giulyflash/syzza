
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
    private Date data_pag;
    private Date data_pronta;
    private Date data_entrega;
    Cliente cliente;
    private int pago;
    
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

    public Date getData_pag() {
        return data_pag;
    }

    public void setData_pag(Date data_pag) {
        this.data_pag = data_pag;
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

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
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
        if (this.data_pag != other.data_pag && (this.data_pag == null || !this.data_pag.equals(other.data_pag))) {
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
        if (this.pago != other.pago) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        hash = 79 * hash + (this.data_pedido != null ? this.data_pedido.hashCode() : 0);
        hash = 79 * hash + (this.data_pag != null ? this.data_pag.hashCode() : 0);
        hash = 79 * hash + (this.data_pronta != null ? this.data_pronta.hashCode() : 0);
        hash = 79 * hash + (this.data_entrega != null ? this.data_entrega.hashCode() : 0);
        hash = 79 * hash + (this.cliente != null ? this.cliente.hashCode() : 0);
        hash = 79 * hash + this.pago;
        return hash;
    }
    
}
