package nightmarethreatreis.com.github.mvp.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class SedisteId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "sala_id")
	private long salaId;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sediste_id")
	private long sedisteId;
	
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
		SedisteId other = (SedisteId)obj;
		return salaId == other.salaId &&
				sedisteId == other.sedisteId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(salaId, sedisteId);
	}
}
