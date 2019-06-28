package nightmarethreatreis.com.github.mvp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue("glumac")
public class Glumac extends DramskiRadnik {
	@ManyToMany(mappedBy = "glumci")
	private List<Uloga> uloge = new ArrayList<>();

	public List<Uloga> getUloge() {
		return uloge;
	}
	public void setUloge(List<Uloga> uloge) {
		this.uloge = uloge;
	}
}
