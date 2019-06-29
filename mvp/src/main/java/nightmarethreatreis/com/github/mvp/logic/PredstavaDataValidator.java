package nightmarethreatreis.com.github.mvp.logic;

import java.util.List;

import org.springframework.stereotype.Component;

import nightmarethreatreis.com.github.mvp.model.Reziser;
import nightmarethreatreis.com.github.mvp.model.Uloga;
import nightmarethreatreis.com.github.mvp.model.Zanr;

@Component
public class PredstavaDataValidator {
	public void validateNaziv(String naziv) throws DataValidityException {
		if(naziv.length() < 3) {
			throw new DataValidityException("Naziv predstave mora biti duzine barem 3");
		}
	}
	
	public void validateTrajanje(int trajanje) throws DataValidityException {
		if(trajanje < 5) {
			throw new DataValidityException("Predstava mora trajati barem 5 minuta");
		}
	}
	
	public void validateOpis(String opis) throws DataValidityException {
		
	}
	
	public void validateZanrove(List<Zanr> zanrovi) throws DataValidityException {
		if(zanrovi.isEmpty()) {
			throw new DataValidityException("Predstava mora pripadati barem jednom zanru");
		}
	}
	
	public void validateRezisere(List<Reziser> reziseri) throws DataValidityException {
		if(reziseri.isEmpty()) {
			throw new DataValidityException("Predstava mora imati barem jednog rezisera");
		}
	}
	
	public void validateUloge(List<Uloga> uloge) throws DataValidityException {
		
	}
	
	
	public void validatePredstava(String naziv, int trajanje, String opis, List<Zanr> zanrovi, List<Reziser> reziseri, List<Uloga> uloge) 
			throws DataValidityException {
		validateNaziv(naziv);
		validateTrajanje(trajanje);
		validateOpis(opis);
		validateZanrove(zanrovi);
		validateRezisere(reziseri);
		validateUloge(uloge);
	}
}
