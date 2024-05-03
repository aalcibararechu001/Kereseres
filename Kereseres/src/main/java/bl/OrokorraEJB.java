
/*
 * Copyright (C) 2013-2022 Maider Huarte Arrayago (maider.huarte@ehu.eus)
 * 
 * OrokorraEJB.java is part of ZTA_P6-1_LAGUNTZA.zip.
 * 
 * ZTA_P6-1_LAGUNTZA.zip is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 or any later version.
 * 
 * ZTA_P6-1_LAGUNTZA.zip is distributed for LEARNING PURPOSES ONLY, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details <http://www.gnu.org/licenses/>.
 */

package bl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dl.ErabiltzaileaE;
import dl.ErlazioaE;
import dl.KereserE;
import dl.TaldeaE;


@Stateless
@LocalBean
public class OrokorraEJB {
	@PersistenceContext private EntityManager em;	
	
	public List<KereserE> egindakoZereginakLortuDB(){
		return (List<KereserE>) em.createNamedQuery("KereserE.findEginda").getResultList();
	}

	public List<KereserE> taldekoEgindakoZereginakLortuDB(int idTaldea){
		return (List<KereserE>) em.createNamedQuery("KereserE.findTaldekoEginda").setParameter("idTaldea", idTaldea).getResultList();
	}
	
	public List<KereserE> esleituGabekoZereginakLortuDB(int idTaldea){
		return (List<KereserE>) em.createNamedQuery("KereserE.findEsleitzeke").setParameter("idTaldea", idTaldea).getResultList();
	}
	
	public List<KereserE> esleitutakoZereginakLortuDB(int idTaldea){
		return (List<KereserE>) em.createNamedQuery("KereserE.findEsleituta").setParameter("idTaldea", idTaldea).getResultList();
	}
	
	public List<ErlazioaE> erabiltzailearenTaldeakDB(int user_id){
		return(List<ErlazioaE>) em.createNamedQuery("ErlazioaE.findErabiltzailearenTaldeak").setParameter("username",user_id ).getResultList();
	}
	public List<ErabiltzaileaE> erabiltzaileaBilatuDB(String username){
		return(List<ErabiltzaileaE>) em.createNamedQuery("ErabiltzaileaE.findErab").setParameter("username", username).getResultList();
	}
	public List<ErabiltzaileaE> erabiltzaileakLortuDB(){
		return (List<ErabiltzaileaE>) em.createNamedQuery("ErabiltzaileaE.findAll").getResultList();

	}
	public List<ErabiltzaileaE> taldearenErabiltzaileakLortuDB(TaldeaE taldeaE){
		return (List<ErabiltzaileaE>) em.createNamedQuery("ErlazioaE.findTaldearenErabiltzaileak").setParameter("taldea", taldeaE).getResultList();

	}
	
	public List<ErlazioaE> erlazioakBilatuDB(int idErab, int idTaldea){
		return(List<ErlazioaE>) em.createNamedQuery("ErlazioaE.findErlazioa").setParameter("idErab", idErab).setParameter("idTaldea", idTaldea).getResultList();
	}
	public void zereginaEgindaJarriDB(KereserE kereserDB) {
		kereserDB.setEginda(true);
		em.merge(kereserDB);
		
	}
	
	public void erabiltzaileBerriaDB(ErabiltzaileaE erabiltzaileberria) {
		em.persist(erabiltzaileberria);
	}
	@SuppressWarnings("unchecked")
	public void zereginaEsleitutaJarriDB(KereserE kereserDB, int idErab, TaldeaE taldea,List<ErabiltzaileaE> taldeko_erabiltzaileak) {
	
		List<ErlazioaE> erlazioaDB = em.createNamedQuery("ErlazioaE.findErlazioa").setParameter("idErab", taldeko_erabiltzaileak.get(idErab).getIdErabiltzailea()).setParameter("idTaldea", taldea.getIdTaldea()).getResultList();
		List<ErlazioaE> erlazioak = em.createNamedQuery("ErlazioaE.findAll").getResultList();
		
		kereserDB.setEsleituta(true);
		kereserDB.setErlazioaE(erlazioaDB.get(0));
		em.merge(kereserDB);
		
	}
	public void zereginBerriaSartuDB(KereserE kereserDB, ErlazioaE erlazioa, int kode) {
		if(kode==0) {
			em.persist(erlazioa);
			em.persist(kereserDB);
		}else {
			em.persist(kereserDB);
		}
		
		
	}

	public void taldeBerriaSortuDB(TaldeaE taldea,ErabiltzaileaE user){
		em.persist(taldea);
		ErlazioaE erlazioa = new ErlazioaE(0,user,taldea);
		em.persist(erlazioa);
	}

	public List<TaldeaE> taldeaBilatuDB(String taldea){
		return(List<TaldeaE>) em.createNamedQuery("TaldeaE.findTaldeak").setParameter("izena", taldea).getResultList();
	}

	public void erabiltzaileaTalderaBatu(TaldeaE taldea, ErabiltzaileaE erab){
		System.out.println(taldea.getIzena());
		System.out.println(erab.getIzena());
		ErlazioaE erlazioaDB = new ErlazioaE(0,erab,taldea);
		em.persist(erlazioaDB);
	}
	
	public void zereginaEzabatuDB(int idKereser) {
		KereserE kereserDB = em.find(KereserE.class, idKereser);
		em.remove(kereserDB);
		
		
	}
}
