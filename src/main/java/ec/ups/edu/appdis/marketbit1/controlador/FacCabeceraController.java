package ec.ups.edu.appdis.marketbit1.controlador;


import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.ups.edu.appdis.marketbit1.datos.FacCabeceraDAO;
import ec.ups.edu.appdis.marketbit1.modelo.FacturaDetalle;
import ec.ups.edu.appdis.marketbit1.modelo.FacCabecera;

@ManagedBean
@ViewScoped
public class FacCabeceraController {
	
	@Inject
	private FacCabeceraDAO fcdao;
	
	private FacCabecera fac_cabecera = null;
	private List<FacCabecera> fac_cabeceras;
	
	private int id;
	
	@PostConstruct
	public void init() {
		fac_cabecera = new FacCabecera();
		fac_cabecera.addDetalle(new FacturaDetalle());
		loadfac_cabeceras();
	}

	public FacCabecera getFac_cabecera() {
		return fac_cabecera;
	}

	public void setFac_cabecera(FacCabecera fac_cabecera) {
		this.fac_cabecera = fac_cabecera;
	}
	
	public void loadfac_cabeceras() {
		fac_cabeceras= fcdao.listadofacCabecera();
	}
	
	public String guardar() {
		fcdao.guardar(fac_cabecera);
		loadfac_cabeceras() ;
		return"ListadoFacCabecera";
	}

	public List<FacCabecera> getFac_cabeceras() {
		return fac_cabeceras;
	}

	public void setFac_cabeceras(List<FacCabecera> fac_cabeceras) {
		this.fac_cabeceras = fac_cabeceras;
	}
	
	public String loadDatosEditar(int id) {
		System.out.println("Cargar datos de la factura para editar"+id);
		fac_cabecera = fcdao.leer(id);
		return"FicheroFacCa";
	}
	
	public String eliminarDatos(int id) {
		fcdao.borrar(id);
		return"listadoFacCabe";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		loadDatosEditar(id);
	}
	public String addDetalle() {
		fac_cabecera.addDetalle(new FacturaDetalle());
		return null;
	}
	
	

}
