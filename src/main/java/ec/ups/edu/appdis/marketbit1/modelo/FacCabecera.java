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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class FacCabecera {

@Id
@GeneratedValue
@Column(name="facC_codigo", length=10)
private int codigo;

@NotNull
@Column(name="facC_numero_factura")
/*@Size(max=20)*/
private int numero_factura;
	
@Temporal(value = TemporalType.DATE)
@Column(name="facC_fecEmision")
private Date fechaEmision;

@NotNull
@Column(name="facC_subtotal")
/*@Size(max=20)*/
private double subtotal;

@NotNull
@Column(name="facC_iva")
/*@Size(max=20)*/
private double iva;

@NotNull
@Column(name="facC_total")
/*@Size(max=20)*/
private double total;

@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="clienteid",nullable=false)
@JsonIgnore
private Cliente cliente;

@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.EAGER)
@JoinColumn(name="codigofacC", referencedColumnName="facC_codigo")
private List<FacturaDetalle> detalles;
/*
@OneToOne(mappedBy="cabeceras1", cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false)
private Entrega entregas;
*/
public int getCodigo() {
	return codigo;
}

public void setCodigo(int codigo) {
	this.codigo = codigo;
}

public int getNumero_factura() {
	return numero_factura;
}

public void setNumero_factura(int numero_factura) {
	this.numero_factura = numero_factura;
}

public Date getFechaEmision() {
	return fechaEmision;
}

public void setFechaEmision(Date fechaEmision) {
	this.fechaEmision = fechaEmision;
}

public List<FacturaDetalle> getDetalles() {
	return detalles;
}

public void setDetalles(List<FacturaDetalle> detalles) {
	this.detalles = detalles;
}


public Cliente getCliente() {
	return cliente;
}

public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}
/*
public Entrega getEntregas() {
	return entregas;
}

public void setEntregas(Entrega entregas) {
	this.entregas = entregas;
}
*/


public void addDetalle(FacturaDetalle detalle) {
	if(detalles==null)
		detalles=new ArrayList<>();
	detalles.add(detalle);
}



public double getSubtotal() {
	return subtotal;
}

public void setSubtotal(double subtotal) {
	this.subtotal = subtotal;
}

public double getIva() {
	return iva;
}

public void setIva(double iva) {
	this.iva = iva;
}

public double getTotal() {
	return total;
}

public void setTotal(double total) {
	this.total = total;
}

@Override
public String toString() {
	return "FacCabecera [codigo=" + codigo + ", numero_factura=" + numero_factura + ", fechaEmision=" + fechaEmision
			+ ", subtotal=" + subtotal + ", iva=" + iva + ", total=" + total + ", cliente=" + cliente + ", detalles="
			+ detalles +  "]";
}


}
		



