package nightmarethreatreis.com.github.mvp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nightmarethreatreis.com.github.mvp.logic.KorisnikDataException;
import nightmarethreatreis.com.github.mvp.logic.KorisnikDataValidator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MvpApplicationTests {
	
	@Test
	public void contextLoads() {
	}
	
	@Autowired
	private KorisnikDataValidator korisnikDataValidator;
	
	@Test
	public void validEmailsTest() throws KorisnikDataException {
		korisnikDataValidator.validateEmail("milos@gmail.com");
		korisnikDataValidator.validateEmail("peirca.98.a.1.b@s.b");
		korisnikDataValidator.validateEmail("sara@m.a123");
		korisnikDataValidator.validateEmail("a@g.c");
	}
	
	@Test(expected = KorisnikDataException.class)
	public void invalidEmailTest1() throws KorisnikDataException {
		korisnikDataValidator.validateEmail("");
	}
	
	@Test(expected = KorisnikDataException.class)
	public void invalidEmailTest2() throws KorisnikDataException {
		korisnikDataValidator.validateEmail("nekiemail.google.com");
	}
	
	@Test(expected = KorisnikDataException.class)
	public void invalidEmailTest3() throws KorisnikDataException {
		korisnikDataValidator.validateEmail("nekiemail-google.com");
	}
	
	@Test(expected = KorisnikDataException.class)
	public void invalidEmailTest4() throws KorisnikDataException {
		korisnikDataValidator.validateEmail("@gmail.com");
	}
	
	@Test(expected = KorisnikDataException.class)
	public void invalidEmailTest5() throws KorisnikDataException {
		korisnikDataValidator.validateEmail("prog.@gmail.com");
	}
	
	@Test(expected = KorisnikDataException.class)
	public void invalidEmailTest6() throws KorisnikDataException {
		korisnikDataValidator.validateEmail("pera@gmail.");
	}
	
	@Test(expected = KorisnikDataException.class)
	public void invalidEmailTest7() throws KorisnikDataException {
		korisnikDataValidator.validateEmail("email@com");
	}
	
	@Test(expected = KorisnikDataException.class)
	public void invalidEmailTest8() throws KorisnikDataException {
		korisnikDataValidator.validateEmail("email@.com");
	}
}
