package io.elpoeta.bitfeather.domain;

import java.time.LocalDateTime;
import java.util.Set;


public class Post {
	private Integer id;
	private String titulo;
	private String subTitulo;
	private String cuerpo;
	private LocalDateTime fechaCreacion;
	private Categoria categoria;
	private User user;
        private Set<String> tags;

	public Post() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		
		return titulo;
	}

	public void setTitulo(String titulo) {
		if(titulo == "" || titulo == null) {
			throw new IllegalArgumentException("El titulo esta vacio");
		}
		this.titulo = titulo;
	}

	public String getSubTitulo() {
		
		return subTitulo;
	}

	public void setSubTitulo(String subTitulo) {
		
		if(subTitulo == "" || subTitulo == null) {
			throw new IllegalArgumentException("El subtitulo esta vacio");
		}
		this.subTitulo = subTitulo;
	}
	

	public String getCuerpo() {
	
		return this.cuerpo;
	}


	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	
	public void setCuerpo(String cuerpo) {
		if(cuerpo == "" || cuerpo == null) {
			throw new IllegalArgumentException("El cuerpo esta vacio");
		}
		this.cuerpo = cuerpo;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Categoria getCategoria() {
	
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		if(categoria.getId() == null) {
			throw new IllegalArgumentException("El id categoria esta vacio");
		}
		this.categoria = categoria;
	}

	public User getUser() {
		
		return user;
	}
	

	public void setUser(User user) {
		if(user.getId() == null) {
			throw new IllegalArgumentException("El id del autor esta vacio");
		}
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", titulo=" + titulo + ", subTitulo=" + subTitulo + ", cuerpo=" + cuerpo
				+ ", fechaCreacion=" + fechaCreacion + ", categoria=" + categoria + ", user=" + user + ", tags="
				+ tags + "]";
	}

	
	
	
	
	
	
}
