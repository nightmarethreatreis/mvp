package nightmarethreatreis.com.github.mvp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Predstava {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "predstava_id")
	private int id;
	@Column(nullable = false)
	private String naziv;
	@Column(nullable = false)
	private int trajanje;
	@Column(nullable = false)
	private String opis;
	
	@ManyToMany
	private Set<Zanr> zanrovi = new HashSet<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public Set<Zanr> getZanrovi() {
		return zanrovi;
	}
	public void setZanrovi(Set<Zanr> zanrovi) {
		this.zanrovi = zanrovi;
	}
	
	
	
}
