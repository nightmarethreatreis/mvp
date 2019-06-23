package nightmarethreatreis.com.github.mvp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import nightmarethreatreis.com.github.mvp.model.Korisnik;

@RepositoryRestResource
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	@Query(value = "select k from Korisnik k where k.username=:username")
	public Korisnik getKorisnikByUsername(String username);
}
