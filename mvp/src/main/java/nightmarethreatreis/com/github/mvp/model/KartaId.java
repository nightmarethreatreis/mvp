package nightmarethreatreis.com.github.mvp.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class KartaId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "izvodjenje_id")
	private long izvodjenjeId;
	
	@Column(name = "sediste_id")
	private SedisteId sedisteId;
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		KartaId other = (KartaId)obj;
		return izvodjenjeId == other.izvodjenjeId &&
				Objects.equals(sedisteId, other.sedisteId);
	}
	
	public long getIzvodjenjeId() {
		return izvodjenjeId;
	}

	public void setIzvodjenjeId(long izvodjenjeId) {
		this.izvodjenjeId = izvodjenjeId;
	}

	public SedisteId getSedisteId() {
		return sedisteId;
	}

	public void setSedisteId(SedisteId sedisteId) {
		this.sedisteId = sedisteId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(izvodjenjeId, sedisteId);
	}
}
