package ec.ups.edu.appdis.marketbit1.datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.marketbit1.modelo.Ubicacion;

@Stateless
public class UbicacionDAO {
	@Inject
	private EntityManager em;
	
	public void guardar(Ubicacion u) {
		Ubicacion aux=leer(u.getCodigo());
		if(aux!=null) {
			actualizar(u);
		}else {
			insertar(u);
		}
	}
	
	public void insertar (Ubicacion u) {
		em.persist(u);
	}
	
	public void actualizar(Ubicacion u) {
		em.merge(u);
	}
	
	public void borrar(int id) {
		Ubicacion u=leer(id);
		em.remove(u);
	}
	public Ubicacion leer (int id) {
		Ubicacion u = em.find(Ubicacion.class, id);
		return u;
	}
	
	public List<Ubicacion> listadoUbicacion(){
		Query query = em.createQuery("SELECT u FROM Ubicacion u", Ubicacion.class);
		List<Ubicacion>listado = query.getResultList();
		return listado;
	}
	public int ultimocodigo(String cliente) {
		int idcli = (Integer) em.createQuery("SELECT u.codigo from Cliente u where u.usuario = :name").setParameter("name", cliente).getSingleResult();
		return idcli;
	}

}
