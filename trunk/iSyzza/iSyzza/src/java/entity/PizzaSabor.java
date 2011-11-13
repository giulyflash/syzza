
package entity;

import java.io.Serializable;

/**
 *
 * @author Jonathan
 */
public class PizzaSabor implements Serializable{
    
    private Pizza pizza;
    private Sabor sabor;

    public PizzaSabor() {
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Sabor getSabor() {
        return sabor;
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PizzaSabor other = (PizzaSabor) obj;
        if (this.pizza != other.pizza && (this.pizza == null || !this.pizza.equals(other.pizza))) {
            return false;
        }
        if (this.sabor != other.sabor && (this.sabor == null || !this.sabor.equals(other.sabor))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.pizza != null ? this.pizza.hashCode() : 0);
        hash = 83 * hash + (this.sabor != null ? this.sabor.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "PizzaSabor{" + "pizza=" + pizza + ", sabor=" + sabor + '}';
    }
    
}
