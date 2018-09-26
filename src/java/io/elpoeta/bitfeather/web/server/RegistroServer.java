
package io.elpoeta.bitfeather.web.server;


import io.elpoeta.bitfeather.domain.User;
import io.elpoeta.bitfeather.repository.impl.UserRepositoryImpl;
import io.elpoeta.bitfeather.util.GsonUtil;
import io.elpoeta.bitfeather.util.ValidarEmail;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elpoeta
 */
@WebServlet(name = "RegistroServer", urlPatterns = {"/RegistroServer"})
public class RegistroServer extends HttpServlet {
    
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
          System.out.println("registrserver");
        try {
        	
            String texto = request.getReader().readLine();

            User userParametro = GsonUtil.CONVERTIR.fromJson(texto, User.class);
            System.out.println(userParametro);
            User userBD = UserRepositoryImpl.getInstance().buscarPorEmail(userParametro.getEmail());  
            System.out.println(userBD);
            if(validarUsuario(userParametro)){
                        if( userBD == null && userParametro.getPassword().equals(userParametro.getConfirmPassword()))
                      {
                        UserRepositoryImpl.getInstance().insertar(userParametro);
                         out.println(GsonUtil.CONVERTIR.toJson("OK"));
                       }else{
                            System.out.println("Error");
                            out.println(GsonUtil.CONVERTIR.toJson("error"));
                        }
                      }else{
                              out.println(GsonUtil.CONVERTIR.toJson("error"));
                              }
            

        } catch (ClassNotFoundException ex) {
            out.println("Verificar: " + ex.getMessage());
            System.out.println("Class > "+ex.getMessage());
        } catch (SQLException ex) {
            out.println("Verificar:" + ex.getMessage());
            System.out.println("SQL > "+ex.getMessage());
        } catch (Exception ex) {
            out.println("Verificar:" + ex.getMessage());
            System.out.println("EXcep > "+ex.getMessage());
        } finally {
            out.close();
        }
    }
    
    
  private boolean validarUsuario(User u){
    if(u.getNombre() != "" && u.getNombre() !=null 
       && ValidarEmail.validate(u.getEmail()) 
       && u.getPassword() != "" && u.getPassword() != null 
       && u.getConfirmPassword() != "" && u.getConfirmPassword() !=null){
        return true;
    }
      
      return false;
}

}
