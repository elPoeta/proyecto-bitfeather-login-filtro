package io.elpoeta.bitfeather.domain;

import java.util.List;


public class User {
	private Integer id;
	private String nombre;
        private String email;
        private String password;
        private String confirmPassword;
        private boolean is_activo;
	private List<Post> posts;
	
	public User() {
	
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
      
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        
        this.password = password;
    }

    public boolean isIs_activo() {
        return is_activo;
    }

    public void setIs_activo(boolean is_activo) {
        this.is_activo = is_activo;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
       
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "USER{" + "id=" + id + ", nombre=" + nombre + ", email=" + email + ", password=" + password + ", is_activo=" + is_activo + ", posts=" + posts + '}';
    }
	
}
