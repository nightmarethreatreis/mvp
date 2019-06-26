package nightmarethreatreis.com.github.mvp.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DataFullKorisnik extends Korisnik {
	@Column(nullable = false)
	private String ime;
	@Column(nullable = false)
	private String prezime;
	
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
}
