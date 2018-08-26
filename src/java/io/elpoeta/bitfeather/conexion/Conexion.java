
package io.elpoeta.bitfeather.conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author elpoeta
 */
public class Conexion {
     private static Conexion INSTANCE = null;
    
     //private static String LABASE = "jdbc:hsqldb:file:db-data/demoposthsqldb";
     private static String LABASE = "jdbc:hsqldb:file:~/db/demopost";
 
     private static String LABASEUSUARIO = "SA";
     private static String LABASECLAVE = "";
//     private static String LABASE = "jdbc:mysql://localhost/db_postconfiltro";
//     private static String LABASEUSUARIO = "elpoeta";  // "root";
//     private static String LABASECLAVE = "elpoeta";    //"root";
/*
      URI dbUri = new URI(System.getenv("DATABASE_URL"));

    String username = dbUri.getUserInfo().split(":")[0];
    String password = dbUri.getUserInfo().split(":")[1];
    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

    return DriverManager.getConnection(dbUrl, username, password);*/
//	private static String LABASE = "jdbc:postgresql://ec2-50-16-196-138.compute-1.amazonaws.com:5432/d4hmaneoqqj6q3?sslmode=require";
//     private static String LABASEUSUARIO = "uimntzlstcfiym";  // "root";
//     private static String LABASECLAVE = "a38e326b12d6d581fef74256f0eb868b711d39726c96855b11a5ac181c5cd643";    //"root";
//     
	public static Conexion getInstance() throws ClassNotFoundException, IOException, SQLException {
         if (INSTANCE == null) {
             INSTANCE = new Conexion();
         }
         return INSTANCE;
     }
     private Conexion() throws ClassNotFoundException,
             IOException, SQLException {
     }

     public Connection getConnection() throws ClassNotFoundException,
             IOException, SQLException {
         Class.forName("org.hsqldb.jdbc.JDBCDriver");
         System.out.println("hsqldb >>"+DriverManager.getConnection(LABASE, LABASEUSUARIO, LABASECLAVE));
         return DriverManager.getConnection(LABASE, LABASEUSUARIO, LABASECLAVE);
     }
}
