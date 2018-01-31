package ec.ups.edu.appdis.marketbit1.controlador;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

import ec.ups.edu.appdis.marketbit1.datos.menuDAO;
import ec.ups.edu.appdis.marketbit1.modelo.Menu;

@ManagedBean
@SessionScoped
public class MenuController {
	
	@Inject
	private menuDAO mendao;
	private Menu menu= null;
	private List<Menu> menus;
	private ArrayList<Menu> menus1 = new ArrayList<>();
	private int id;
	private List<Menu> dato;
	private int idMenuABuscar=0;
	
	@PostConstruct
	public void init() {
		menu = new Menu();
		loadMenus();
		doListarTodosMenus();
	}

	public menuDAO getMendao() {
		return mendao;
	}

	public void setMendao(menuDAO mendao) {
		this.mendao = mendao;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		loadDatosEditar(id);
	}

	public List<Menu> getDato() {
		return dato;
	}

	public void setDato(List<Menu> dato) {
		this.dato = dato;
	}
	
	
	public void loadMenus() {
		menus=mendao.listadoMenu();
	}
	
	public String guardar() {
		mendao.guardar(menu);
		loadMenus();
		return "ListadoMenu.xhtml";
	}
	 public String doListarTodosMenus(){
	     	menus1.clear();
	        List<Menu> lista =mendao.listadoMenu();
	        for(Menu p:lista){
	            menus1.add(p);                     
	        }
	        return "carrito";
	    }
	 public String doMenuPorId(){
		 	menus1.clear();
	        System.out.println("id mdnu "+idMenuABuscar);
	      
	        Menu m = mendao.leer(idMenuABuscar);
	        menus1.add(m);                     
	        return "carrito";
	    }
	 public Menu doBuscarMenuParaElCarrito(int idMenu){
	        return mendao.leer(idMenu);
	    }
	
	public String loadDatosEditar(int id) {
		System.out.println("Cargando datos del Menu a editar"+ id);
		menu=mendao.leer(id);
		return "CrearMenu";
	}
	public Menu loadDatosCarrito(int id) {
		//System.out.println("Cargando datos del Menu a editar"+ id);
		menu=mendao.leer(id);
		return menu;
	}
	
	public String eliminarDatos(int id) {
		mendao.borrar(id);
		return "ListadoMenu";
	}

	
	public String resolverimagen(String url1){
		String url = "resources/img/photo/"+url1;
		System.out.println(url);
		return url;
	}
	
	public ArrayList<Menu> getMenus1() {
		return menus1;
	}

	public void setMenus1(ArrayList<Menu> menus1) {
		this.menus1 = menus1;
	}

	public int getIdMenuABuscar() {
		return idMenuABuscar;
	}

	public void setIdMenuABuscar(int idMenuABuscar) {
		this.idMenuABuscar = idMenuABuscar;
	}

	@Override
	public String toString() {
		return "MenuController [mendao=" + mendao + ", menu=" + menu + ", menus=" + menus + ", menus1=" + menus1
				+ ", id=" + id + ", dato=" + dato + ", idMenuABuscar=" + idMenuABuscar + "]";
	}

}
