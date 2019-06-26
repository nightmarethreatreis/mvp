package nightmarethreatreis.com.github.mvp.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import nightmarethreatreis.com.github.mvp.model.Admin;
import nightmarethreatreis.com.github.mvp.model.Korisnik;
import nightmarethreatreis.com.github.mvp.model.Kupac;
import nightmarethreatreis.com.github.mvp.model.Radnik;
import nightmarethreatreis.com.github.mvp.repositories.KorisnikRepository;

@Component
public class KorisnikLogic {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private KorisnikDataValidator korisnikDataValidator;
	@Autowired
	private KorisnikRepository korisnikRepo;
	
	public boolean registerAdmin(String username, String password) throws KorisnikDataException {
		korisnikDataValidator.validateAdmin(username, password);
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(passwordEncoder.encode(password));
		return korisnikRepo.saveAndFlush(admin) != null;
	}
	
	public boolean registerRadnik(String username, String password, String ime, String prezime) 
			throws KorisnikDataException {
		korisnikDataValidator.validateRadnik(username, password, ime, prezime);
		Radnik radnik = new Radnik();
		radnik.setUsername(username);
		radnik.setPassword(passwordEncoder.encode(password));
		radnik.setIme(ime);
		radnik.setPrezime(prezime);
		return korisnikRepo.saveAndFlush(radnik) != null;
	}
	
	public boolean registerKupac(String username, String password, String ime, String prezime, String email) 
			throws KorisnikDataException {
		try {
			korisnikDataValidator.validateEmail("a@gmail.com");
		} catch(KorisnikDataException e) {
			e.printStackTrace();
		}
		korisnikDataValidator.validateKupac(username, password, ime, prezime, email);
		Kupac kupac = new Kupac();
		kupac.setUsername(username);
		kupac.setPassword(passwordEncoder.encode(password));
		kupac.setIme(ime);
		kupac.setPrezime(prezime);
		kupac.setEmail(email);
		return korisnikRepo.saveAndFlush(kupac) != null;
	}
	
	public long getIdByLoginData(String username, String password) throws KorisnikLoginException {
		Korisnik korisnik = korisnikRepo.getKorisnikByUsername(username);
		if(korisnik == null) {
			throw new KorisnikLoginException("Uneti korisnik ne postoji");
		}
		if(!passwordEncoder.matches(password, korisnik.getPassword())) {
			throw new KorisnikLoginException("Pogresna sifra");
		}
		return korisnik.getId();
	}
}
