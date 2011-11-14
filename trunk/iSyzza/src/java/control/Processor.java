
package control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jonathan
 */
public abstract class Processor {
    private ServletContext context;  
  
    private HttpServletRequest request;  
  
    private HttpServletResponse response;  
  
  
    public abstract void execute() throws ServletException, IOException ;  
  
    public void forward(String forward) throws ServletException, IOException{  
            RequestDispatcher rd = getRequest().getRequestDispatcher(forward);  
            rd.forward(getRequest(), getResponse());   
    }

    public ServletContext getContext() {
        return context;
    }

    public void setContext(ServletContext context) {
        this.context = context;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    
}
