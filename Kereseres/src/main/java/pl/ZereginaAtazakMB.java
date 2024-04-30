package pl;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import bl.OrokorraEJB;
import dl.ErabiltzaileaE;
import dl.ErlazioaE;
import dl.KereserE;
import dl.TaldeaE;
@Named
@SessionScoped
public class ZereginaAtazakMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB private OrokorraEJB orokorraEJB;
	

	public ZereginaAtazakMB() {
		// TODO Auto-generated constructor stub
	}
	public void zereginaEzabatuDB() {
		
	}

	public void zereginBerriaSartuDB(ZereginaFormMB form, ZereginakViewMB view, TaldeAtazakMB atazakT, SesioaAtazakMB atazakS) {
		ErlazioaE erlazioa = new ErlazioaE(0, atazakS.getErabiltzailea(), atazakT.getErabiltzailearenTaldea());
		System.out.println(erlazioa.getErabiltzaileaE().getIzena());
		KereserE kereser = new KereserE(0,false,false,form.getIzena(),form.getOrdukop(),erlazioa);
		System.out.println(kereser.getIzena());
		orokorraEJB.zereginBerriaSartuDB(kereser,erlazioa);
		form.resetForm();
		view.resetView();
	}
	
	public void zereginaEgindaJarriDB(KereserE kereserDB, ZereginakViewMB view) {
		orokorraEJB.zereginaEgindaJarriDB(kereserDB);
		view.resetView();
	}
	
	public void zereginaEsleitutaJarriDB(KereserE kereserDB, ZereginakViewMB view, ZereginaFormMB form, TaldeAtazakMB taldeAtazak) {
		TaldeaE taldea=new TaldeaE(0,"kaka","kakita");
		orokorraEJB.zereginaEsleitutaJarriDB(kereserDB, form.getIdErab()+1,taldea);
		view.resetView();
		form.resetForm();
	}
	
}
