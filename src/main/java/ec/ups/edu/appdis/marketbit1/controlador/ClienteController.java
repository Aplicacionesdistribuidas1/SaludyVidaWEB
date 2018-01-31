package ec.ups.edu.appdis.marketbit1.controlador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.ups.edu.appdis.marketbit1.datos.ClienteDAO;
import ec.ups.edu.appdis.marketbit1.modelo.Cliente;
import ec.ups.edu.appdis.marketbit1.modelo.FacCabecera;
import ec.ups.edu.appdis.marketbit1.modelo.SessionUtils;
import ec.ups.edu.appdis.marketbit1.modelo.Ubicacion;

@ManagedBean
@ViewScoped
public class ClienteController {

	@Inject
	private ClienteDAO clidao;
	private SessionUtils session;
	private String username;
	private String password;
	private Cliente cliente = null;
	private List<Cliente> clientes;
	private List<Cliente> nombres;
	private String cedula;
	private ArrayList<String> nombre;
	ArrayList<Integer> obtener = new ArrayList<Integer>();
	ArrayList<Integer> separar;
	//ArrayList<Integer> contar;
	ArrayList<Integer> contar = new ArrayList<Integer>();
	private int id;
	private List<Cliente> dato;
	
	@PostConstruct
	public void init() {
		cliente = new Cliente();
		//cliente.addUbicacion(new Ubicacion());
		loadClientes();
		loadNombres();
	}
	
	
	
	
	public ArrayList<String> getNombre() {
		return nombre;
	}




	public void setNombre(ArrayList<String> nombre) {
		this.nombre = nombre;
	}




	public List<Cliente> getNombres() {
		return nombres;
	}



	public void setNombres(List<Cliente> nombres) {
		this.nombres = nombres;
	}



	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public ClienteDAO getClidao() {
		return clidao;
	}

	public void setClidao(ClienteDAO clidao) {
		this.clidao = clidao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		loadDatosEditar(id);
	}
	
	public void loadClientes() {
		clientes=clidao.listadoCliente();
	}
	
	public void loadNombres() {
		nombres=clidao.listadoNombre();
	}
	
	public String guardar() {
		clidao.guardar(cliente);
		
		loadClientes();
		
		
		return "LoginCliente.xhtml";
	}
	public String addUbicacion() {
		//cliente.addUbicacion(new Ubicacion());
		
		return null;
	}
	
	public String loadDatosEditar(int id) {
		System.out.println("cargando datos de Cliente a editar"+ id);
		cliente= clidao.leer(id);
		return "CrearCliente";
	}
	
	public String send() { 
		cliente = clidao.getCliente(cliente.getUsuario(), cliente.getContrasena()); 
		if (cliente == null) {
			cliente = new Cliente(); 
			
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contrasena incorrectos!", " Login Error!")); 
			return null; 
			} else {
				return "carrito.xhtml"; 
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
		clidao.borrar(id);
		return "ListadoClientes";
	}
	

	public List<Cliente> getDato() {
		return dato;
	}

	public void setDato(List<Cliente> dato) {
		this.dato = dato;
	}
	
		
	public void validadorCedula(FacesContext arg0, UIComponent arg1, Object arg2) {
		arg0 = FacesContext.getCurrentInstance();
		String cedula = (String)arg2;
		String aux = clidao.cedula(cedula);
		if (cedula.equals("1234567890")) {
			((UIInput)arg1).setValid(false);
			arg0.addMessage(arg1.getClientId(arg0), new FacesMessage("Cedula invalida"));
		} else {
			System.out.println(aux);
			if (aux.equals("no")) {
				((UIInput)arg1).setValid(false);
				arg0.addMessage(arg1.getClientId(arg0), new FacesMessage("Cedula invalida"));
			}
		}
	}
	
	public void validadorUsuario(FacesContext arg0, UIComponent arg1, Object arg2) {
		arg0 = FacesContext.getCurrentInstance();
		String usuario = (String)arg2;
		Cliente aux = clidao.getUsuario(usuario); 
		System.out.println(aux);
		if (aux != null) {
			((UIInput)arg1).setValid(false);
			arg0.addMessage(arg1.getClientId(arg0), new FacesMessage("El usuario ya existe"));
		}
	}
	
	public void validadorCorreo(FacesContext arg0, UIComponent arg1, Object arg2) {
		arg0 = FacesContext.getCurrentInstance();
		String correo = (String)arg2;
		Cliente aux = clidao.getCorreo(correo); 
		System.out.println(aux);
		if (aux != null) {
			((UIInput)arg1).setValid(false);
			arg0.addMessage(arg1.getClientId(arg0), new FacesMessage("El correo ya esta en uso"));
		}
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
				contar.add(aux);
			}
		}
		}
		
		//for (int i = 0; i < contar.size(); i++) {
			System.out.println(contar.get(0));
			System.out.println(contar.get(1));
			System.out.println(contar.get(2));
			System.out.println(contar.get(3));
			System.out.println(contar.get(4));
		//}
	}

public int id() {
	int i = id;
	return i;
}

public void validadoredad(FacesContext arg0, UIComponent arg1, Object arg2) {
	arg0 = FacesContext.getCurrentInstance();
	Date valor = (Date)arg2;
	//Cliente aux = clidao.getCorreo(correo); 
	int aux = (Integer) valor.getYear()+1;
	int aux2 = (Integer) valor.getMonth();
	System.out.println(aux);
	System.out.println(aux2);
	Date fecha = new Date();
	int aux3 = (Integer) fecha.getYear();
	int edad = aux3 - aux;
	System.out.println(edad);
	if (edad <= 17) {
		((UIInput)arg1).setValid(false);
		arg0.addMessage(arg1.getClientId(arg0), new FacesMessage("Es menor a 18 anios"));
	}
}

}


