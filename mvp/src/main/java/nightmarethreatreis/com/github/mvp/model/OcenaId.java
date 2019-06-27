package nightmarethreatreis.com.github.mvp.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OcenaId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5116418143745285434L;
	
	@Column(name = "kupac_id")
	private long kupacId;
	@Column(name = "predstava_id")
	private long predstavaId;
	
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
		OcenaId other = (OcenaId)obj;
		return kupacId == other.kupacId &&
				predstavaId == other.predstavaId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(kupacId, predstavaId);
	}
}
