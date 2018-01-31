package ec.ups.edu.appdis.marketbit1.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="cli_codigo", length=10)
	private int codigo;
	
	@NotNull
	@NotEmpty
	@Column(name="cli_cedula", length=10)
	@Size(max=10)
	private String cedula;
	
	@NotNull
	@NotEmpty
	@Column(name="cli_usuario", length=10)
	@Size(min=4,max=10)
	private String usuario;
	
	@NotNull
	@NotEmpty
	@Column(name="cli_contrasena", length=10)
	@Size(min=5,max=10)
	private String contrasena;
	
	@NotEmpty
	@Column(name="cli_nombres")
	@Size(min=4,max=20)
	private String nombres;
	
	@NotEmpty
	@Email
	@Column(name="cli_email")
	private String email;
	
	@Column(name="cli_fecNaci")
	private String fechaNacimiento;
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="cli_cod", referencedColumnName="cli_codigo")
	private List<Ubicacion> ubicaciones;
		
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

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public void addUbicacion(Ubicacion ubicacion) {
		if(ubicaciones==null)
			ubicaciones=new ArrayList<Ubicacion>();
			ubicaciones.add(ubicacion);
	}
	/*
	public Ubicacion addUbicacion(Ubicacion ubicacion) {
		getUbicaciones().add(ubicacion);
		ubicacion.setCliente(this);

		return ubicacion;
	}
*/
	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", cedula=" + cedula + ", usuario=" + usuario + ", contrasena="
				+ contrasena + ", nombres=" + nombres + ", email=" + email + ", fechaNacimiento=" + fechaNacimiento
				+ ", ubicaciones=" + ubicaciones + "]";
	}
	

	

	


	
}
