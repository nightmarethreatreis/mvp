package nightmarethreatreis.com.github.mvp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "zanimanje", discriminatorType = DiscriminatorType.STRING, length = 10)
public abstract class DramskiRadnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String ime;
	private String prezime;
	private String jmbg;
	
	@Temporal(TemporalType.DATE)
	private Date datumRodjenja;
	
	@Column(name = "zanimanje", insertable = false, updatable = false)
	private String zanimanje;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getZanimanje() {
		return zanimanje;
	}
	public void setZanimanje(String zanimanje) {
		this.zanimanje = zanimanje;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s (%s)", zanimanje, ime, prezime, jmbg);
	}
}
