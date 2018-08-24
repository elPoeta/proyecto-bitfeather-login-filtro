
package io.elpoeta.bitfeather.web.filtro;


import com.google.gson.Gson;
import io.elpoeta.bitfeather.domain.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author elpoeta
 */
@WebFilter(filterName = "CrearPostFiltro", urlPatterns = {"/api/privado/*"})
public class CrearPostFiltro implements Filter {
    private final Gson CONVERTIR = new Gson();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
     
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("... POSTFiltra ...............................");
        User userActual = (User)((HttpServletRequest)request).getSession().getAttribute("User");
        if ( userActual !=null ) { 
            System.out.println("...OK PrivadoFiltra ............................... " + userActual);
            chain.doFilter(request, response);
            
        } else {
            System.out.println("...UPA PrivadoFiltra ..............................." + userActual);
            response.getWriter().print(CONVERTIR.toJson("error"));
        }
    }

    @Override
    public void destroy() {
        
    }
    
    
}
