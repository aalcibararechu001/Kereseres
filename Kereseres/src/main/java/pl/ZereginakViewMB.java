package pl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import bl.OrokorraEJB;
import dl.ErabiltzaileaE;
import dl.KereserE;

@Named
@ViewScoped
public class ZereginakViewMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB private OrokorraEJB orokorraEJB;
	private List<KereserE> kereserDB;
	private List<ErabiltzaileaE> erabiltzaileakDB;
	private List<KereserE> keresergabeDB;

	public List<ErabiltzaileaE> getErabiltzaileakDB() {
		return erabiltzaileakDB;
	}
	public void setErabiltzaileakDB(List<ErabiltzaileaE> erabiltzaileakDB) {
		this.erabiltzaileakDB = erabiltzaileakDB;
	}
	public ZereginakViewMB() {
		// TODO Auto-generated constructor stub
	}
	public List<KereserE> esleitutakoZereginakLortuDB(TaldeAtazakMB talde_atazak) {
		if(kereserDB == null) {
			kereserDB = orokorraEJB.esleitutakoZereginakLortuDB(talde_atazak.getErabiltzailearenTaldea().getIdTaldea());
		}
		return kereserDB;
		
	}
	
	public List<KereserE> esleituGabekoZereginakLortuDB(TaldeAtazakMB atazak) {
		if(keresergabeDB == null) {
			keresergabeDB = orokorraEJB.esleituGabekoZereginakLortuDB(atazak.getErabiltzailearenTaldea().getIdTaldea());
		}
		return keresergabeDB;
		
	}
	public List<ErabiltzaileaE> erabiltzaileakLortuDB() {
		if(erabiltzaileakDB == null) {
			erabiltzaileakDB = orokorraEJB.erabiltzaileakLortuDB();
		}
		return erabiltzaileakDB;
	}
	public List<ErabiltzaileaE> taldearenErabiltzaileakLortuDB(TaldeAtazakMB taldeAtazak) {
		if(erabiltzaileakDB == null) {
			erabiltzaileakDB = orokorraEJB.taldearenErabiltzaileakLortuDB(taldeAtazak.getErabiltzailearenTaldea());
		}
		return erabiltzaileakDB;
	}
	public List<KereserE> getKereserDB() {
		return kereserDB;
	}
	public void setKereserDB(List<KereserE> kereserDB) {
		this.kereserDB = kereserDB;
	}
	
	public List<KereserE> getKeresergabeDB() {
		return keresergabeDB;
	}
	public void setKeresergabeDB(List<KereserE> keresergabeDB) {
		this.keresergabeDB = keresergabeDB;
	}


	public void resetView() {
		kereserDB = null;
		keresergabeDB = null;
	}
}
