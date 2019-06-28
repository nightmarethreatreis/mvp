package nightmarethreatreis.com.github.mvp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nightmarethreatreis.com.github.mvp.model.DramskiRadnik;
import nightmarethreatreis.com.github.mvp.model.Glumac;
import nightmarethreatreis.com.github.mvp.model.Reziser;

public interface DramskiRadnikRepository extends JpaRepository<DramskiRadnik, Long> {
	@Query("select g from Glumac g")
	public List<Glumac> getAllGlumac();
	
	@Query("select r from Reziser r")
	public List<Reziser> getAllReziser();
	
	@Query("select dr from DramskiRadnik dr")
	public List<DramskiRadnik> getAllDramskiRadnik();
}
