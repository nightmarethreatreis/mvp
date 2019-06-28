package nightmarethreatreis.com.github.mvp.logic;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nightmarethreatreis.com.github.mvp.repositories.KorisnikRepository;

@Component
public class KorisnikDataValidator {
	/*
	 * Implementirati:
	 * public void validateEmail(String email) throws KorisnikDataException; DONE kao primer
	 * public void validateUsername(String username) throws KorisnikDataException;
	 * public void validatePassword(String password) throws KorisnikDataException;
	 * public void validateIme(String ime) throws KorisnikDataException;
	 * public void validatePrezime(String prezime) throws KorisnikDataException;
	 */
	private Pattern emailPattern = Pattern.compile("^(?:\\w+\\.)*\\w+@(?:\\w+\\.)+\\w+$");
	private Pattern jmbgPattern = Pattern.compile("^\\d{13}$");
	@Autowired
	private KorisnikRepository korisnikRepo;
	
	public void validateEmail(String email) throws DataValidityException {
		if(!emailPattern.matcher(email).find()) {
			throw new DataValidityException("Nevalidna e-mail adresa");
		}
	}
	
	public void validateJmbg(String jmbg) throws DataValidityException {
		if(!jmbgPattern.matcher(jmbg).find()) {
			throw new DataValidityException("Nevalidan JMBG");
		}
	}
	
	/*
	 * Username mora biti duzine bar 4 i moze sadrzati slova, brojeve i tacku, ali tacka ne sme 
	 * biti na kraju ni pocetku i ne smeju biti dve jedna do druge
	 * Ne sme sadrzati razmake
	 */
	public void validateUsername(String username) throws DataValidityException {
		if(korisnikRepo.getKorisnikByUsername(username) != null) {
			throw new DataValidityException("Vec postoji korisnik sa unetim korisnickim imenom");
		}
	}
	
	/*
	 * Sifra mora biti duzine 8, mora imati barem jedno malo, jedno veliko slovo i broj, ne sme sadrzati razmake 
	 * i moze sadrzati karaktere, kao i sledece "-$=[]{}()", ne sme imati razmake
	 */
	public void validatePassword(String password) throws DataValidityException {
		
	}
	
	/*
	 * Ime moze sadrzati samo slova i ' (ne smeju biti 2 jedno do drugoga, ne sme biti na kraju ni pocetku), 
	 * mora biti duzine barem 2
	 */
	public void validateIme(String ime) throws DataValidityException {
		
	}
	
	/*
	 * Prezime kao i ime, ali moze imati i razmake, ali ne sme 2 jedan do drugoga i ne smeju biti na pocetku ni kraju
	 */
	public void validatePrezime(String prezime) throws DataValidityException {
		
	}
	
	private void validateKorisnik(String username, String password) throws DataValidityException {
		validateUsername(username);
		validatePassword(password);
	}
	
	private void validateDataFullKorisnik(String username, String password, String ime, String prezime) 
			throws DataValidityException {
		validateKorisnik(username, password);
		validateIme(prezime);
		validatePrezime(prezime);
	}
	
	public void validateAdmin(String username, String password) throws DataValidityException {
		validateKorisnik(username, password);
	}
	
	public void validateRadnik(String username, String password, String ime, String prezime) 
			throws DataValidityException {
		validateDataFullKorisnik(username, password, ime, prezime);
	}
	
	public void validateKupac(String username, String password, String ime, String prezime, String email)
			throws DataValidityException {
		validateDataFullKorisnik(username, password, ime, prezime);
		validateEmail(email);
	}
	
	public void validateDramskiRadnik(String ime, String prezime, String jmbg) throws DataValidityException {
		validateIme(ime);
		validatePrezime(prezime);
		validateJmbg(jmbg);
	}
	
}
