package ec.ups.edu.appdis.marketbit1.datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ec.ups.edu.appdis.marketbit1.modelo.Menu;
import ec.ups.edu.appdis.marketbit1.webservices.Respuesta;

@Stateless
@SessionScoped
public class menuDAO {
	
	@Inject
	private EntityManager em;
	
	public void guardar(Menu men) {
		Menu aux=leer(men.getCodigo());
		if(aux!=null) {
			actualizar(men);
		}else {
			insertar(men);
		}
	}

	public void insertar (Menu men) {
		em.persist(men);
	}
	
	public void actualizar(Menu men ) {
		em.merge(men);
	}
	
	public void borrar (int id) {
		Menu men =leer (id);
		em.remove(men);
	}
	
	public Menu leer(int id) {
		Menu men = em.find(Menu.class, id);
		return men;
	}
	
	public List<Menu> listadoMenu(){
		Query query= em.createQuery("SELECT men FROM Menu men", Menu.class);
		List<Menu>listado =query.getResultList();
		return listado;
	}
	public List<Menu> listadoMenuid(int id){
		Query query= em.createQuery("SELECT  men FROM Menu men WHERE men.codigo = :id").setParameter("id", id);
		//Query query= em.createQuery("SELECT men FROM menus WHERE men.menu_codigo=:"+codigo+"", Menu.class);
		List<Menu>listado =query.getResultList();
		return listado;
	}
	public List<Menu> getMenus(){
		String jpql = "SELECT distinct men FROM Menu men LEFT JOIN FETCH men.facturadetalle";
		Query query = em.createQuery(jpql, Menu.class);
		List<Menu> menus = query.getResultList();
		return menus;
	}
	public List<Menu> getMenus1(){
		String jpql = "SELECT distinct men FROM Menu men";
		Query query = em.createQuery(jpql, Menu.class);
		List<Menu> menus = query.getResultList();
		return menus;
	}
	
	
}
