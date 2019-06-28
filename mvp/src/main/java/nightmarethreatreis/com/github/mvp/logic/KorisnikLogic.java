package nightmarethreatreis.com.github.mvp.logic;

import java.util.function.BiConsumer;

import javax.persistence.EntityNotFoundException;

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
	
	public boolean registerAdmin(String username, String password) throws DataValidityException {
		korisnikDataValidator.validateAdmin(username, password);
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(passwordEncoder.encode(password));
		return korisnikRepo.save(admin) != null;
	}
	
	public boolean registerRadnik(String username, String password, String ime, String prezime) 
			throws DataValidityException {
		korisnikDataValidator.validateRadnik(username, password, ime, prezime);
		Radnik radnik = new Radnik();
		radnik.setUsername(username);
		radnik.setPassword(passwordEncoder.encode(password));
		radnik.setIme(ime);
		radnik.setPrezime(prezime);
		return korisnikRepo.save(radnik) != null;
	}
	
	public boolean registerKupac(String username, String password, String ime, String prezime, String email) 
			throws DataValidityException {
		korisnikDataValidator.validateKupac(username, password, ime, prezime, email);
		Kupac kupac = new Kupac();
		kupac.setUsername(username);
		kupac.setPassword(passwordEncoder.encode(password));
		kupac.setIme(ime);
		kupac.setPrezime(prezime);
		kupac.setEmail(email);
		return korisnikRepo.save(kupac) != null;
	}
	
	public long getIdByLoginData(String username, String password) throws KorisnikLoginException {
		Korisnik korisnik = korisnikRepo.getKorisnikByUsername(username);
		if(korisnik == null) {
			throw new KorisnikLoginException("Uneti korisnik ne postoji");
		}
		if(!passwordEncoder.matches(password, korisnik.getPassword())) {
			throw new KorisnikLoginException("Pogresna lozinka");
		}
		return korisnik.getId();
	}
	
	@FunctionalInterface
	private interface KorisnikValidator<T> {
		public void validate(T data) throws DataValidityException;
	}
	
	private <T extends Korisnik, V> boolean updateKorisnikData(long korisnikId, V newValue, KorisnikValidator<V> validator, BiConsumer<T, V> assignment) 
			throws DataValidityException {
		validator.validate(newValue);
		try {
			Korisnik korisnik = korisnikRepo.getOne(korisnikId);
			@SuppressWarnings("unchecked")
			T entity = (T)korisnik;
			assignment.accept(entity, newValue);
			return korisnikRepo.save(entity) != null;
		} catch(EntityNotFoundException e) {
			throw new DataValidityException("Doslo je do greske prilikom pribavljanja korisnika");
		} catch(ClassCastException e) {
			throw new ClassCastException("Ne moze se kastovati u navedeni tip");
		}
	}
	
	public boolean updateUsername(long korisnikId, String username) throws DataValidityException {
		return updateKorisnikData(korisnikId, username, korisnikDataValidator::validateUsername, Korisnik::setUsername);
	}
	
	public boolean updatePassword(long korisnikId, String password) throws DataValidityException {
		return updateKorisnikData(korisnikId, password, korisnikDataValidator::validatePassword, 
				(korisnik, sifra) -> {
					korisnik.setPassword(passwordEncoder.encode(password));
				});
	}
}
