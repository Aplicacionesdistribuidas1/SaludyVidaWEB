package ec.ups.edu.appdis.marketbit1.controlador;
import ec.ups.edu.appdis.marketbit1.datos.EntregaDAO;
import ec.ups.edu.appdis.marketbit1.modelo.Entrega;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean
public class EntregaController {

	@Inject
	private EntregaDAO entdao;
	private Entrega entrega= null;
	private List<Entrega> entregas;
	
	private int id;
	
	@PostConstruct
	public void init() {
		entrega =new Entrega();
		loadEntrega();
	}

	public EntregaDAO getEntdao() {
		return entdao;
	}

	public void setEntdao(EntregaDAO entdao) {
		this.entdao = entdao;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public List<Entrega> getEntregas() {
		return entregas;
	}

	public void setEntregas(List<Entrega> entregas) {
		this.entregas = entregas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		loadDatosEditar(id);
	}
	
	public void loadEntrega() {
		entregas=entdao.listadoEntrega();
	}
	
	public String guardar() {
		entdao.guardar(entrega);
		loadEntrega();
		return "ListadoEntrega.xhtml";
	}
	
	public String loadDatosEditar(int id) {
		System.out.println("cargando datos de Entrega a editar"+ id);
		entrega= entdao.leer(id);
		return "crearEntrega";
	}
	public String eliminarDatos(int id) {
		entdao.borrar(id);
		return "listadoEntrega";
	}
}
