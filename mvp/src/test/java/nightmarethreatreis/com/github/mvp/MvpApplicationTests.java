package nightmarethreatreis.com.github.mvp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nightmarethreatreis.com.github.mvp.logic.DataValidityException;
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
	public void validEmailsTest() throws DataValidityException {
		korisnikDataValidator.validateEmail("milos@gmail.com");
		korisnikDataValidator.validateEmail("peirca.98.a.1.b@s.b");
		korisnikDataValidator.validateEmail("sara@m.a123");
		korisnikDataValidator.validateEmail("a@g.c");
	}
	
	@Test(expected = DataValidityException.class)
	public void invalidEmailTest1() throws DataValidityException {
		korisnikDataValidator.validateEmail("");
	}
	
	@Test(expected = DataValidityException.class)
	public void invalidEmailTest2() throws DataValidityException {
		korisnikDataValidator.validateEmail("nekiemail.google.com");
	}
	
	@Test(expected = DataValidityException.class)
	public void invalidEmailTest3() throws DataValidityException {
		korisnikDataValidator.validateEmail("nekiemail-google.com");
	}
	
	@Test(expected = DataValidityException.class)
	public void invalidEmailTest4() throws DataValidityException {
		korisnikDataValidator.validateEmail("@gmail.com");
	}
	
	@Test(expected = DataValidityException.class)
	public void invalidEmailTest5() throws DataValidityException {
		korisnikDataValidator.validateEmail("prog.@gmail.com");
	}
	
	@Test(expected = DataValidityException.class)
	public void invalidEmailTest6() throws DataValidityException {
		korisnikDataValidator.validateEmail("pera@gmail.");
	}
	
	@Test(expected = DataValidityException.class)
	public void invalidEmailTest7() throws DataValidityException {
		korisnikDataValidator.validateEmail("email@com");
	}
	
	@Test(expected = DataValidityException.class)
	public void invalidEmailTest8() throws DataValidityException {
		korisnikDataValidator.validateEmail("email@.com");
	}
}
