package ec.ups.edu.appdis.marketbit1.controlador;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.ups.edu.appdis.marketbit1.datos.EmpleadoDAO;
import ec.ups.edu.appdis.marketbit1.modelo.Cliente;
import ec.ups.edu.appdis.marketbit1.modelo.Empleado;
import ec.ups.edu.appdis.marketbit1.modelo.Entrega;


@ManagedBean
public class EmpleadoController {

	@Inject
	private EmpleadoDAO empdao;
	
	private Empleado empleado = null;
	private java.util.List<Empleado> empleados;
	
	private int id;
	
	@PostConstruct
	public void init() {
		empleado = new Empleado();
		loadEmpleados();
		
	}
	
	
	public void loadEmpleados() {
		empleados = empdao.listadoEmpleado();
	}
	public String guardar()
	{
		
		empdao.guardar(empleado);
		loadEmpleados();
		return "ListadoEmpleados";
			}

	public String loadDatosEditar(int id) {
		System.out.println("cargando datos de empleado a editar"+ id);
		empleado = empdao.leer(id);
		return "CrearEmpleado";
	}
	
	public String send() { 
		if (empleado.getUsuario().equals("ADMIN") && empleado.getContrasena().equals("ADMIN")) {
			return "PaginaPrincipal";
		} else {
			empleado = empdao.getEmpleado(empleado.getUsuario(), empleado.getContrasena()); 
			if (empleado == null) {
				empleado = new Empleado(); 
				FacesContext.getCurrentInstance().addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contrasena incorrectos!", " Login Error!")); 
				return null; 
				} else {
					return "ListadoEntrega"; 
					} 
		}
		
		}
			
	public void logout() {
		ExternalContext ec=FacesContext.getCurrentInstance().getExternalContext();
		ec.invalidateSession();
		//return "/home.xhtml?faces-redirect=true";\
        try {
			ec.redirect(ec.getRequestContextPath() + "/probar.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

	public String eliminarDatos(int id) {
		empdao.borrar(id);
		return "ListadoEmpleados";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		loadDatosEditar(id);
	}


	public Empleado getEmpleado() {
		return empleado;
	}


	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}


	public List<Empleado> getEmpleados() {
		return empleados;
	}


	public void setEmpleados(java.util.List<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	public void validadorCedula(FacesContext arg0, UIComponent arg1, Object arg2) {
		arg0 = FacesContext.getCurrentInstance();
		String cedula = (String)arg2;
		String aux = empdao.cedula(cedula);
		System.out.println(aux);
		if (aux.equals("no")) {
			((UIInput)arg1).setValid(false);
			arg0.addMessage(arg1.getClientId(arg0), new FacesMessage("Cedula invalida"));
		}
	}
	
	public void validadorUsuario(FacesContext arg0, UIComponent arg1, Object arg2) {
		arg0 = FacesContext.getCurrentInstance();
		String usuario = (String)arg2;
		Empleado aux = empdao.getUsuario(usuario); 
		System.out.println(aux);
		if (aux != null) {
			((UIInput)arg1).setValid(false);
			arg0.addMessage(arg1.getClientId(arg0), new FacesMessage("El usuario ya existe"));
		}
	}

	@Override
	public String toString() {
		return "empleadoController [empdao=" + empdao + ", empleado=" + empleado + ", empleados=" + empleados + ", id="
				+ id + "]";
	}
	
}
