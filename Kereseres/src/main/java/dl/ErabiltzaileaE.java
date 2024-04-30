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
 * The persistent class for the Erabiltzailea database table.
 * 
 */
@Entity
@Table(name="Erabiltzailea")
@NamedQueries({
	@NamedQuery(name="ErabiltzaileaE.findAll", query="SELECT e FROM ErabiltzaileaE e"),
	@NamedQuery(name="ErabiltzaileaE.findErab", query="SELECT e FROM ErabiltzaileaE e WHERE e.username=:username")
})
public class ErabiltzaileaE implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idErabiltzailea;

	@Column(name="Izena")
	private String izena;

	@Column(name="Pasahitza")
	private String pasahitza;

	private String role;

	@Column(name="Username")
	private String username;

	public ErabiltzaileaE() {
	}

	public int getIdErabiltzailea() {
		return this.idErabiltzailea;
	}

	public void setIdErabiltzailea(int idErabiltzailea) {
		this.idErabiltzailea = idErabiltzailea;
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

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ErabiltzaileaE(int idErabiltzailea, String izena, String pasahitza, String role, String username) {
		this.idErabiltzailea = idErabiltzailea;
		this.izena = izena;
		this.pasahitza = pasahitza;
		this.role = role;
		this.username = username;
	}
	

}