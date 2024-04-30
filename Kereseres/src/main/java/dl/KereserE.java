package dl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Kereser database table.
 * 
 */
@Entity
@Table(name="Kereser")
@NamedQueries({
	@NamedQuery(name="KereserE.findAll", query="SELECT k FROM KereserE k"),
	@NamedQuery(name="KereserE.findEginda", query="SELECT k FROM KereserE k WHERE k.eginda= true"),
	@NamedQuery(name="KereserE.findEsleitzeke", query="SELECT k FROM KereserE k WHERE k.esleituta= false AND k.erlazioaE.taldeaE.idTaldea=:idTaldea"),
	@NamedQuery(name="KereserE.findEsleituta", query="SELECT k FROM KereserE k WHERE k.esleituta = true AND k.eginda= false AND k.erlazioaE.taldeaE.idTaldea=:idTaldea"),
	@NamedQuery(name="KereserE.findErabiltzailearenak", query="SELECT k FROM KereserE k WHERE k.erlazioaE.erabiltzaileaE.idErabiltzailea= :id_erab")
})

public class KereserE implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKereser;

	@Column(columnDefinition = "TINYINT")
	private boolean eginda;

	@Column(columnDefinition = "TINYINT")
	private boolean esleituta;

	@Column(name="Izena")
	private String izena;

	@Column(name="`Ordu kopurua`")
	private int ordu_kopurua;

	//uni-directional many-to-one association to ErlazioaE
	@ManyToOne
	@JoinColumn(name="idErlazioa")
	private ErlazioaE erlazioaE;

	public KereserE() {
	}
	
	public KereserE(int idKereser, boolean eginda, boolean esleituta, String izena, int ordu_kopurua,
			ErlazioaE erlazioaE) {
		this.idKereser = idKereser;
		this.eginda = eginda;
		this.esleituta = esleituta;
		this.izena = izena;
		this.ordu_kopurua = ordu_kopurua;
		this.erlazioaE = erlazioaE;
	}

	public int getIdKereser() {
		return this.idKereser;
	}

	public void setIdKereser(int idKereser) {
		this.idKereser = idKereser;
	}

	public boolean getEginda() {
		return this.eginda;
	}

	public void setEginda(boolean eginda) {
		this.eginda = eginda;
	}

	public boolean getEsleituta() {
		return this.esleituta;
	}

	public void setEsleituta(boolean esleituta) {
		this.esleituta = esleituta;
	}

	public String getIzena() {
		return this.izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public int getOrdu_kopurua() {
		return this.ordu_kopurua;
	}

	public void setOrdu_kopurua(int ordu_kopurua) {
		this.ordu_kopurua = ordu_kopurua;
	}

	public ErlazioaE getErlazioaE() {
		return this.erlazioaE;
	}

	public void setErlazioaE(ErlazioaE erlazioaE) {
		this.erlazioaE = erlazioaE;
	}

}