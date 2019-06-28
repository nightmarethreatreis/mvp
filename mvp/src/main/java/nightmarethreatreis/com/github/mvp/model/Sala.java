package nightmarethreatreis.com.github.mvp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sala {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String naziv;
	
	@OneToMany(mappedBy = "sala")
	private List<Izvodjenje> izvodjenja = new ArrayList<>();
	
	@OneToMany(mappedBy = "sala")
	private List<Sediste> sedista = new ArrayList<>();

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public List<Izvodjenje> getIzvodjenja() {
		return izvodjenja;
	}
	public void setIzvodjenja(List<Izvodjenje> izvodjenja) {
		this.izvodjenja = izvodjenja;
	}
	public List<Sediste> getSedista() {
		return sedista;
	}
	public void setSedista(List<Sediste> sedista) {
		this.sedista = sedista;
	}
}
