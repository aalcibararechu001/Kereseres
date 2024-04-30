package pl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class TaldeBerriaFormMB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String taldearen_izena;
	private String pasahitza;
	
	
	public TaldeBerriaFormMB() {
		// TODO Auto-generated constructor stub
	}


	public TaldeBerriaFormMB(String taldearen_izena, String pasahitza) {
		this.taldearen_izena = taldearen_izena;
		this.pasahitza = pasahitza;
	}


	public String gettaldearen_izena() {
		return taldearen_izena;
	}


	public void settaldearen_izena(String taldearen_izena) {
		this.taldearen_izena = taldearen_izena;
	}


	public String getPasahitza() {
		return pasahitza;
	}


	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}
	
	public void resetForm() {
		taldearen_izena = "";
		pasahitza = "";
	}
	

}
