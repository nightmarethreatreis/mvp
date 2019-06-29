package nightmarethreatreis.com.github.mvp.logic;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import nightmarethreatreis.com.github.mvp.model.Predstava;
import nightmarethreatreis.com.github.mvp.model.Reziser;
import nightmarethreatreis.com.github.mvp.model.Uloga;
import nightmarethreatreis.com.github.mvp.model.UlogaId;
import nightmarethreatreis.com.github.mvp.model.Zanr;
import nightmarethreatreis.com.github.mvp.repositories.PredstavaRepository;
import nightmarethreatreis.com.github.mvp.repositories.UlogaRepository;
import nightmarethreatreis.com.github.mvp.repositories.ZanrRepository;

@Component
public class PredstavaLogic {
	@Autowired
	private ZanrRepository zanrRepo;
	@Autowired
	private PredstavaRepository predstavaRepo;
	@Autowired
	private PredstavaDataValidator predstavaDataValidator;
	@Autowired
	private UlogaRepository ulogaRepo;
	
	public boolean addZanr(String naziv) {
		Zanr zanr = new Zanr();
		zanr.setNaziv(naziv);
		return zanrRepo.save(zanr) != null;
	}
	
	// Ovaj deo koda je odneo onog malo sto nije ludilo, a ostalo je u meni, by: Milos
	@Transactional
	public boolean addPredstava(String naziv, int trajanje, String opis, 
			List<Zanr> zanrovi, List<Reziser> reziseri, List<Uloga> uloge) 
			throws DataValidityException {
		boolean ok = true;
		predstavaDataValidator.validatePredstava(naziv, trajanje, opis, zanrovi, reziseri, uloge);
		Predstava predstava = new Predstava();
		predstava.setNaziv(naziv);
		predstava.setTrajanje(trajanje);
		predstava.setOpis(opis);
		predstava.setZanrovi(zanrovi);
		predstava.setReziseri(reziseri);
		predstava.setUloge(uloge);
		ok &= (predstavaRepo.save(predstava) != null);
		for(Uloga uloga: uloge) {
			uloga.setPredstava(predstava);
			uloga.setId(new UlogaId());
			uloga.getId().setPredstavaId(predstava.getId());
			ok &= (ulogaRepo.save(uloga) != null);
		}
		if(!ok) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return ok;
	}
}
