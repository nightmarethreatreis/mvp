package nightmarethreatreis.com.github.mvp.screens.home;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import nightmarethreatreis.com.github.mvp.events.OnShowEvent;
import nightmarethreatreis.com.github.mvp.managers.NavbarManager;
import nightmarethreatreis.com.github.mvp.managers.ScreenManager;
import nightmarethreatreis.com.github.mvp.managers.SessionManager;
import nightmarethreatreis.com.github.mvp.model.Korisnik;
import nightmarethreatreis.com.github.mvp.screens.MVCController;
import nightmarethreatreis.com.github.mvp.screens.SpringMVCController;

@SpringMVCController
public class HomeController implements MVCController {
	@Autowired
	private NavbarManager navbarManager;
	@Autowired
	private ScreenManager screenManager;
	@Autowired
	private SessionManager sessionManager;
	
	@FXML
	private Pane navbar;
	@FXML
	private VBox content;
	
	private void initializeContent() {
		content.getChildren().clear();
		
		Korisnik korisnik = sessionManager.getLoggedInKorisnik();
		Text welcomeText = new Text(
				"Dobrodošli u Nightmare Theatre, ulogovani ste kao " + korisnik +
				"\nOvde nema još ništa, ali možda bude jednog dana :)\n\n" +
				"\"So we beat on, boats against the current, borne back ceaselessly into the past.\"");
		welcomeText.setTextAlignment(TextAlignment.CENTER);
		welcomeText.setFont(Font.font("Verdana", 20));
		content.getChildren().add(welcomeText);
	}
	
	@Override
	public void onShow(OnShowEvent event) {
		if(screenManager.redirectNonLogged("login")) {
			return;
		}
		navbarManager.updateNavbar(navbar);
		initializeContent();
	}
}
