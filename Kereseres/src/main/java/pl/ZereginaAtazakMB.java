package pl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import bl.OrokorraEJB;
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
		List<ErlazioaE> erlazioaDB = orokorraEJB.erlazioakBilatuDB(atazakS.getErabiltzailea().getIdErabiltzailea(), atazakT.getErabiltzailearenTaldea().getIdTaldea());
		
		if(erlazioaDB.size()==0) {
			ErlazioaE erlazioa = new ErlazioaE(0, atazakS.getErabiltzailea(), atazakT.getErabiltzailearenTaldea());
			KereserE kereser = new KereserE(0,false,false,form.getIzena(),form.getOrdukop(),erlazioa);
			orokorraEJB.zereginBerriaSartuDB(kereser,erlazioa, 0);
		}else {
			KereserE kereser = new KereserE(0,false,false,form.getIzena(),form.getOrdukop(),erlazioaDB.get(0));
			orokorraEJB.zereginBerriaSartuDB(kereser,erlazioaDB.get(0),1);
		}
		
		form.resetForm();
		view.resetView();
	}
	
	public void zereginaEgindaJarriDB(KereserE kereserDB, ZereginakViewMB view,TaldeAtazakMB talde) {
		orokorraEJB.zereginaEgindaJarriDB(kereserDB);
		estatistikak_sortu(talde);
		view.resetView();
	}
	
	private void estatistikak_sortu(TaldeAtazakMB talde) {
		
		List<KereserE> zereginak = orokorraEJB.taldekoEgindakoZereginakLortuDB(talde.getErabiltzailearenTaldea().getIdTaldea());
		List<datuak> dataList = new ArrayList<>();
		for(KereserE kereser : zereginak){
			String orduak = Integer.toString(kereser.getOrdu_kopurua());
			datuak datua = new datuak(kereser.getErlazioaE().getErabiltzaileaE().getIzena(), orduak );
			dataList.add(datua);
		}
		System.out.print("Honea aiau da");
		String filePath = "data.json";
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            String jsonData=listToJson(dataList);
            fileWriter.write(jsonData);
        } catch (IOException e) {
        	System.out.print("Arazoa fitxategia irekitzerakoan.");
            e.printStackTrace(); // Handle or log the exception as needed
        }
	}
	private String listToJson(List<datuak> lista) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        for (int i = 0; i < lista.size(); i++) {
            datuak data = lista.get(i);
            json.append("{\"month\":\"").append(data.getIzena()).append("\",\"income\":").append(data.getOrduak()).append("}");
            if (i < lista.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }
	
	public void zereginaEsleitutaJarriDB(KereserE kereserDB, ZereginakViewMB view,int idErab, TaldeAtazakMB taldeAtazak) {
		TaldeaE taldea=new TaldeaE(0,"kaka","kakita");
		// List<ErabiltzaileaE> erabiltzaileak = new ArrayList<ErabiltzaileaE>();
		orokorraEJB.zereginaEsleitutaJarriDB(kereserDB, idErab,taldeAtazak.getErabiltzailearenTaldea(),view.getErabiltzaileakDB());
		view.resetView();
		// form.resetForm();
	}
	
	public void zereginaEzabatuDB(KereserE zeregina, ZereginakViewMB view) {
		orokorraEJB.zereginaEzabatuDB(zeregina.getIdKereser());
		view.resetView();
	}
	
}
