package ec.ups.edu.appdis.marketbit1.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


import ec.ups.edu.appdis.marketbit1.datos.menuDAO;
import ec.ups.edu.appdis.marketbit1.modelo.FacturaDetalle;
import ec.ups.edu.appdis.marketbit1.modelo.Menu;
import ec.ups.edu.appdis.marketbit1.modelo.MenuTemp;

@Path("/menus")
public class MenuService {
	
	@Inject
	private menuDAO dao;
	
	
		
	@GET
	@Path("/menuid")
	@Produces("application/json")
	public Menu getMenuId(@QueryParam("id") int id) {
		Menu men=new Menu();
		men.setCodigo(id);
		return men;
	}
	@GET
	@Path("/listarid")
	@Produces("application/json")
	public List<Menu> getMenuI(@QueryParam("id") int id) {
		List<Menu> menus1=dao.listadoMenuid(id);
		return menus1;
		
	}
	
	
	
	@GET
	@Path("/menus")
	@Produces("application/json")
	public List<Menu> getMenus1() {
		List<Menu> menus1=dao.listadoMenu();
		return menus1;
	}
	
	@POST
	@Path("/guardar")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta saveMenu(Menu men) {
		Respuesta resp = new Respuesta();
		try {
			dao.insertar(men);
			resp.setCodigo(1);
			resp.setMensaje("Registro satisfactorio");
		}catch(Exception e) {
			resp.setCodigo(-1);
			resp.setMensaje("Error en registro");
		}			
		return resp;
	}

}
