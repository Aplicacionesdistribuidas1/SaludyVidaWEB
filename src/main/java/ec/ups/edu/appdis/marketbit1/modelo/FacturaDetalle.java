package ec.ups.edu.appdis.marketbit1.modelo;

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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="facturadetalle")
public class FacturaDetalle {
	@Id
		@GeneratedValue
		@Column(name="fd_codigo")
		private int codigo;
		
		@NotNull
		@Column(name="fd_cantidad")
		private int cantidad;
		
		@NotNull
		@Column(name="fd_subtotal")
		private double subtotal;
		
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name="menuid", nullable=false)
		@JsonIgnore
		private Menu menu;
	/*
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name="fdetalleid", nullable=false)
		@JsonIgnore
		private Entrega entregas;
		*/
		/*
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name="cabeceraid", nullable=false)
		@JsonIgnore
		private FacCabecera cabeceras;
*/
		public int getCodigo() {
			return codigo;
		}

		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}

		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}

		public double getSubtotal() {
			return subtotal;
		}

		public void setSubtotal(double subtotal) {
			this.subtotal = subtotal;
		}
		
		public Menu getMenu() {
			return menu;
		}

		public void setMenu(Menu menu) {
			this.menu = menu;
		}
		/*
		public FacCabecera getCabeceras() {
			return cabeceras;
		}

		public void setCabeceras(FacCabecera cabeceras) {
			this.cabeceras = cabeceras;
		}
*/


/*
		public Entrega getEntregas() {
			return entregas;
		}

		public void setEntregas(Entrega entregas) {
			this.entregas = entregas;
		}*/

		@Override
		public String toString() {
			return "FacturaDetalle [codigo=" + codigo + ", cantidad=" + cantidad + ", subtotal=" + subtotal + ", menu="
					+ menu +  "]";
		}

		

	
		

		
}

