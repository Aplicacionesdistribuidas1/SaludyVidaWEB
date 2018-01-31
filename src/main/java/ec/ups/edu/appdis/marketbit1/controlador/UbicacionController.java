package ec.ups.edu.appdis.marketbit1.controlador;
import ec.ups.edu.appdis.marketbit1.modelo.Ubicacion;
import ec.ups.edu.appdis.marketbit1.datos.UbicacionDAO;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class UbicacionController {
	@Inject
	private UbicacionDAO ubidao;
	private Ubicacion ubicacion= null;
	private List<Ubicacion> ubicaciones;
	
	private int id;
	
	@PostConstruct
	public void init() {
		ubicacion = new Ubicacion();
		loadUbicacion();
	}

	public UbicacionDAO getUbidao() {
		return ubidao;
	}

	public void setUbidao(UbicacionDAO ubidao) {
		this.ubidao = ubidao;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		loadDatosEditar(id);
	}
	public void loadUbicacion() {
		ubicaciones = ubidao.listadoUbicacion();
	}
	public String guardar() {
		ubidao.guardar(ubicacion);
		loadUbicacion();
		return "ListadoUbicacion";
	}
	
	public String loadDatosEditar(int id) {
		System.out.println("cargando datos de ubicacion a editar"+ id);
		ubicacion= ubidao.leer(id);
		return "crearUbicacion";
	}
	
	public String eliminarDatos(int id) {
		ubidao.borrar(id);
		return "listadoUbicacion";
	}
}
