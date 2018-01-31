package ec.ups.edu.appdis.marketbit1.datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.marketbit1.modelo.Entrega;


@Stateless
public class EntregaDAO {
	
	@Inject
	private EntityManager em;
	
	public void guardar(Entrega en) {
		Entrega aux=leer(en.getCodigo());
		if(aux!=null) {
			actualizar(en);
		}else {
			insertar(en);
		}
	}
	public void insertar (Entrega en) {
		em.persist(en);
	}
	
	public void actualizar(Entrega en) {
		em.merge(en);
	}
	
	public void borrar (int id) {
		Entrega en = leer(id);
		em.remove(en);
	}
	
	public Entrega leer (int id) {
		Entrega en=em.find(Entrega.class, id);
		return en;
	}
	
	public List<Entrega> listadoEntrega(){
		Query query = em.createQuery("SELECT en FROM Entrega en", Entrega.class);
		List<Entrega>listado =query.getResultList();
		return listado;
	}

}
