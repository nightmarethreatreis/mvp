package nightmarethreatreis.com.github.mvp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;

import nightmarethreatreis.com.github.mvp.logic.KorisnikLogic;
import nightmarethreatreis.com.github.mvp.repositories.KorisnikRepository;

@Component
public class DbInitializer extends SpringBootServletInitializer {
    
	@Autowired
	private KorisnikLogic korisnikLogic;
	@Autowired
	private KorisnikRepository korisnikRepo;
	
	public void checkForInitialization() {
		// glupo, ali sigurno :)
		if(korisnikRepo.getAdminCount() == 0) {
			initializeDatabase();
		}
	}
	
	public void initializeDatabase() {
		try {
			korisnikLogic.registerAdmin("admin", "admin12345");
			System.out.println(" --- Baza inicijalizovana");
		} catch (Exception e) {
			System.out.println(" --- Doslo je do greske prilikom inicijalizacije");
		}
	}

}