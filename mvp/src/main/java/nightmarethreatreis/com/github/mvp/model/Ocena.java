package nightmarethreatreis.com.github.mvp.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Ocena {

	@EmbeddedId
	private OcenaId id;
	
	@MapsId("kupacId")
	@JoinColumn(name = "kupac_id")
	@ManyToOne
	private Kupac kupac;
	
	@MapsId("predstavaId")
	@JoinColumn(name = "predstava_id")
	@ManyToOne
	private Predstava predstava;
	
	@Column(nullable = false)
	private int ocena;

	public OcenaId getId() {
		return id;
	}
	public void setId(OcenaId id) {
		this.id = id;
	}
	public Kupac getKupac() {
		return kupac;
	}
	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}
	public Predstava getPredstava() {
		return predstava;
	}
	public void setPredstava(Predstava predstava) {
		this.predstava = predstava;
	}
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
}
