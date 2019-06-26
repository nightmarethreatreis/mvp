package nightmarethreatreis.com.github.mvp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("radnik")
public class Radnik extends DataFullKorisnik {

}
