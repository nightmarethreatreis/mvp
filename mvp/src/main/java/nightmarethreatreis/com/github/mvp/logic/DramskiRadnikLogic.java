package nightmarethreatreis.com.github.mvp.logic;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nightmarethreatreis.com.github.mvp.model.DramskiRadnik;
import nightmarethreatreis.com.github.mvp.model.DramskoZanimanje;
import nightmarethreatreis.com.github.mvp.model.Glumac;
import nightmarethreatreis.com.github.mvp.model.Reziser;
import nightmarethreatreis.com.github.mvp.repositories.DramskiRadnikRepository;

@Component
public class DramskiRadnikLogic {
	@Autowired
	private KorisnikDataValidator korisnikDataValidator;
	@Autowired
	private DramskiRadnikRepository dramskiRadnikRepo;
	
	public boolean addDramskiRadnik(String ime, String prezime, String jmbg, Date datumRodjenja, String zanimanje) 
			throws DataValidityException {
		DramskiRadnik dramskiRadnik = null;
		if(zanimanje.equalsIgnoreCase(DramskoZanimanje.GLUMAC.getNaziv())) {
			dramskiRadnik = new Glumac();
		}
		else if(zanimanje.equalsIgnoreCase(DramskoZanimanje.REZISER.getNaziv())) {
			dramskiRadnik = new Reziser();
		}
		else {
			throw new DataValidityException("Nevalidno zanimanje");
		}
		korisnikDataValidator.validateDramskiRadnik(ime, prezime, jmbg);
		dramskiRadnik.setIme(ime);
		dramskiRadnik.setPrezime(prezime);
		dramskiRadnik.setJmbg(jmbg);
		dramskiRadnik.setDatumRodjenja(datumRodjenja);
		return dramskiRadnikRepo.save(dramskiRadnik) != null;
	}
}
