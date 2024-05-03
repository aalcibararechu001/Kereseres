package pl;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.FileWriter;
import java.io.IOException;
//import com.google.gson.Gson;
//import com.google.gson.*;
import pl.datuak;


import bl.OrokorraEJB;
import dl.ErlazioaE;
import dl.KereserE;

import java.io.Serializable;

@Named
@ViewScoped
public class EstadistikakViewMB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB private OrokorraEJB orokorraEJB;
	private int kodea=0;

	public EstadistikakViewMB() {
		// TODO Auto-generated constructor stub
	}

	public void estatistikak_sortu(TaldeAtazakMB talde) {
		if(kodea==1) {
			kodea=0;
		}else {
			kodea=1;
		}
		
		List<KereserE> zereginak = orokorraEJB.taldekoEgindakoZereginakLortuDB(talde.getErabiltzailearenTaldea().getIdTaldea());
		List<datuak> dataList = new ArrayList<>();
		for(KereserE kereser : zereginak){
			String orduak = Integer.toString(kereser.getOrdu_kopurua());
			datuak datua = new datuak(kereser.getIzena(), orduak );
			dataList.add(datua);
		}
		System.out.print("Honea aiau da");
		String filePath = "/users/1009719/git/Kereseres/Kereseres/src/main/webapp/data.json";
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

	public int getKodea() {
		return kodea;
	}

	public void setKodea(int kodea) {
		this.kodea = kodea;
	}
}
