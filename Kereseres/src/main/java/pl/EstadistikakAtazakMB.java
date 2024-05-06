package pl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import bl.OrokorraEJB;
import dl.ErabiltzaileaE;
import dl.KereserE;
import dl.TaldeaE;

@Named
@ViewScoped
public class EstadistikakAtazakMB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB private OrokorraEJB orokorraEJB;

	public EstadistikakAtazakMB() {
		// TODO Auto-generated constructor stub
	}

	public void estatistikak_sortu(TaldeaE talde) {	
		List <ErabiltzaileaE> erabiltzaileak = orokorraEJB.taldearenErabiltzaileakLortuDB(talde);
		List<datuak> dataList = new ArrayList<>();
		for(ErabiltzaileaE erabiltzaile : erabiltzaileak) {
			List<KereserE> keresereak = orokorraEJB.erabiltzailearenEgindakoakLortuDB(talde.getIdTaldea(), erabiltzaile.getIdErabiltzailea());
			int orduak = 0;
			for (KereserE keresere: keresereak) {
				orduak+=keresere.getOrdu_kopurua();
			}
			String ordu = Integer.toString(orduak);
			datuak datua = new datuak(erabiltzaile.getIzena(),ordu);
			dataList.add(datua);
		}
		System.out.print("Honea aiau da");
		String filePath = "/users/1013645/git/Kereseres/Kereseres/src/main/webapp/data.json";
		
		
		
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
        	fileWriter.write("");
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
}
