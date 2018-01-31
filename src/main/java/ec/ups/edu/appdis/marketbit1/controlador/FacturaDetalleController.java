package ec.ups.edu.appdis.marketbit1.controlador;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.ups.edu.appdis.marketbit1.datos.FacturaDetalleDAO;
import ec.ups.edu.appdis.marketbit1.modelo.FacturaDetalle;
import ec.ups.edu.appdis.marketbit1.modelo.Menu;
import ec.ups.edu.appdis.marketbit1.modelo.FacCabecera;


@ManagedBean
@ViewScoped
public class FacturaDetalleController {
	
	@Inject
	private FacturaDetalleDAO fddao;
	private FacturaDetalle facturaD = null;
	private List<FacturaDetalle> facturaDs;
	private List<Menu> m;
	ArrayList<Integer> obtener = new ArrayList<Integer>();
	ArrayList<Integer> separar;
	ArrayList<Integer> contar = new ArrayList<Integer>();
	
	public FacturaDetalleDAO getFddao() {
		return fddao;
	}

	public void setFddao(FacturaDetalleDAO fddao) {
		this.fddao = fddao;
	}

	private int id;
	
	@PostConstruct
	public void init() {
		facturaD = new FacturaDetalle();
		loadFacturaD();
	}

	public FacturaDetalle getFacturaD() {
		return facturaD;
	}

	public void setFacturaD(FacturaDetalle facturaD) {
		this.facturaD = facturaD;
	}

	public List<FacturaDetalle> getFacturaDs() {
		return facturaDs;
	}

	public void setFacturaDs(List<FacturaDetalle> facturaDs) {
		this.facturaDs = facturaDs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		loadDatosEditar(id);
	}
	
	public void loadFacturaD() {
		facturaDs = fddao.listadoFacturaDetalle();
	}
	
	public String guardar() {
		fddao.guardar(facturaD);
		loadFacturaD();
		return "ListadoFacturaD";
	}
	
	public String loadDatosEditar (int id) {
		System.out.println("cargando datos de ubicacion a editar"+ id);
		facturaD = fddao.leer(id);
		return "FicheroFacturaD";
	}
	
	public String eliminarDatos(int id) {
		fddao.borrar(id);
		return "ListadoFacturaD";
	}
	
	public void codigo(int nombre){
		int aux = nombre;
		obtener.add(aux);	
	}
	
	public void li(){
		for (int i = 0; i < obtener.size(); i++) {
			System.out.println(obtener.get(i));
		}
		Set<Integer> hs = new HashSet<>();
		hs.addAll(obtener);
		 separar = new ArrayList<Integer>();
		 //contar = new ArrayList<Integer>();
		separar.addAll(hs);
		System.out.println("separdor");
		for (int i = 0; i < separar.size(); i++) {
			System.out.println(separar.get(i));
		}
		System.out.println("contar");
		for (int j = 0; j < separar.size(); j++) {
		int aux = 0;
		for (int i = 0; i < obtener.size();i++) {
			if (obtener.get(i)== separar.get(j)) {
				aux++;
				
			}
		}
		contar.add(aux);
		Menu me = new Menu();
		//m.setCodigo(1);
		//for (int i = 0; i < separar.size(); i++) {
			me.setCodigo(1);
			facturaD.setMenu(me);
			facturaD.setCantidad(1);
			facturaD.setSubtotal(10.1);
			me.addMenu(facturaD);
			
		}
		
	}
	

	
}
