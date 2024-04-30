package pl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ZereginaFormMB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String izena;
	private int ordukop;
	private int idErab;
	
	
	public ZereginaFormMB() {
		// TODO Auto-generated constructor stub
	}


	public ZereginaFormMB(String izena, int ordukop, int idErab) {
		this.izena = izena;
		this.ordukop = ordukop;
		this.idErab = idErab;
	}


	
	public String getIzena() {
		return izena;
	}


	public void setIzena(String izena) {
		this.izena = izena;
	}


	public int getOrdukop() {
		return ordukop;
	}


	public void setOrdukop(int ordukop) {
		this.ordukop = ordukop;
	}



	public void resetForm() {
		izena = "";
		ordukop = 0;
		idErab = 0;
	}


	public int getIdErab() {
		return idErab;
	}


	public void setIdErab(int idErab) {
		this.idErab = idErab;
	}
	

}
