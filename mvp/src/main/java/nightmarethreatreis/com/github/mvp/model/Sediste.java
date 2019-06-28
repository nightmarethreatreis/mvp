package nightmarethreatreis.com.github.mvp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;

@Entity
public class Sediste {
	@EmbeddedId
	private SedisteId id;
	
	@MapsId("salaId")
	@JoinColumn(name = "sala_id")
	@ManyToOne
	private Sala sala;
	
	private int red;
	private int kolona;
	
	@OneToMany(mappedBy = "sediste")
	private List<Karta> karte = new ArrayList<> ();
	
	public List<Karta> getKarte() {
		return karte;
	}
	public void setKarte(List<Karta> karte) {
		this.karte = karte;
	}
	public SedisteId getId() {
		return id;
	}
	public void setId(SedisteId id) {
		this.id = id;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public int getRed() {
		return red;
	}
	public void setRed(int red) {
		this.red = red;
	}
	public int getKolona() {
		return kolona;
	}
	public void setKolona(int kolona) {
		this.kolona = kolona;
	}
}
