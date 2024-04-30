package pl;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import bl.OrokorraEJB;
import dl.ErlazioaE;
import java.io.Serializable;

@Named
@ViewScoped
public class EstadistikakViewMB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB private OrokorraEJB orokorraEJB;
	
	private List<ErlazioaE> erabiltzailearenTaldeak;

	public EstadistikakViewMB() {
		// TODO Auto-generated constructor stub
	}

	
	
	public List<ErlazioaE> getErabiltzailearenTaldeak() {
		return erabiltzailearenTaldeak;
	}



	public void setErabiltzailearenTaldeak(List<ErlazioaE> erabiltzailearenTaldeak) {
		this.erabiltzailearenTaldeak = erabiltzailearenTaldeak;
	}



	public List<ErlazioaE> erabiltzailearenTaldeakLortu(int id){
		if(erabiltzailearenTaldeak!=null) {
			//erabiltzailearenTaldeak = orokorraEJB.ErabiltzailearenTaldeakDB(id);
		}
		return erabiltzailearenTaldeak;
	}
	
	public void resetView() {
		erabiltzailearenTaldeak=null;
	}
}
