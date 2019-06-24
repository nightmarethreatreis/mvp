package nightmarethreatreis.com.github.mvp.logic;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

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
	
	public void validateEmail(String email) throws KorisnikDataException {
		if(!emailPattern.matcher(email).find()) {
			throw new KorisnikDataException("Nevalidna e-mail adresa");
		}
	}
	
	/*
	 * Username mora biti duzine bar 4 i moze sadrzati slova, brojeve i tacku, ali tacka ne sme 
	 * biti na kraju ni pocetku i ne smeju biti dve jedna do druge
	 * Ne sme sadrzati razmake
	 */
	public void validateUsername(String username) throws KorisnikDataException {
		
	}
	
	/*
	 * Sifra mora biti duzine 8, mora imati barem jedno malo, jedno veliko slovo i broj, ne sme sadrzati razmake 
	 * i moze sadrzati karaktere, kao i sledece "-$=[]{}()", ne sme imati razmake
	 */
	public void validatePassword(String password) throws KorisnikDataException {
		
	}
	
	/*
	 * Ime moze sadrzati samo slova i ' (ne smeju biti 2 jedno do drugoga, ne sme biti na kraju ni pocetku), 
	 * mora biti duzine barem 2
	 */
	public void validateIme(String ime) throws KorisnikDataException {
		
	}
	
	/*
	 * Prezime kao i ime, ali moze imati i razmake, ali ne sme 2 jedan do drugoga i ne smeju biti na pocetku ni kraju
	 */
	public void validatePrezime(String prezime) throws KorisnikDataException {
		
	}
}
