package nightmarethreatreis.com.github.mvp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin extends Korisnik {
	
}
