package ec.ups.edu.appdis.marketbit1.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ubicacion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int codigo;
	
	@NotEmpty
	@Column(name="ub_direccion", length=50)
	private String direccion;
	

	@Column(name="ub_sector")
	private String sector;
	
	@NotNull
	@Column(name="ub_vectorx")
	private double vectorx;
	
	@NotNull
	@Column(name="ub_vectory")
	private double vectory;

	/*@OneToOne(mappedBy="ubicaciones", cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false)
	private Entrega entregas;*/
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="clienteid", nullable=false)
	@JsonIgnore
	private Cliente clientes;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public double getVectorx() {
		return vectorx;
	}
	public void setVectorx(double vectorx) {
		this.vectorx = vectorx;
	}
	public double getVectory() {
		return vectory;
	}
	public void setVectory(double vectory) {
		this.vectory = vectory;
	}
	
	
	
	
	
	
}
