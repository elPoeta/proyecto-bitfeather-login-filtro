
package io.elpoeta.bitfeather.web.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.elpoeta.bitfeather.domain.Post;
import io.elpoeta.bitfeather.domain.adapter.LocalDateTimeAdapter;
import io.elpoeta.bitfeather.repository.impl.PostRepositoryImpl;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elpoeta
 */
@WebServlet(name = "PostServer", urlPatterns = {"/api/postServer"})
public class PostServer extends HttpServlet {

  //final static Gson CONVERTIR = new Gson();
  private final static Gson CONVERTIR = new GsonBuilder()
                                                .setPrettyPrinting()
                                                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                                                .create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   	
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        Integer parametro = CONVERTIR.fromJson(request.getParameter("q"), Integer.class);
 
          if(parametro == 0){
                List<Post> listado =  PostRepositoryImpl.getInstance().buscarTodos();
	             String resultado = CONVERTIR.toJson(listado); //gson.toJson(listado); //
	             out.println("" + resultado);
          }else{
            Post post = PostRepositoryImpl.getInstance().buscarPorId(parametro);
            String resultado = CONVERTIR.toJson(post); //gson.toJson(post); //
            out.println("" + resultado);
          }
        } catch (ClassNotFoundException ex) {
            out.println("Verificar:" + ex.getMessage());
        } catch (SQLException ex) {
            out.println("Verificar:" + ex.getMessage());
        } catch (Exception ex) {
            out.println("Verificar:" + ex.getMessage());
        } finally {
            out.close();
        }
    }

	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
//        	System.out.println("<<POST-INS>>");
//            String texto = request.getReader().readLine();
//
//            Post postParametro = CONVERTIR.fromJson(texto, Post.class);
//            System.out.println(postParametro);
//
//            PostRepositoryImpl.getInstance().insertar(postParametro);
//            out.println(CONVERTIR.toJson("OK"));
//
//        } catch (ClassNotFoundException ex) {
//            out.println("Verificar: " + ex.getMessage());
//            System.out.println("Class > "+ex.getMessage());
//        } catch (SQLException ex) {
//            out.println("Verificar:" + ex.getMessage());
//            System.out.println("SQL > "+ex.getMessage());
//        } catch (Exception ex) {
//            out.println("Verificar:" + ex.getMessage());
//            System.out.println("EXcep > "+ex.getMessage());
//        } finally {
//            out.close();
//        }
    }
}
