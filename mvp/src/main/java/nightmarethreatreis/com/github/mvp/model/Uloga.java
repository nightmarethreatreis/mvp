package nightmarethreatreis.com.github.mvp.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Uloga {
	
	@EmbeddedId
	private UlogaId id;
	
	@MapsId("predstavaId")
	@JoinColumn(name = "predstava_id")
	@ManyToOne
	private Predstava predstava;
	
	private boolean glavna;
	private String naziv;
	
	@ManyToMany
	private List<Glumac> glumci = new LinkedList<>();

	public UlogaId getId() {
		return id;
	}
	public void setId(UlogaId id) {
		this.id = id;
	}
	public Predstava getPredstava() {
		return predstava;
	}
	public void setPredstava(Predstava predstava) {
		this.predstava = predstava;
	}
	public boolean isGlavna() {
		return glavna;
	}
	public void setGlavna(boolean glavna) {
		this.glavna = glavna;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public List<Glumac> getGlumci() {
		return glumci;
	}
	public void setGlumci(List<Glumac> glumci) {
		this.glumci = glumci;
	}
	
}
