package nightmarethreatreis.com.github.mvp.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import nightmarethreatreis.com.github.mvp.logic.KorisnikLogic;
import nightmarethreatreis.com.github.mvp.logic.KorisnikLoginException;
import nightmarethreatreis.com.github.mvp.model.Korisnik;
import nightmarethreatreis.com.github.mvp.repositories.KorisnikRepository;

@Component
@Scope("singleton")
public class SessionManager {
	private Long korisnikId = null;
	
	public Long getKorisnikId() {
		return korisnikId;
	}
	
	@Autowired
	private KorisnikRepository korisnikRepo;
	@Autowired
	private KorisnikLogic korisnikLogic;
	
	public Korisnik getCurrentKorisnik() {
		if(korisnikId == null) {
			return null;
		}
		return korisnikRepo.findById(korisnikId).get();
	}
	
	public void loginKorisnik(String username, String password) throws KorisnikLoginException {
		if(korisnikId != null) {
			throw new KorisnikLoginException("Korisnik je vec ulogovan");
		}
		korisnikId = korisnikLogic.getKorisnikIdByLoginData(username, password);
	}
	
	public void logout() {
		korisnikId = null;
	}
}
