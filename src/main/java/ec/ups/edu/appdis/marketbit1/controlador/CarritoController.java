package ec.ups.edu.appdis.marketbit1.controlador;



import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JOptionPane;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import ec.ups.edu.appdis.marketbit1.datos.FacCabeceraDAO;
import ec.ups.edu.appdis.marketbit1.datos.FacturaDetalleDAO;
import ec.ups.edu.appdis.marketbit1.datos.menuDAO;
import ec.ups.edu.appdis.marketbit1.modelo.Cliente;
import ec.ups.edu.appdis.marketbit1.modelo.Entrega;
import ec.ups.edu.appdis.marketbit1.modelo.FacCabecera;
import ec.ups.edu.appdis.marketbit1.modelo.FacturaDetalle;
import ec.ups.edu.appdis.marketbit1.modelo.Menu;
@ManagedBean
@SessionScoped
public class CarritoController implements Serializable{
	
	@Inject
	private menuDAO mDao;
	
	@Inject
    private MenuController menuController;
    
    @Inject
    private FacCabeceraDAO daoFac;
    public ArrayList<Menu> carrito = new ArrayList<Menu>();
    private Menu menuSelecionado;
    private int idMenuSeleccionado;
    private double totalCompra = 0.0;
    private int cantidad=1;
    private double iva,subtotal,total;
	DecimalFormat fd=new DecimalFormat("#.00");
    public CarritoController() {
    	
    }
    //metodos
    
    public String agregarAlCarrito(int idMenu) {
        //int id=idMenu;
    	System.out.println("Aplasto agregar al carrito");
        setIdMenuSeleccionado(idMenu);
        Menu m = buscarMenuCarrito(idMenuSeleccionado);
       
        if (m != null ) {
            int n = carrito.indexOf(m);
            System.out.println(n);
            carrito.get(n).setCantidad(carrito.get(n).getCantidad() + 1);
            
            //fd.format(totalCompra+=m.getPrecio());
            totalCompra+=menuSelecionado.getPrecio()*cantidad;
            fd.format(iva=totalCompra*0.12);
    		fd.format(subtotal=totalCompra-iva);
        } else {
        	//int n = carrito.indexOf(m);
        	menuSelecionado = mDao.leer(idMenuSeleccionado);
	        carrito.add(menuSelecionado);
	        totalCompra+=menuSelecionado.getPrecio()*cantidad;
	        fd.format(iva=totalCompra*0.12);
			fd.format(subtotal=totalCompra-iva);   
	        System.out.println("tam "+carrito.get(0).getNombre());
        	for(int i=0;i<carrito.size();i++) {
        		if(carrito.get(i).getCodigo()==idMenuSeleccionado) {
        			fd.format( total=(carrito.get(i).getCantidad()*carrito.get(i).getPrecio()));
        		}else {
        			
        		}
        	}
        
        }
        //System.out.println();
        return null;
    }
    /*
    public String confirmarPedido() {
        Date date = new Date();
        ArrayList<Entrega> pedidos = new ArrayList<Entrega>();
        if (usuarioController.getIdcliente() > 0) {
            Pedido pedido = new Pedido(date, usuarioController.getIdcliente(), 'a');
            for (Menu p : carrito) {
                Number n = p.getCantidad();
                BigDecimal cant = new BigDecimal(n.toString());
                BigDecimal monto = p.getPrecio().multiply(cant);
                BigDecimal descuento = new BigDecimal(0.0);
               
                Lineapedido linea = new Lineapedido(pedido, p.getIdproducto(), descuento, p.getCantidad(), monto);
                pedidos.add(linea);
            }
            pedido.setPedidos(pedidos);
            pedidoFacade.create(pedido);            
            carrito.clear();
            totalCompra = 0.0;
            return "confirmacion";
        } else {
            FacesContext faces = FacesContext.getCurrentInstance();
            faces.addMessage("mensajeError", new FacesMessage("Para poder comprar debes registrate o ingresar al sistema"));
            return "carrito";
        }
    }
*/
	 public String borrarDelCarrito(int idMenu) {
	        boolean encontrado = false;
	        int i = 0;
	        while (!encontrado) {
	            if (carrito.get(i).getCodigo() == idMenu) {
	                totalCompra-=carrito.get(i).getPrecio();
	                carrito.remove(i);                
	                encontrado = true;
	            }
	            i++;
	        }
	        return "carritoactual";
	    }
	 private Menu buscarMenuCarrito(int idMenu) {
	        Menu m = null;
	        for (Menu men : carrito) {
	        	System.out.println(men.getCodigo()+" " +idMenu);
	            if (men.getCodigo() == idMenu) {
	            	System.out.println("pch "+ men.getCodigo());
	                m = men;
	                break;
	            }
	        }
	        return m;
	    }
	 
	 public String aumentarCantidad(int id) {
		 for(int i=0;i<carrito.size();i++) {
			 if(carrito.get(i).getCodigo()==id) {
		 carrito.get(i).setCantidad(carrito.get(i).getCantidad() + 1);
		 //cantidad+=1;
		 fd.format(total=(carrito.get(i).getCantidad()*carrito.get(i).getPrecio()));
		 }
		 }
		 
		 fd.format(totalCompra+=menuSelecionado.getPrecio()*cantidad);
		 fd.format(iva=totalCompra*0.12);
		 fd.format(subtotal=totalCompra-iva);
	 return null;
	 }
	 
	 public String disminuirCantidad(int id) {
		 for(int i=0;i<carrito.size();i++) {
		 if(carrito.get(i).getCodigo()==id) {
		 carrito.get(i).setCantidad(carrito.get(i).getCantidad()-1);	 
		 fd.format(total=(carrito.get(i).getCantidad()*carrito.get(i).getPrecio()));
		 if(carrito.get(i).getCantidad()==0) {
			borrarDelCarrito(id); 
		 }
		 if(carrito.size()==0) {

			 fd.format(subtotal=0);
			 fd.format(iva=0);
			 fd.format(totalCompra=0);
					
		 }
		 }
		 }
		 
		 fd.format(totalCompra-=menuSelecionado.getPrecio());
		 fd.format(iva=totalCompra*0.12);
		 fd.format(subtotal=totalCompra-iva);
		 
		 return null;
	 }
	
	 public String doIrCarrito() {
	        menuController.doListarTodosMenus();
	        return "carrito";
	    }
	 
	    public String doIrCarritoActual() {
	        return "carritoactual";
	    }
	    
	  
	public String guardar() {
		java.util.Date fecha = new Date();
		
		FacCabecera fc = new FacCabecera();
		Cliente cli = new Cliente();
		Entrega en=new Entrega();
		cli.setCodigo(11);  //debe ir el de la sesion
		fc.setCliente(cli);
		fc.setFechaEmision(fecha);
		fc.setNumero_factura(11111111);
		fc.setTotal(totalCompra);
		fc.setIva(iva);
		fc.setSubtotal(subtotal);
		en.setCostoEnvio(2.00);	
		en.setCliente(cli);
		for(Menu m : carrito) {
			FacturaDetalle fd = new FacturaDetalle();
			fd.setCantidad(m.getCantidad());
			fd.setSubtotal(m.getPrecio()*m.getCantidad());
			Menu mnu = new Menu();  //el entity
			mnu.setCodigo(m.getCodigo());
			fd.setMenu(mnu);
			fc.addDetalle(fd);
			en.setCabeceras1(fc);
		}
		
		daoFac.insertar(fc);
		
		
		return "carrito.xhtml";
	}
	
	public void validadornegativos(FacesContext arg0, UIComponent arg1, Object arg2) {
		arg0 = FacesContext.getCurrentInstance();
		//String valor = (String)arg2;
		//Cliente aux = clidao.getCorreo(correo); 
		//Double aux = Double.valueOf(valor);
		System.out.println(total);
		if (total <= 0.00) {
			((UIInput)arg1).setValid(false);
			arg0.addMessage(arg1.getClientId(arg0), new FacesMessage("el valor no puede ser 0 o menor"));
		}
	}
	

	public MenuController getMenuController() {
		return menuController;
	}

	public void setMenuController(MenuController menuController) {
		this.menuController = menuController;
	}

	public ArrayList<Menu> getCarrito() {
		return carrito;
	}

	public void setCarrito(ArrayList<Menu> carrito) {
		this.carrito = carrito;
	}

	public Menu getMenuSelecionado() {
		return menuSelecionado;
	}

	public void setMenuSelecionado(Menu menuSelecionado) {
		this.menuSelecionado = menuSelecionado;
	}

	public int getIdMenuSeleccionado() {
		return idMenuSeleccionado;
	}

	public void setIdMenuSeleccionado(int idMenuSeleccionado) {
		this.idMenuSeleccionado = idMenuSeleccionado;
	}

	public double getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(double totalCompra) {
		this.totalCompra = totalCompra;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
