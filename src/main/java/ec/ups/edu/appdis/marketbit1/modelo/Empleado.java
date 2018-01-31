package ec.ups.edu.appdis.marketbit1.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Empleado {
	
	@Id
	@GeneratedValue
	@Column(name = "emp_codigo")
	private int codigo;
	
	@NotNull
	@NotEmpty
	@Column(name = "emp_cedula", length = 10)
	@Size(max = 10)
	private String cedula;
	
	@NotNull
	@NotEmpty
	@Column(name = "emp_nombre")
	@Size(min = 4, max = 20)
	private String nombre;
	
	@NotNull
	@NotEmpty
	@Column(name = "emp_usuario")
	@Size(min = 4, max = 20)
	private String usuario;
	
	@NotNull
	@NotEmpty
	@Column(name = "emp_contrasena")
	@Size(min = 4, max = 20)
	private String contrasena;
	
	@NotNull
	@NotEmpty
	@Column(name = "emp_cargo")
	@Size(min = 4, max = 20)
	private String cargo;
	
	@OneToMany(mappedBy="empleados")
	private List<Entrega> entrega;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	@Override
	public String toString() {
		return "Empleado [codigo=" + codigo + ", cedula=" + cedula + ", nombre=" + nombre + ", usuario=" + usuario
				+ ", contrasena=" + contrasena + ", cargo=" + cargo + "]";
	}
	
	
	
	

}
