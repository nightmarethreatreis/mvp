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
	private Pattern usernamePattern = Pattern.compile("^[.\\d\\w]*$");
	private Pattern passwordChars = Pattern.compile("^[-=$\\w\\d{}\\[\\]()]*$");
	private Pattern imePattern = Pattern.compile("^[\\w'\\s]{2,}$");
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
		if(username.length() < 4) {
			throw new DataValidityException("Korisnicko ime mora biti duzine barem 4");
		}
		if(!usernamePattern.matcher(username).find()) {
			throw new DataValidityException("Korisnicko ime sve zadrzati samo tacke, brojeve i tacke");
		}
		if(username.charAt(0) == '.' || username.charAt(username.length() - 1) == '.') {
			throw new DataValidityException("Korisnicko ime ne sme sadrzati tacku na pocetku ni na kraju");
		}
		if(username.contains("..")) {
			throw new DataValidityException("Koriscko ne sme sadzati uzastopne tacke");
		}
		
		if(korisnikRepo.getKorisnikByUsername(username) != null) {
			throw new DataValidityException("Vec postoji korisnik sa unetim korisnickim imenom");
		}
	}
	
	/*
	 * Sifra mora biti duzine 8, mora imati barem jedno malo, jedno veliko slovo i broj, ne sme sadrzati razmake 
	 * i moze sadrzati karaktere "-$=[]{}()", ne sme imati razmake
	 */
	public void validatePassword(String password) throws DataValidityException {
		if(password.length() < 8) {
			throw new DataValidityException("Lozinka mora biti duzine barem 8");
		}
		if(!passwordChars.matcher(password).find()) {
			throw new DataValidityException("Lozinka sme zasdzati samo slova, brojeve i karaktere -$=[]{}()");
		}
		
		boolean velikoSlovo = false, maloSlovo = false, broj = false;
		for(char karakter: password.toCharArray()) {
			velikoSlovo |= (karakter >= 'A' && karakter <= 'Z');
			maloSlovo |= (karakter >= 'a' && karakter <= 'z');
			broj |= (karakter >= '0' && karakter <= '9');
		}
		if((velikoSlovo ? 1 : 0) + (maloSlovo ? 1 : 0) + (broj ? 1 : 0) < 2) {
			throw new DataValidityException("Sifra mora imati bar dva od ponudjenih: veliko slovo, jedno malo slovo i broj");
		}
	}
	
	private void validateImePrezime(String target, String value) throws DataValidityException {
		if(!imePattern.matcher(value).find()) {
			throw new DataValidityException("Nevalidno " + target);
		}
		if(value.contains("''")) {
			throw new DataValidityException("Ne smeju biti dva uzastopna znaka ' u " + target + "nu");
		}
	}
	
	/*
	 * Ime moze sadrzati samo slova i ' (ne smeju biti 2 jedno do drugoga, ne sme biti na kraju ni pocetku)
	 */
	public void validateIme(String ime) throws DataValidityException {
		validateImePrezime("ime", ime);
	}
	
	/*
	 * Prezime kao i ime
	 */
	public void validatePrezime(String prezime) throws DataValidityException {
		validateImePrezime("prezime", prezime);
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
