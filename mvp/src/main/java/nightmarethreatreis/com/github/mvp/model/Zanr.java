package nightmarethreatreis.com.github.mvp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Zanr {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true)
	private String naziv;
	
	@ManyToMany(mappedBy = "zanrovi")
	private List<Predstava> predstave = new ArrayList<>();
	
	public List<Predstava> getPredstave() {
		return predstave;
	}
	public void setPredstave(List<Predstava> predstave) {
		this.predstave = predstave;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	@Override
	public String toString() {
		return "Zanr " + naziv;
	}
}
