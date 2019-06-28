package nightmarethreatreis.com.github.mvp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Izvodjenje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date vreme;
	
	private int referentnaCenaKarte;
	
	@ManyToOne
	private Predstava predstava;
	
	@ManyToOne
	private Sala sala;
	
	@OneToMany(mappedBy = "izvodjenje")
	private List<Karta> karte = new ArrayList<>();

	public List<Karta> getKarte() {
		return karte;
	}
	public void setKarte(List<Karta> karte) {
		this.karte = karte;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getVreme() {
		return vreme;
	}
	public void setVreme(Date vreme) {
		this.vreme = vreme;
	}
	public int getReferentnaCenaKarte() {
		return referentnaCenaKarte;
	}
	public void setReferentnaCenaKarte(int referentnaCenaKarte) {
		this.referentnaCenaKarte = referentnaCenaKarte;
	}
	public Predstava getPredstava() {
		return predstava;
	}
	public void setPredstava(Predstava predstava) {
		this.predstava = predstava;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}

	
}
