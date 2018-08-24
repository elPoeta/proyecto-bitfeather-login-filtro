
package io.elpoeta.bitfeather.repository.impl;


import io.elpoeta.bitfeather.conexion.Conexion;
import io.elpoeta.bitfeather.domain.Categoria;
import io.elpoeta.bitfeather.domain.Post;
import io.elpoeta.bitfeather.domain.User;
import io.elpoeta.bitfeather.repository.CrudRepository;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elpoeta
 */
public class PostRepositoryImpl implements CrudRepository<Post, Integer>{
    private static PostRepositoryImpl INSTANCE = null;
    private final static String SQL_POSTS_SELECT = "SELECT * FROM post ORDER BY fecha_creacion DESC;";
    private final static String SQL_POST_SELECT = "SELECT * FROM post WHERE id = ?;";
    private final static String SQL_POST_INSERT = "INSERT INTO post (titulo, sub_titulo, cuerpo, fecha_creacion, categoria_id, user_id)values(?,?,?,?,?,?);";
    
    private PostRepositoryImpl() throws ClassNotFoundException,
    IOException, SQLException {
}
    public static PostRepositoryImpl getInstance() throws ClassNotFoundException,
	IOException, SQLException {
		if (INSTANCE == null) {
			INSTANCE = new PostRepositoryImpl();
		}
		return INSTANCE;
	}

    @Override
    public Post buscarPorId(Integer id) throws ClassNotFoundException, IOException, SQLException {
      Post post = null;
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
		try {
		conexion = Conexion.getInstance().getConnection();
		ptsmt = conexion.prepareStatement(SQL_POST_SELECT);
		ptsmt.setInt(1, id);
		rs = ptsmt.executeQuery();
		
		if(rs.next()) {
		    try {
		        post = new Post();
		        post.setId(rs.getInt("id"));
		        post.setTitulo(rs.getString("titulo"));
		        post.setSubTitulo(rs.getString("sub_titulo"));
		        post.setCuerpo(rs.getString("cuerpo"));
                        post.setFechaCreacion(rs.getObject("fecha_creacion", LocalDateTime.class));
		        Categoria cat = CategoriaRepositoryImpl.getInstance().buscarPorId(rs.getInt("categoria_id"));
		        post.setCategoria(cat);
                        User user = UserRepositoryImpl.getInstance().buscarPorId(rs.getInt("user_id"));
			post.setUser(user);

		 
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
		return post;
    }

    @Override
    public List<Post> buscarTodos() throws ClassNotFoundException, IOException, SQLException {
     
        ArrayList<Post> posts = new ArrayList();
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
		try {
			conexion = Conexion.getInstance().getConnection();
			ptsmt = conexion.prepareStatement(SQL_POSTS_SELECT);
			rs = ptsmt.executeQuery();
			Post post = null;
			while (rs.next()) {
			    try {
			        post = new Post();
			        post.setId(rs.getInt("id"));
			        post.setTitulo(rs.getString("titulo"));
			        post.setSubTitulo(rs.getString("sub_titulo"));
			        post.setCuerpo(rs.getString("cuerpo"));
                                post.setFechaCreacion(rs.getObject("fecha_creacion", LocalDateTime.class));
			        Categoria cat = CategoriaRepositoryImpl.getInstance().buscarPorId(rs.getInt("categoria_id"));
			        post.setCategoria(cat);
                                User user = UserRepositoryImpl.getInstance().buscarPorId(rs.getInt("user_id"));
			        post.setUser(user);
			 
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
			    posts.add(post);
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
		
		return posts;
    }

    @Override
    public void insertar(Post post) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
		   PreparedStatement ptsmt = null;
		   try {
		       c = Conexion.getInstance().getConnection();
		       ptsmt = c.prepareStatement(SQL_POST_INSERT);
		       ptsmt.setString(1, post.getTitulo());
		       ptsmt.setString(2, post.getSubTitulo());
		       ptsmt.setString(3, post.getCuerpo());
                       ptsmt.setObject(4, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
		       ptsmt.setInt(5, post.getCategoria().getId());
		       ptsmt.setInt(6, post.getUser().getId());
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
    public void modificar(Post post) throws ClassNotFoundException, IOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(Integer id) throws ClassNotFoundException, IOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
