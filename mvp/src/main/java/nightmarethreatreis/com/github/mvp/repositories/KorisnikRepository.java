package nightmarethreatreis.com.github.mvp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nightmarethreatreis.com.github.mvp.model.Admin;
import nightmarethreatreis.com.github.mvp.model.Korisnik;
import nightmarethreatreis.com.github.mvp.model.Kupac;
import nightmarethreatreis.com.github.mvp.model.Radnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	@Query(value = "select k from Korisnik k where k.username=:username")
	public Korisnik getKorisnikByUsername(@Param("username") String username);
	
	// Duplicate method, but with same naming convention
	@Query(value = "select k from Korisnik k")
	public List<Korisnik> getAllKorisnik();
	
	@Query(value = "select k from Kupac k")
	public List<Kupac> getAllKupac();
	
	@Query(value = "select a from Admin a")
	public List<Admin> getAllAdmin();
	
	@Query(value = "select r from Radnik r")
	public List<Radnik> getAllRadnik();
	
	@Query(value = "select count(a) from Admin a")
	public long getAdminCount();
}
