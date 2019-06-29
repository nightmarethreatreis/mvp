package nightmarethreatreis.com.github.mvp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import nightmarethreatreis.com.github.mvp.model.Uloga;
import nightmarethreatreis.com.github.mvp.model.UlogaId;

public interface UlogaRepository extends JpaRepository<Uloga, UlogaId> {

}
