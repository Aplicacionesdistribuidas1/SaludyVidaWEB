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

import ec.ups.edu.appdis.marketbit1.datos.EmpleadoDAO;
import ec.ups.edu.appdis.marketbit1.modelo.Empleado;
  
@Path("/empleados")
public class EmpleadosService {
	
	@Inject
	private EmpleadoDAO dao;	

	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Empleado> listadoEmpleado() {
		List<Empleado> empleados = dao.listadoEmpleado();		
		return empleados;
	}
	
	
	@GET
	@Path("/listarid")
	@Produces("application/json")
	public Empleado getEmpleado(@QueryParam("id") int id) {
		Empleado emp = new Empleado();
		emp.setCodigo(id);
		return emp;
	}
	@POST
	@Path("/guardar")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta saveEmpleado(Empleado emp) {
		Respuesta resp = new Respuesta();
		try {
			dao.insertar(emp);
			resp.setCodigo(1);
			resp.setMensaje("Registro satisfactorio");
		}catch(Exception e) {
			resp.setCodigo(-1);
			resp.setMensaje("Error en registro");
		}			
		return resp;
	}
	
}
