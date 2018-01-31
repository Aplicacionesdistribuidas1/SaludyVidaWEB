package ec.ups.edu.appdis.marketbit1.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.ups.edu.appdis.marketbit1.datos.ClienteDAO;
import ec.ups.edu.appdis.marketbit1.modelo.Cliente;
@Path("/clientes")
public class ClientesService {
	
	@Inject
	private ClienteDAO dao;	

	
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Cliente> listadoCliente() {
		List<Cliente> clientes = dao.listadoCliente();		
		return clientes;
		}
		
	@GET
	@Path("/login")
	@Produces("application/json")
	public Cliente getClienteLogin(@QueryParam("user") String user) {
		Cliente cli = dao.getUsuario(user);
		return cli;
	}
		
		@GET
		@Path("/listarid")
		@Produces("application/json")
		public Cliente getCliente(@QueryParam("id") int id) {
			Cliente cli = new Cliente();
			cli.setCodigo(id);;
			return cli;
		}
		@POST
		@Path("/guardar")
		@Produces("application/json")
		@Consumes("application/json")
		public Respuesta saveCliente(Cliente cli) {
			Respuesta resp = new Respuesta();
			try {
				dao.insertar(cli);
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
