package ec.ups.edu.appdis.marketbit1.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/ejemplos")
public class saludo {

	@GET
	@Path("/saludo")
	@Produces("application/json")
	public String saludo(@QueryParam("n") String nombre) {
		return "Hola " + nombre;
	}
}

