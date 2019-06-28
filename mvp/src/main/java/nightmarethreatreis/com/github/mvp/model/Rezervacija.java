package nightmarethreatreis.com.github.mvp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Rezervacija {
	private static final int SIFRA_LENGTH = 10;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date vremeRezervacije;
	private boolean izdato;
	@Column(length = SIFRA_LENGTH)
	private String sifraRezervacije;
	
	@OneToMany(mappedBy = "rezervacija")
	private List<Karta> karte = new ArrayList<>();
	
	@ManyToOne
	private Kupac kupac;
	
	@PrePersist
	protected void onCreate() {
		setSifraRezervacije(java.util.UUID.randomUUID().toString().substring(0, SIFRA_LENGTH));
	}

	public List<Karta> getKarte() {
		return karte;
	}
	public void setKarte(List<Karta> karte) {
		this.karte = karte;
	}
	public Kupac getKupac() {
		return kupac;
	}
	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getVremeRezervacije() {
		return vremeRezervacije;
	}
	public void setVremeRezervacije(Date vremeRezervacije) {
		this.vremeRezervacije = vremeRezervacije;
	}
	public boolean isIzdato() {
		return izdato;
	}
	public void setIzdato(boolean izdato) {
		this.izdato = izdato;
	}
	public String getSifraRezervacije() {
		return sifraRezervacije;
	}
	public void setSifraRezervacije(String sifraRezervacije) {
		this.sifraRezervacije = sifraRezervacije;
	}
}
