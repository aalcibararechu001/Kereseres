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
import dl.TaldeaE;

@Named
@SessionScoped
public class TaldeAtazakMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int kodea;
	private TaldeaE erabiltzailearenTaldea;
	@EJB private OrokorraEJB orokorraEJB;
	private String pasahitza;
	private String mezua;
	private String bilatutako_taldea;
	
	public int getKodea() {
		return kodea;
	}



	public String getPasahitza() {
		return pasahitza;
	}



	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}



	public void setKodea(int kodea) {
		this.kodea = kodea;
	}

	public TaldeaE getErabiltzailearenTaldea() {
		return erabiltzailearenTaldea;
	}



	public void setErabiltzailearenTaldea(TaldeaE erabiltzailearenTaldea) {
		this.erabiltzailearenTaldea = erabiltzailearenTaldea;
	}



	public TaldeAtazakMB() {
		// TODO Auto-generated constructor stub
	}

	public void taldeBerriaSortu(TaldeBerriaFormMB form, SesioaAtazakMB atazak){
		TaldeaE taldea = new TaldeaE(0,form.gettaldearen_izena(),form.getPasahitza());
		List<TaldeaE> taldeak = orokorraEJB.taldeaBilatuDB(taldea.getIzena());
		if(taldeak.size()>0) {
			kodea=1;
			mezua= "Iadanik badago talde bat izen horrekin. Jarri beste izen bat.";
		}else {
			orokorraEJB.taldeBerriaSortuDB(taldea,atazak.getErabiltzailea());
			redirigir("zereginak.xhtml");
		}
		
		form.resetForm();
	}
	
	public int talderaSartu(TaldeaE taldea) {
		kodea=0;//iual hemen zoze konprobau berkoa orokorrian
		erabiltzailearenTaldea = taldea;
		redirigir("zereginak.xhtml");
		return kodea;
	}
	public void taldetikAtera() {
		erabiltzailearenTaldea = null;
	}

	public void erabiltzaileaTalderaBatu(TaldeaE taldea, SesioaAtazakMB atazak){
		
		int konparatu = taldea.getPasahitza().compareTo(pasahitza);
		if(konparatu!=0){
			kodea=1;
			setMezua("Pasahitza ez da zuzena");
		}else{
			orokorraEJB.erabiltzaileaTalderaBatu(taldea, atazak.getErabiltzailea());
		}
		
	}
	
	private void redirigir(String pagina) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        try {
            externalContext.redirect(pagina);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



	public String getBilatutako_taldea() {
		return bilatutako_taldea;
	}



	public void setBilatutako_taldea(String bilatutako_taldea) {
		this.bilatutako_taldea = bilatutako_taldea;
	}



	public String getMezua() {
		return mezua;
	}



	public void setMezua(String mezua) {
		this.mezua = mezua;
	}

}
