package io.elpoeta.bitfeather.domain;

public class Categoria {
	
	private Integer id;
	private String nombre;
	private boolean isActiva;
	
	public Categoria() {
		
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

	public boolean isActiva() {
		return isActiva;
	}

	public void setActiva(boolean isActiva) {
		this.isActiva = isActiva;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", isActiva=" + isActiva + "]";
	}
	
	
	
	
}
