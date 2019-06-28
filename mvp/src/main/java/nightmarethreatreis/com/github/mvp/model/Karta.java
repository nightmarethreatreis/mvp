package nightmarethreatreis.com.github.mvp.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Karta {
	@EmbeddedId
	private KartaId id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date vremeKupovine;
	
	@MapsId("izvodjenjeId")
	@JoinColumn(name = "izvodjenje_id")
	@ManyToOne
	private Izvodjenje izvodjenje;
	
	@MapsId("sedisteId")
	@JoinColumn(name = "sediste_id")
	@ManyToOne
	private Sediste sediste;
	
	@ManyToOne
	private Rezervacija rezervacija;

	public KartaId getId() {
		return id;
	}
	public void setId(KartaId id) {
		this.id = id;
	}
	public Date getVremeKupovine() {
		return vremeKupovine;
	}
	public void setVremeKupovine(Date vremeKupovine) {
		this.vremeKupovine = vremeKupovine;
	}
	public Izvodjenje getIzvodjenje() {
		return izvodjenje;
	}
	public void setIzvodjenje(Izvodjenje izvodjenje) {
		this.izvodjenje = izvodjenje;
	}
	public Sediste getSediste() {
		return sediste;
	}
	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}
}
