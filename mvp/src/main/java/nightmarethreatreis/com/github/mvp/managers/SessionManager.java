package nightmarethreatreis.com.github.mvp.managers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import nightmarethreatreis.com.github.mvp.logic.KorisnikLogic;
import nightmarethreatreis.com.github.mvp.logic.KorisnikLoginException;
import nightmarethreatreis.com.github.mvp.model.Korisnik;
import nightmarethreatreis.com.github.mvp.repositories.KorisnikRepository;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SessionManager {
	private Long korisnikId = null;
	
	@Autowired
	private ScreenManager screenManager;
	@Autowired
	private KorisnikRepository korisnikRepo;
	@Autowired
	private KorisnikLogic korisnikLogic;
	
	private Map<String, Object> sessionData = new HashMap<>();
	
	
	//
	// USER DATA CODE BLOCK
	//
	public boolean isKorisnikLoggedIn() {
		return korisnikId != null;
	}
	
	public Korisnik getLoggedInKorisnik() {
		if(korisnikId == null) {
			return null;
		}
		return korisnikRepo.findById(korisnikId).get();
	}
	
	public void logout() {
		korisnikId = null;
	}
	
	public void login(String username, String password) throws KorisnikLoginException {
		if(isKorisnikLoggedIn()) {
			throw new KorisnikLoginException("Korisnik je vec ulogovan");
		}
		korisnikId = korisnikLogic.getIdByLoginData(username, password);
	}
	
	public void redirectLogged(String screenName) {
		if(isKorisnikLoggedIn()) {
			screenManager.activate(screenName);
		}
	}
	
	public void redirectNonLogged(String screenName) {
		if(!isKorisnikLoggedIn()) {
			screenManager.activate(screenName);
		}
	}
	
	public void redictAuthorized(Class<? extends Korisnik> korisnikClass, String screenName) {
		Korisnik korisnik = getLoggedInKorisnik();
		if(korisnik != null && korisnikClass.isInstance(korisnik)) {
			screenManager.activate(screenName);
		}
	}
	
	public void redictUnauthorized(Class<? extends Korisnik> korisnikClass, String screenName) {
		Korisnik korisnik = getLoggedInKorisnik();
		if(korisnik == null || !korisnikClass.isInstance(korisnik)) {
			screenManager.activate(screenName);
		}
	}
	
	//
	// SESSION DATA CODE BLOCK
	//
	public void addData(String key, Object value) {
		sessionData.put(key, value);
	}
	
	public Object getData(String key) {
		return sessionData.get(key);
	}
	
	public Object removeData(String key) {
		return sessionData.remove(key);
	}
	
	public boolean isDataPresent(String key) {
		return sessionData.get(key) != null;
	}
	
	public void clearSession() {
		sessionData.clear();
	}
	
	public boolean isSessionEmpty() {
		return sessionData.isEmpty();
	}
}
