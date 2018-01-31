package ec.ups.edu.appdis.marketbit1.modelo;

import java.text.DecimalFormat;

public class MenuTemp {
	private Integer menu;
	private String nombre;
	private String tipo;
	private String imagen;
	private String ingredientes;
	private double precio;
	private int cantidad;
	public Integer getMenu() {
		return menu;
	}
	public void setMenu(Integer menu) {
		this.menu = menu;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	@Override
	public String toString() {
		return "MenuTemp [menu=" + menu + ", nombre=" + nombre + ", tipo=" + tipo + ", imagen=" + imagen
				+ ", ingredientes=" + ingredientes + ", precio=" + precio + ", cantidad=" + cantidad + "]";
	}

	
	
	
	
}
