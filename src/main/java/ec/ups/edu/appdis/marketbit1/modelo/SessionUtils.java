package ec.ups.edu.appdis.marketbit1.modelo;



import java.io.Serializable;

import javax.faces.context.FacesContext;

public class SessionUtils implements Serializable{
	
	public void add(String key, Object value){
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
	}
	
public void get(String key){
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}

}
