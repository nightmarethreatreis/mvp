package nightmarethreatreis.com.github.mvp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;
import nightmarethreatreis.com.github.mvp.managers.ScreenManager;
import nightmarethreatreis.com.github.mvp.model.Admin;
import nightmarethreatreis.com.github.mvp.model.Kupac;
import nightmarethreatreis.com.github.mvp.repositories.KorisnikRepository;

@SpringBootApplication
public class MvpApplication extends Application {

	private ConfigurableApplicationContext context;
	private ScreenManager screenManager;
	
	private KorisnikRepository korisnikRepo;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void loadBeans() {
		screenManager = context.getBean(ScreenManager.class);
		korisnikRepo = context.getBean(KorisnikRepository.class);
	}
	
	@Override
	public void init() throws Exception {
		context = SpringApplication.run(MvpApplication.class);
		loadBeans();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		screenManager.setPrimaryStage(primaryStage, true);
		
		primaryStage.show();
		screenManager.activate("canvas");
		
		
		/*Kupac k = new Kupac();
		k.setEmail("milos.vujasinovic.98@gmail.com");
		k.setIme("Milos");
		k.setPrezime("Vujasinovic");
		k.setUsername("admin1");
		k.setPassword("perica");
		System.out.println(korisnikRepo.saveAndFlush(k));*/
	
		/*Admin a = new Admin();
		a.setUsername("admin");
		a.setPassword("savica");
		System.out.println(korisnikRepo.saveAndFlush(a));*/
	
		System.out.println("Korisnici: " + korisnikRepo.getAllKorisnik());
		System.out.println("Kupac: " + korisnikRepo.getAllKupac());
		System.out.println("Admin: " + korisnikRepo.getAllAdmin());
	}
	
	@Override
	public void stop() throws Exception {
		context.close();
	}

}
