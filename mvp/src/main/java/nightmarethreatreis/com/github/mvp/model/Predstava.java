package nightmarethreatreis.com.github.mvp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

@Entity
public class Predstava {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "predstava_id")
	private long id;
	
	@Column(nullable = false)
	private String naziv;
	@Column(nullable = false)
	private int trajanje;
	@Column(nullable = false)
	@Type(type = "text")
	private String opis;
	
	@ManyToMany
	private List<Zanr> zanrovi = new ArrayList<>();
	
	@OneToMany(mappedBy = "predstava")
	private List<Uloga> uloge = new ArrayList<>();
	
	@ManyToMany
	private List<Reziser> reziseri = new ArrayList<>();
	
	@OneToMany(mappedBy = "predstava")
	private List<Izvodjenje> izvodjenja = new ArrayList<>();
	
	public List<Uloga> getUloge() {
		return uloge;
	}
	public void setUloge(List<Uloga> uloge) {
		this.uloge = uloge;
	}
	public List<Reziser> getReziseri() {
		return reziseri;
	}
	public void setReziseri(List<Reziser> reziseri) {
		this.reziseri = reziseri;
	}
	public List<Izvodjenje> getIzvodjenja() {
		return izvodjenja;
	}
	public void setIzvodjenja(List<Izvodjenje> izvodjenja) {
		this.izvodjenja = izvodjenja;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
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
	public List<Zanr> getZanrovi() {
		return zanrovi;
	}
	public void setZanrovi(List<Zanr> zanrovi) {
		this.zanrovi = zanrovi;
	}
	public String zanroviAsString() {
		if(getZanrovi().size() > 0) {
			return String.join(", ", getZanrovi().stream().map(Zanr::getNaziv).collect(Collectors.toList()));
		}
		else {
			return "/";
		}
	}
	public String reziseriAsString() {
		if(getReziseri().size() > 0) {
			return String.join(", ", getReziseri().stream().map(reziser -> reziser.getIme() + " " + reziser.getPrezime()).collect(Collectors.toList()));
		}
		else {
			return "/";
		}
	}
	public String ulogeAsString() {
		if(getUloge().size() > 0) {
			return String.join(", ", getUloge().stream().map(uloga -> {
				String result = uloga.getNaziv();
				if(uloga.getGlumci().size() > 0) {
					result += "(";
					result += String.join(", ", uloga.getGlumci().stream().map(glumac -> glumac.getIme() + " " + glumac.getPrezime()).collect(Collectors.toList()));
					result += ")";
				}
				return result;
			}).collect(Collectors.toList()));
		}
		else {
			return "/";
		}
	}
	
	
	
}
