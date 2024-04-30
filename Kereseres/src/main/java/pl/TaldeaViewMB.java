package pl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import bl.OrokorraEJB;
import dl.ErlazioaE;
import dl.TaldeaE;

@Named
@ViewScoped
public class TaldeaViewMB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB private OrokorraEJB orokorraEJB;
	
	private List<ErlazioaE> erabiltzailearenTaldeak;
	private List<TaldeaE> bilatutakoTaldeak;
	private int kodea=0;

	public TaldeaViewMB() {
		// TODO Auto-generated constructor stub
	}
	
	public List<ErlazioaE> getErabiltzailearenTaldeak() {
		return erabiltzailearenTaldeak;
	}



	public void setErabiltzailearenTaldeak(List<ErlazioaE> erabiltzailearenTaldeak) {
		this.erabiltzailearenTaldeak = erabiltzailearenTaldeak;
	}



	public List<ErlazioaE> erabiltzailearenTaldeakLortu(int id){
		if(erabiltzailearenTaldeak==null) {
			erabiltzailearenTaldeak = orokorraEJB.erabiltzailearenTaldeakDB(id);
		}
		return erabiltzailearenTaldeak;
	}
	public List<TaldeaE> bilatutakoTaldeakLortu(String bilatutakoa){
		setKodea(1);
		resetView();
		bilatutakoTaldeak = orokorraEJB.taldeaBilatuDB(bilatutakoa);
		return bilatutakoTaldeak;
	}
	
	public void resetView() {
		erabiltzailearenTaldeak=null;
		bilatutakoTaldeak=null;
	}

	public int getKodea() {
		return kodea;
	}

	public void setKodea(int kodea) {
		this.kodea = kodea;
	}

	public List<TaldeaE> getBilatutakoTaldeak() {
		return bilatutakoTaldeak;
	}

	public void setBilatutakoTaldeak(List<TaldeaE> bilatutakoTaldeak) {
		this.bilatutakoTaldeak = bilatutakoTaldeak;
	}
	
	
}
