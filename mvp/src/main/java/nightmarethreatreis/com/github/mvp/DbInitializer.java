package nightmarethreatreis.com.github.mvp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;

import nightmarethreatreis.com.github.mvp.logic.DramskiRadnikLogic;
import nightmarethreatreis.com.github.mvp.logic.KorisnikLogic;
import nightmarethreatreis.com.github.mvp.logic.PredstavaLogic;
import nightmarethreatreis.com.github.mvp.model.DramskoZanimanje;
import nightmarethreatreis.com.github.mvp.repositories.KorisnikRepository;

@Component
public class DbInitializer extends SpringBootServletInitializer {
    
	@Autowired
	private KorisnikLogic korisnikLogic;
	@Autowired
	private KorisnikRepository korisnikRepo;
	@Autowired
	private PredstavaLogic predstavaLogic;
	@Autowired
	private DramskiRadnikLogic dramskiRadnikLogic;
	
	public void checkForInitialization() {
		// glupo, ali sigurno :)
		if(korisnikRepo.getAdminCount() == 0) {
			initializeDatabase();
		}
	}
	
	public void initializeDatabase() {
		try {
			korisnikLogic.registerAdmin("admin", "admin12345");
			for(String nazivZanra: new String[] {
					"mjuzikl", "horor", "opera", "balet"
			}) {
				predstavaLogic.addZanr(nazivZanra);
			}
			dramskiRadnikLogic.addDramskiRadnik("Dragan", "Nikolic", "1111111111111", new Date(System.currentTimeMillis()), DramskoZanimanje.GLUMAC.getNaziv());
			dramskiRadnikLogic.addDramskiRadnik("Pavle", "Vujisic", "1111111111111", new Date(System.currentTimeMillis()), DramskoZanimanje.GLUMAC.getNaziv());
			dramskiRadnikLogic.addDramskiRadnik("Misa", "Janketic", "1111111111111", new Date(System.currentTimeMillis()), DramskoZanimanje.GLUMAC.getNaziv());
			
			dramskiRadnikLogic.addDramskiRadnik("Sava", "Mikic", "1111111111111", new Date(System.currentTimeMillis()), DramskoZanimanje.REZISER.getNaziv());
			dramskiRadnikLogic.addDramskiRadnik("Pera", "Sollieri", "1111111111111", new Date(System.currentTimeMillis()), DramskoZanimanje.REZISER.getNaziv());
			dramskiRadnikLogic.addDramskiRadnik("Lara", "Tarabasic", "1111111111111", new Date(System.currentTimeMillis()), DramskoZanimanje.REZISER.getNaziv());
			System.out.println(" --- Baza inicijalizovana");
		} catch (Exception e) {
			System.out.println(" --- Doslo je do greske prilikom inicijalizacije");
		}
	}

}