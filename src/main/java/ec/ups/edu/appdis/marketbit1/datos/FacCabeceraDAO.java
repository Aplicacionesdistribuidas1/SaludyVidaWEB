package ec.ups.edu.appdis.marketbit1.datos;


import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.marketbit1.modelo.FacCabecera;

@Stateless
public class FacCabeceraDAO {
	
	@Inject
	private EntityManager em;
	
	public void guardar(FacCabecera fc) {
		FacCabecera aux = leer(fc.getCodigo());
		if(aux!=null) {
			actualizar(fc);
		}else {
			insertar(fc);
		}
	}

	
	public void insertar(FacCabecera fc) {
		em.persist(fc);
	}
	
	public void actualizar(FacCabecera fc) {
		em.merge(fc);
	}
	
	public void borrar(int id) {
		FacCabecera fc = leer(id);
		em.remove(fc);
	}
	
	public FacCabecera leer(int id) {
		FacCabecera fc = em.find(FacCabecera.class, id);
		return fc;
	}
	
	public List<FacCabecera> listadofacCabecera(){
		Query query = em.createQuery("SELECT fc FROM FacCabecera fc", FacCabecera.class);
		List<FacCabecera> listado=query.getResultList();
		return listado;
	}
}
