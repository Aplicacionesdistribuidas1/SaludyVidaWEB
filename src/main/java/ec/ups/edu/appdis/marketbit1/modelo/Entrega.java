package ec.ups.edu.appdis.marketbit1.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Entrega {
	@Id
	@GeneratedValue
	@Column(name="ent_codigo", length=10)
	private int codigo;
	
	@NotNull
	@Column(name="ent_costo_envio",length=10)
	private double costoEnvio;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente_id", nullable=false)
	@JsonIgnore
	private Cliente cliente;
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cabeceraid", nullable=false)
	@JsonIgnore
	private FacCabecera cabeceras1;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="empleadoid", nullable=false)
	@JsonIgnore
	private Empleado empleados;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public double getCostoEnvio() {
		return costoEnvio;
	}
	public void setCostoEnvio(double costoEnvio) {
		this.costoEnvio = costoEnvio;
	}
	
	public FacCabecera getCabeceras1() {
		return cabeceras1;
	}
	public void setCabeceras1(FacCabecera cabeceras1) {
		this.cabeceras1 = cabeceras1;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		return "Entrega [codigo=" + codigo + ", costoEnvio=" + costoEnvio + ", cliente=" + cliente + ", cabeceras1="
				+ cabeceras1 + "]";
	}
	



	

	
	
	

}
