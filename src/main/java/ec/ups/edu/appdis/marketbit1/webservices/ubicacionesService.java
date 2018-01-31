package ec.ups.edu.appdis.marketbit1.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.ups.edu.appdis.marketbit1.datos.UbicacionDAO;
import ec.ups.edu.appdis.marketbit1.modelo.Ubicacion;

@Path("/ubicaciones")
public class ubicacionesService {
	
	@Inject
	private UbicacionDAO dao;	

	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Ubicacion> listadoUbicacion() {
		List<Ubicacion> ubicaciones = dao.listadoUbicacion();		
		return ubicaciones;
		}
		
		@GET
		@Path("/listarid")
		@Produces("application/json")
		public Ubicacion getCliente(@QueryParam("id") int id) {
			Ubicacion ubi = new Ubicacion();
			ubi.setCodigo(id);;
			return ubi;
		}
		@POST
		@Path("/guardar")
		@Produces("application/json")
		@Consumes("application/json")
		public Respuesta saveCliente(Ubicacion ubi) {
			Respuesta resp = new Respuesta();
			try {
				dao.insertar(ubi);;
				resp.setCodigo(1);
				resp.setMensaje("Registro satisfactorio");
			}catch(Exception e) {
				resp.setCodigo(-1);
				resp.setMensaje("Aki error en registro");
				e.printStackTrace();
			}			
			return resp;
		}
		@GET
		@Path("/ultimoid")
		@Produces("application/json")
		public int ultimaId(@QueryParam("user") String user) {
			int cliId = dao.ultimocodigo(user);		
			return cliId;
			}
		
}
