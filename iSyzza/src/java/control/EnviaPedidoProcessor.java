/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import javax.servlet.ServletException;

/**
 *
 * @author Familia
 */
public class EnviaPedidoProcessor extends Processor {

    @Override
    public void execute() throws ServletException, IOException {
        forward("confirmaPedido.jsp");
    }
    
}
