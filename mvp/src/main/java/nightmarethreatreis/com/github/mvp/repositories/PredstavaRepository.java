package nightmarethreatreis.com.github.mvp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nightmarethreatreis.com.github.mvp.model.Predstava;

public interface PredstavaRepository extends JpaRepository<Predstava, Long> {
	@Query(value = "select p from Predstava p left join fetch p.zanrovi")
	public List<Predstava> getAllPredstavaWithZanrs();
	
	@Query(value ="select distinct p from Predstava p where p.id = :id")
	public Predstava fetchFullById(@Param("id") long id);
}
