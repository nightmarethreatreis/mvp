package nightmarethreatreis.com.github.mvp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue("reziser")
public class Reziser extends DramskiRadnik {
	@ManyToMany(mappedBy = "reziseri")
	private List<Predstava> predstave = new ArrayList<>();

	public List<Predstava> getPredstave() {
		return predstave;
	}
	public void setPredstave(List<Predstava> predstave) {
		this.predstave = predstave;
	}
}
