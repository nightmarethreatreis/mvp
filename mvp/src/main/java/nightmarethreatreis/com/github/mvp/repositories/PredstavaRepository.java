package nightmarethreatreis.com.github.mvp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nightmarethreatreis.com.github.mvp.model.Predstava;
import nightmarethreatreis.com.github.mvp.model.Zanr;

public interface PredstavaRepository extends JpaRepository<Predstava, Long> {
	@Query(value = "select z from Zanr z")
	public List<Zanr> getAllZanr();
	
	@Query(value = "select p.zanrovi from Predstava p where p = predstava")
	public List<Zanr> getPredstavasZanrs(@Param("predstava") Predstava predstava);
}
