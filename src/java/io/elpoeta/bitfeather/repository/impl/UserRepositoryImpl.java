
package io.elpoeta.bitfeather.repository.impl;


import io.elpoeta.bitfeather.conexion.Conexion;
import io.elpoeta.bitfeather.domain.User;
import io.elpoeta.bitfeather.repository.CrudRepository;
import java.util.List;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author elpoeta
 */
public class UserRepositoryImpl implements CrudRepository<User, Integer>{
    private static UserRepositoryImpl INSTANCE = null;
    private final static String SQL_USERS_SELECT = "SELECT * FROM user;";
    private final static String SQL_USER_SELECT = "SELECT * FROM user WHERE id = ?;";
    private final static String SQL_USER_FIND_BY_EMAIL = "SELECT * FROM user WHERE email = ?;";
    private final static String SQL_USER_INSERT = "INSERT INTO user (nombre, email, password)values(?,?,?);";
    
    private UserRepositoryImpl() throws ClassNotFoundException,
    IOException, SQLException {
}
    public static UserRepositoryImpl getInstance() throws ClassNotFoundException,
	IOException, SQLException {
		if (INSTANCE == null) {
			INSTANCE = new UserRepositoryImpl();
		}
		return INSTANCE;
	}
    @Override
    public User buscarPorId(Integer id) throws ClassNotFoundException, IOException, SQLException {
         User user = null;
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
		try {
		conexion = Conexion.getInstance().getConnection();
		ptsmt = conexion.prepareStatement(SQL_USER_SELECT);
		ptsmt.setInt(1, id);
		rs = ptsmt.executeQuery();
		
		if(rs.next()) {
		    try {
		        user = new User();
		        user.setId(rs.getInt("id"));
		        user.setNombre(rs.getString("nombre"));
                        user.setEmail(rs.getString("email"));
                        user.setPassword(rs.getString("password"));
                        user.setIs_activo(rs.getBoolean("is_activo"));
		     
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		}
		} finally {
		try {
		    rs.close();
		} finally {
		    try {
		        ptsmt.close();
		    } finally {
		        conexion.close();
		    }
		}
		}
		return user;

    }

    @Override
    public List<User> buscarTodos() throws ClassNotFoundException, IOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertar(User user) throws ClassNotFoundException, IOException, SQLException {
         Connection c = null;
		   PreparedStatement ptsmt = null;
		   try {
		       c = Conexion.getInstance().getConnection();
		       ptsmt = c.prepareStatement(SQL_USER_INSERT);
                       ptsmt.setString(1, user.getNombre());
		       ptsmt.setString(2,  user.getEmail());
                       String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
                       System.out.println("cryt > "+hashed);
		       ptsmt.setString(3, hashed);
		       ptsmt.execute();
		   } finally {
		       try {
		           ptsmt.close();
		       } finally {
		           c.close();
		       }
		   }
    }

    @Override
    public void modificar(User obj) throws ClassNotFoundException, IOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(Integer id) throws ClassNotFoundException, IOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
     public User buscarPorEmail(String email) throws ClassNotFoundException, IOException, SQLException {
         User user = null;
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
		try {
		conexion = Conexion.getInstance().getConnection();
		ptsmt = conexion.prepareStatement(SQL_USER_FIND_BY_EMAIL);
		ptsmt.setString(1, email);
		rs = ptsmt.executeQuery();
		
		if(rs.next()) {
		    try {
                      user = new User();
		      user.setId(rs.getInt("id"));
		      user.setNombre(rs.getString("nombre"));
                      user.setEmail(rs.getString("email"));
                      user.setPassword(rs.getString("password"));
                      user.setIs_activo(rs.getBoolean("is_activo"));
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		}
		} finally {
		try {
		    rs.close();
		} finally {
		    try {
		        ptsmt.close();
		    } finally {
		        conexion.close();
		    }
		}
		}
		return user;

    }
}
