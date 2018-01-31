package ec.ups.edu.appdis.marketbit1.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.ups.edu.appdis.marketbit1.controlador.CarritoController;
import ec.ups.edu.appdis.marketbit1.datos.ClienteDAO;
import ec.ups.edu.appdis.marketbit1.modelo.Cliente;
import ec.ups.edu.appdis.marketbit1.modelo.Menu;
@Path("/carrito")	
public class carritoServices {
	
	@Inject
	private CarritoController ccon;	

	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Menu> listadoCarrito() {
		List<Menu> menus = ccon.carrito;		
		System.out.println(ccon.carrito.size()+"");
		return menus;		
		}
		
		@POST
		@Path("/guardar")
		@Produces("application/json")
		@Consumes("application/json")
		public Respuesta saveCarrito(@QueryParam("id") int idMenu) {
			Respuesta resp = new Respuesta();
			try {
				ccon.agregarAlCarrito(idMenu);
				resp.setCodigo(1);
				resp.setMensaje("Registro satisfactorio");
			}catch(Exception e) {
				resp.setCodigo(-1);
				resp.setMensaje("Aki error en registro");
				e.printStackTrace();
			}			
			return resp;
		
}
}
