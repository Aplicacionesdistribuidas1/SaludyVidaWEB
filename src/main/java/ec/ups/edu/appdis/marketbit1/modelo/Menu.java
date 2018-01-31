package ec.ups.edu.appdis.marketbit1.modelo;

import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="menus")
public class Menu {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="menu_codigo", length=10)
	private int codigo;
	
	@NotNull
	@NotEmpty
	@Column(name="menu_nombre")
	@Size(min=4,max=100)
	private String nombre;
	
	@NotNull
	@NotEmpty
	@Column(name="menu_tipo")
	private String tipo;
	
	@NotNull
	@NotEmpty
	@Column(name="menu_imagen")
	@Size(min=4,max=10)
	private String imagen;
	
	@NotNull
	@NotEmpty
	@Column(name="menuc_ingredientes", length=100)
	@Size(min=4,max=100)
	private String ingredientes;
	
	@NotNull
	@Column(name="menu_precio")
	private double precio;
	
	@NotNull
	
	@Column(name="menu_cantidad")
	private int cantidad;
	
	@OneToMany(mappedBy="menu")
	
	private List<FacturaDetalle> detalles;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
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


	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public List<FacturaDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<FacturaDetalle> detalles) {
		this.detalles = detalles;
	}

	
	public FacturaDetalle addMenu(FacturaDetalle detalle) {
		getDetalles().add(detalle);
		detalle.setMenu(this);
		return detalle;
	}
	
	@Override
	public String toString() {
		return "Menu [codigo=" + codigo + ", nombre=" + nombre + ", tipo=" + tipo + ", imagen=" + imagen
				+ ", ingredientes=" + ingredientes + ", precio=" + precio + "]";
	}
	
	



	





	

	

	
}
