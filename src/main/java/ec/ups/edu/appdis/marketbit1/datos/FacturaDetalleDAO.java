package ec.ups.edu.appdis.marketbit1.datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.marketbit1.modelo.FacturaDetalle;
import ec.ups.edu.appdis.marketbit1.modelo.Menu;

@Stateless
public class FacturaDetalleDAO {
	
	@Inject
	private EntityManager em;
	
	public void guardar(FacturaDetalle fd) {
		FacturaDetalle aux = leer(fd.getCodigo());
		if(aux!=null) {
			actualizar(fd);
		}else {
			insertar(fd);
		}
	}
	
	public void insertar (FacturaDetalle fd) {
		em.persist(fd);
	}
	
	public void actualizar (FacturaDetalle fd) {
		em.merge(fd);
	}
	
	public void borrar (int  id) {
		FacturaDetalle fd = leer(id);
		em.merge(fd);
	}
	
	public FacturaDetalle leer (int id) {
		FacturaDetalle fd = em.find(FacturaDetalle.class, id);
		return fd;
	}
	
	public List<FacturaDetalle> listadoFacturaDetalle(){
		Query query = em.createQuery("SELECT fd From FacturaDetalle fd", FacturaDetalle.class);
		List<FacturaDetalle> listado = query.getResultList();
		return listado;
	}
	public List<FacturaDetalle> getDetalles1(){
		String jpql = "SELECT distinct fd FROM FacturaDetalle fd";
		Query query = em.createQuery(jpql, FacturaDetalle.class);
		List<FacturaDetalle> detalles = query.getResultList();
		return detalles;
	}
}
