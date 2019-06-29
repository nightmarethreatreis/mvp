package nightmarethreatreis.com.github.mvp.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.GenericGenerator;

@Embeddable
public class UlogaId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "predstava_id")
	private long predstavaId;
	
	// Treba naci pametnije resenje za ovo
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "uloga_id")
	private UUID ulogaId = UUID.randomUUID();
	
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
		return Objects.equals(ulogaId, other.ulogaId) &&
				predstavaId == other.predstavaId;
	}

	public long getPredstavaId() {
		return predstavaId;
	}

	public void setPredstavaId(long predstavaId) {
		this.predstavaId = predstavaId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ulogaId, predstavaId);
	}

	public UUID getUlogaId() {
		return ulogaId;
	}

	public void setUlogaId(UUID ulogaId) {
		this.ulogaId = ulogaId;
	}

}
