package ec.ups.edu.appdis.marketbit1.datos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.ups.edu.appdis.marketbit1.modelo.Cliente;
import ec.ups.edu.appdis.marketbit1.modelo.Empleado;

@Stateless
public class EmpleadoDAO {
	
	@Inject
private EntityManager em;
	
	public void guardar(Empleado emp) {
		Empleado aux=leer(emp.getCodigo());
		if(aux!=null){
			actualizar(emp);
		}else {
			insertar(emp);
			
		}
	}
	String usuario1;
	String contra;
	
	public void insertar (Empleado emp) {
		em.persist(emp);
	}
	
	public void actualizar (Empleado emp) {
		em.merge(emp);
	}
	
	public void borrar (int id) {
		Empleado emp = leer (id);
		em.remove(emp);
	}
	
	public Empleado leer (int id) {
		Empleado emp = em.find(Empleado.class, id);
		return emp;
	
	}
	public java.util.List<Empleado> listadoEmpleado(){
		javax.persistence.Query query= em.createQuery("SELECT emp FROM Empleado emp", Empleado.class);
		java.util.List<Empleado>listado = query.getResultList();
		return listado;
	}
	
	public Empleado getEmpleado(String usuario, String contrasena){ 
		usuario1 = usuario;
		contra = contrasena;
		try {
			Empleado empleado = (Empleado) em .createQuery( "SELECT u from Empleado u where u.usuario = :name and u.contrasena = :password") 
					.setParameter("name", usuario) .setParameter("password", contrasena).getSingleResult();
			return empleado; 
			} catch (NoResultException e) { 
				return null; 
				} 
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
	
	public Empleado getUsuario(String usuario){ 
		try {
			Empleado empleado = (Empleado) em .createQuery( "SELECT u from Empleado u where u.usuario = :name ").setParameter("name", usuario).getSingleResult();
			System.out.println(empleado);
			return empleado; 
			} catch (NoResultException e) { 
				
				}
		return null; 
	}
	
	
}



