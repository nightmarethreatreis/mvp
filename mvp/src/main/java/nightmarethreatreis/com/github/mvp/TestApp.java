package nightmarethreatreis.com.github.mvp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nightmarethreatreis.com.github.mvp.logic.KorisnikLogic;
import nightmarethreatreis.com.github.mvp.logic.KorisnikLoginException;
import nightmarethreatreis.com.github.mvp.logic.KorisnikRegisterException;
import nightmarethreatreis.com.github.mvp.managers.SessionManager;
import nightmarethreatreis.com.github.mvp.repositories.KorisnikRepository;

@SpringBootApplication
public class TestApp {
	@Autowired
	private SessionManager sessionManager;
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(TestApp.class, args);
	}
	
	@Autowired
	private KorisnikLogic logic;
	
	@PostConstruct
	private void nekiMethod() {
		try {
			logic.registerKorisnikAndReturnId("pera", "peric");
		} catch (KorisnikRegisterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(sessionManager.getCurrentKorisnik());
		try {
			sessionManager.loginKorisnik("pera", "peric");
			System.out.println(sessionManager.getKorisnikId());
			System.out.println(sessionManager.getCurrentKorisnik());
		} catch (KorisnikLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
