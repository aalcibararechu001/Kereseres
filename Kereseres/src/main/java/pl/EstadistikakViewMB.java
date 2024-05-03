package pl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;


import bl.OrokorraEJB;
import dl.ErlazioaE;
import dl.KereserE;

import java.io.Serializable;

@Named
@ViewScoped
public class EstadistikakViewMB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB private OrokorraEJB orokorraEJB;
	

	public EstadistikakViewMB() {
		// TODO Auto-generated constructor stub
	}

	public void estatistikak_sortu(TaldeAtazakMB talde) {
		List<KereserE> zereginak = orokorraEJB.taldekoEgindakoZereginakLortuDB(talde.getErabiltzailearenTaldea().getIdTaldea());
		List<datuak> dataList = new ArrayList<>();
		for(KereserE kereser : zereginak){
			String orduak = Integer.toString(kereser.getOrdu_kopurua());
			datuak datua = new datuak(kereser.getIzena(), orduak );
			dataList.add(datua);
		}
		String filePath = "Kereseres/src/main/webapp/ESTATISTIKAK/data.json";
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            Gson gson = new Gson();
            String jsonData = gson.toJson(dataList);
            fileWriter.write(jsonData);
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }
	
}
