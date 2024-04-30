package pl;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import bl.OrokorraEJB;
import dl.ErabiltzaileaE;

@Named
@SessionScoped
public class SesioaAtazakMB implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	@EJB private OrokorraEJB orokorraEJB;
	private String username;
	private String pasahitza;
	private String izena;
	private String rola;
	private int kodea=0;
	private ErabiltzaileaE erabiltzailea;
	private int erregistratu=0;
	
	public SesioaAtazakMB() {
		// TODO Auto-generated constructor stub
	}
	
	public ErabiltzaileaE getErabiltzailea() {
		return erabiltzailea;
	}

	public void setErabiltzailea(ErabiltzaileaE erabiltzailea) {
		this.erabiltzailea = erabiltzailea;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public int getErregistratu() {
		return erregistratu;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getRola() {
		return rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}

	public void setErregistratu(int erregistratu) {
		this.erregistratu = erregistratu;
	}

	public void erregistratuAtera() {
		erregistratu = 1;
	}
	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public int getKodea() {
		return kodea;
	}

	public void setKodea(int kodea) {
		this.kodea = kodea;
	}

	/*
	public void saioaHasiDB(ErabFormMB formMB) {
		ErabiltzaileaE erabiltzaileaE = orokorraEJB.saioaHasiDB(formMB.getUsername());
		System.out.println(erabiltzaileaE.getUsername());
		
	}*/
	
	public void erabiltzaileBerriaDB() {
		List<ErabiltzaileaE>ErabiltzaileZerrenda = orokorraEJB.erabiltzaileaBilatuDB(username);
		if (ErabiltzaileZerrenda.size()>0) {
			kodea =3 ;
		}else {
			String e = "Erabiltzailea";
			ErabiltzaileaE erabiltzaileberria = new ErabiltzaileaE(0,izena,pasahitza,e,username);
			orokorraEJB.erabiltzaileBerriaDB(erabiltzaileberria);
			kodea = 5;
			erregistratu=0;
		}
		username =null;
		pasahitza=null;
		izena=null;
		rola=null;
	}
	
	public void SaioaHasi() {
		List<ErabiltzaileaE>ErabiltzaileZerrenda = orokorraEJB.erabiltzaileaBilatuDB(username);
		if (ErabiltzaileZerrenda.size()==0) {
			kodea =1 ;
		}else {
			
			if(ErabiltzaileZerrenda.get(0).getPasahitza().equals(pasahitza)==true) {
				kodea = 0;
				erabiltzailea = ErabiltzaileZerrenda.get(0);
				redirigir("taldeak.xhtml");
			}else {
				kodea = 2;
			}
		}
		username=null;
		pasahitza=null;	
	}
	
	private void redirigir(String pagina) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        String contextPath = externalContext.getRequestContextPath();

        try {
            externalContext.redirect(pagina);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
