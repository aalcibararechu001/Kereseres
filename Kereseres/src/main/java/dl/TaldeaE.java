package dl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Taldea database table.
 * 
 */
@Entity
@Table(name="Taldea")
@NamedQueries({
	@NamedQuery(name="TaldeaE.findAll", query="SELECT t FROM TaldeaE t"),
	@NamedQuery(name="TaldeaE.findTaldeak", query="SELECT t FROM TaldeaE t WHERE t.izena=:izena")
})

public class TaldeaE implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTaldea;

	@Column(name="Izena")
	private String izena;

	@Column(name="Pasahitza")
	private String pasahitza;

	public TaldeaE() {
	}
	
	public TaldeaE(int idTaldea, String izena, String pasahitza) {
		this.idTaldea = idTaldea;
		this.izena = izena;
		this.pasahitza = pasahitza;
	}

	public int getIdTaldea() {
		return this.idTaldea;
	}

	public void setIdTaldea(int idTaldea) {
		this.idTaldea = idTaldea;
	}

	public String getIzena() {
		return this.izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getPasahitza() {
		return this.pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

}
