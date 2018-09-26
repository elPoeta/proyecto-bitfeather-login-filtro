
package io.elpoeta.bitfeather.web.server;

import com.google.gson.Gson;
import io.elpoeta.bitfeather.domain.User;
import io.elpoeta.bitfeather.repository.impl.UserRepositoryImpl;
import io.elpoeta.bitfeather.util.GsonUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author elpoeta
 */
@WebServlet(name = "LoginServer", urlPatterns = {"/LoginServer"})
public class LoginServer extends HttpServlet {
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User usuario = (User) request.getSession().getAttribute("user");
        if( usuario != null ){
            
            response.getWriter().print(GsonUtil.CONVERTIR.toJson(usuario));
        }else{
            response.getWriter().print(GsonUtil.CONVERTIR.toJson("error"));
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	
            String texto = request.getReader().readLine();
            
            User userParametro = GsonUtil.CONVERTIR.fromJson(texto, User.class);
            System.out.println("PaR > "+userParametro);
            User userBD = UserRepositoryImpl.getInstance().buscarPorEmail(userParametro.getEmail()); 
            System.out.println("BD > "+userBD);
        
            if(userBD.getEmail().equals(userParametro.getEmail()) && BCrypt.checkpw(userParametro.getPassword(), userBD.getPassword()) && userBD.isIs_activo())
            {   
                request.getSession().setAttribute("user", userBD);
               System.out.println("...  logIN ..." + request.getSession().getAttribute("user") );
               out.print(GsonUtil.CONVERTIR.toJson("ok"));
             //out.println(CONVERTIR.toJson(autorBD));
               
            }else{
                request.getSession().removeAttribute("user");
               System.out.println("...  logOUT ..." + request.getSession().getAttribute("user") );
                out.print( GsonUtil.CONVERTIR.toJson( "error"));
                //out.println(CONVERTIR.toJson("error"));
            
            }

        } catch (ClassNotFoundException ex) {
            out.println("Verificar: " + ex.getMessage());
            System.out.println("Class > "+ex.getMessage());
        } catch (SQLException ex) {
            out.println("Verificar:" + ex.getMessage());
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            out.println(ex.getMessage());
            System.out.println("EXcep > "+ex.getMessage());
        } finally {
            out.close();
        }
        
      
    }


}
