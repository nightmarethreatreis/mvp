package nightmarethreatreis.com.github.mvp.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import nightmarethreatreis.com.github.mvp.model.Korisnik;
import nightmarethreatreis.com.github.mvp.repositories.KorisnikRepository;

@Component
public class KorisnikLogic {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private KorisnikRepository korisnikRepo;
	
	public Long getKorisnikIdByLoginData(String username, String password) throws KorisnikLoginException {
		Korisnik korisnik = korisnikRepo.getKorisnikByUsername(username);
		if(korisnik == null) {
			throw new KorisnikLoginException("Korisnik pod datim korisnickim imenom ne postoji");
		}
		
		if(!passwordEncoder.matches(password, korisnik.getPassword())) {
			throw new KorisnikLoginException("Neispravna sifra");
		}
		return korisnik.getId();
	}
	
	public Long registerKorisnikAndReturnId(String username, String password) throws KorisnikRegisterException {
		if(username == null || password == null) {
			throw new NullPointerException("Username i password ne smeju biti null");
		}
		
		String hash = passwordEncoder.encode(password);
		Korisnik korisnik = new Korisnik(username, hash);
		
		try {
			korisnik = korisnikRepo.saveAndFlush(korisnik);
			
			if(korisnik == null) {
				throw new KorisnikRegisterException("Doslo je do greske prilikom registracije korisnika");
			}
			return korisnik.getId();
		} catch (Exception e) {
			throw new KorisnikRegisterException("Doslo je do greske prilikom registracije korisnika");
		}
		
	}
}
