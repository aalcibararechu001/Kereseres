package dl;

import java.io.Serializable;

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
 * The persistent class for the Erlazioa database table.
 * 
 */
@Entity
@Table(name="Erlazioa")
@NamedQueries({
	@NamedQuery(name="ErlazioaE.findAll", query="SELECT e FROM ErlazioaE e"),
	@NamedQuery(name="ErlazioaE.findErabiltzailearenTaldeak", query="SELECT e FROM ErlazioaE e WHERE e.erabiltzaileaE.idErabiltzailea= :username"),
	@NamedQuery(name="ErlazioaE.findTaldearenErabiltzaileak", query="SELECT e.erabiltzaileaE FROM ErlazioaE e WHERE e.taldeaE= :taldea"),
	@NamedQuery(name="ErlazioaE.findErlazioa", query="SELECT e FROM ErlazioaE e WHERE e.erabiltzaileaE.idErabiltzailea=:idErab AND e.taldeaE.idTaldea=:idTaldea"),
})

public class ErlazioaE implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idErlazioa;

	//uni-directional many-to-one association to ErabiltzaileaE
	@ManyToOne
	@JoinColumn(name="idErabiltzailea")
	private ErabiltzaileaE erabiltzaileaE;

	//uni-directional many-to-one association to TaldeaE
	@ManyToOne
	@JoinColumn(name="idTaldea")
	private TaldeaE taldeaE;

	public ErlazioaE(int idErlazioa, ErabiltzaileaE erabiltzaileaE, TaldeaE taldeaE) {
		this.idErlazioa = idErlazioa;
		this.erabiltzaileaE = erabiltzaileaE;
		this.taldeaE = taldeaE;
	}

	public ErlazioaE() {
	}

	public int getIdErlazioa() {
		return this.idErlazioa;
	}

	public void setIdErlazioa(int idErlazioa) {
		this.idErlazioa = idErlazioa;
	}

	public ErabiltzaileaE getErabiltzaileaE() {
		return this.erabiltzaileaE;
	}

	public void setErabiltzaileaE(ErabiltzaileaE erabiltzaileaE) {
		this.erabiltzaileaE = erabiltzaileaE;
	}

	public TaldeaE getTaldeaE() {
		return this.taldeaE;
	}

	public void setTaldeaE(TaldeaE taldeaE) {
		this.taldeaE = taldeaE;
	}

}