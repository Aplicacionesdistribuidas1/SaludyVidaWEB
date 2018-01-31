package ec.ups.edu.appdis.marketbit1.datos;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.ups.edu.appdis.marketbit1.modelo.Cliente;

@Stateless
public class ClienteDAO {
	

	@Inject
	private EntityManager em;
	
	public void guardar(Cliente cli) {
		Cliente aux=leer(cli.getCodigo());
		if(aux!=null){
			actualizar(cli);
		}else {
			insertar(cli);
			
		}
	}
	
	String usuario1;
	
	public void insertar (Cliente cli) {
		em.persist(cli);
	}
	
	public void actualizar (Cliente cli) {
		em.merge(cli);
	}
	
	public void borrar (int id) {
		Cliente cli = leer (id);
		em.remove(cli);
	}
	
	public Cliente leer (int id) {
		Cliente cli = em.find(Cliente.class, id);
		//cli.getUbicaciones().size();
		return cli;
	}
	
	public Cliente Buscar (String usuario) {
		Cliente cli = em.find(Cliente.class, usuario);
		return cli;
	}
	
	public List<Cliente> listadoCliente(){
		Query query= em.createQuery("SELECT distinct cli FROM Cliente cli LEFT JOIN FETCH cli.ubicaciones ", Cliente.class);
		List<Cliente>listado = query.getResultList();
		return listado;
		
	}
	
	public List<Cliente> listadoNombre(){
		//javax.persistence.Query query= em.createQuery("SELECT cli FROM Cliente cli where usuario = 'jpelaez92' ", Cliente.class);
		javax.persistence.Query query= em.createQuery("SELECT cli FROM Cliente cli where usuario = :name").setParameter("name", usuario1);
		java.util.List<Cliente>listado = query.getResultList();
		return listado;
	}
	
	public Cliente getCliente(String usuario, String contrasena){ 
		usuario1 = usuario;
		try {
			Cliente cliente = (Cliente) em .createQuery( "SELECT u from Cliente u where u.usuario = :name and u.contrasena = :password") 
					.setParameter("name", usuario) .setParameter("password", contrasena).getSingleResult();
			return cliente; 
			} catch (NoResultException e) { 
				return null; 
				} 
		}
	
	public int contador_cliente() {
		int aux = 0;
		try {
			 aux= (Integer) em.createNativeQuery("select max(cli_codigo) from cliente").getSingleResult();
			 return aux;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return aux;
	}
	
	public String cedula(String cedula) {
		String hola = "no";
		try {
			ArrayList<String> cedula1 = new ArrayList<String>();
			ArrayList<Integer> cedula2 = new ArrayList<Integer>();
			for (int i = 1; i <= cedula.length(); i++) {
				cedula1.add(cedula.substring(i-1, i));
				//System.out.println(cedula1.get(i-1));
				cedula2.add(Integer.parseInt(cedula1.get(i-1)));	
			}
			//System.out.println("aki");
			ArrayList<Integer> cedula3 = new ArrayList<Integer>();
			if (cedula2.get(0)*2>9) {
				cedula3.add(cedula2.get(0)*2-9);
			} else {
				cedula3.add(cedula2.get(0)*2);
			}
			if (cedula2.get(2)*2>9) {
				cedula3.add(cedula2.get(2)*2-9);
			} else {
				cedula3.add(cedula2.get(2)*2);
			}
			if (cedula2.get(4)*2>9) {
				cedula3.add(cedula2.get(4)*2-9);
			} else {
				cedula3.add(cedula2.get(4)*2);
			}
			if (cedula2.get(6)*2>9) {
				cedula3.add(cedula2.get(6)*2-9);
			} else {
				cedula3.add(cedula2.get(6)*2);
			}
			if (cedula2.get(8)*2>9) {
				cedula3.add(cedula2.get(8)*2-9);
			} else {
				cedula3.add(cedula2.get(8)*2);
			}
			cedula3.add(cedula2.get(1));
			cedula3.add(cedula2.get(3));
			cedula3.add(cedula2.get(5));
			cedula3.add(cedula2.get(7));
			int subtotal = cedula3.get(0)+cedula3.get(1)+cedula3.get(2)+cedula3.get(3)+cedula3.get(4)+cedula3.get(5)+cedula3.get(6)+cedula3.get(7)+cedula3.get(8); 
			//System.out.println(subtotal);
			int residuo = 10-(subtotal%10);
			//System.out.println(residuo);
			//System.out.println(cedula2.get(9));
			if (subtotal%10 == cedula2.get(9)) {
				hola = "si";
			} else if (residuo == cedula2.get(9)) {
				hola = "si";
			}		
			return hola;
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return hola;
	}
	
	public Cliente getUsuario(String usuario){ 
		try {
			Cliente cliente = (Cliente) em.createQuery( "SELECT u from Cliente u where u.usuario = :name ").setParameter("name", usuario).getSingleResult();
			System.out.println(cliente);
			return cliente; 
			} catch (NoResultException e) { 
				
				
				}
		return null; 
	}
	
	public Cliente getCorreo(String correo){ 
		try {
			Cliente cliente = (Cliente) em .createQuery( "SELECT u from Cliente u where u.email = :name ").setParameter("name", correo).getSingleResult();
			System.out.println(cliente);
			return cliente; 
			} catch (NoResultException e) { 
				return null; 
				}
	}
	
	public String getNombre(String usuario) {
		try {
			String nombre = (String) em.createQuery("SELECT u.nombres from Cliente u where u.usuario = :name").setParameter("name", usuario).getSingleResult();
			System.out.println(nombre);
			return nombre;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public List<Cliente> listadoNombres(){
		javax.persistence.Query query= em.createQuery("SELECT cli FROM Cliente cli", Cliente.class);
		java.util.List<Cliente>listado = query.getResultList();
		return listado;
	}
	
	

}
