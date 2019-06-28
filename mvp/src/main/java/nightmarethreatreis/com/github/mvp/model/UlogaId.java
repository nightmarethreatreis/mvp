package nightmarethreatreis.com.github.mvp.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class UlogaId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "predstava_id")
	private long predstavaId;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uloga_id")
	private long ulogaId;
	
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
		UlogaId other = (UlogaId)obj;
		return ulogaId == other.ulogaId &&
				predstavaId == other.predstavaId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(ulogaId, predstavaId);
	}
}
