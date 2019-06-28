package nightmarethreatreis.com.github.mvp.screens.changeUsername;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import nightmarethreatreis.com.github.mvp.events.OnShowEvent;
import nightmarethreatreis.com.github.mvp.logic.DataValidityException;
import nightmarethreatreis.com.github.mvp.logic.KorisnikLogic;
import nightmarethreatreis.com.github.mvp.managers.NavbarManager;
import nightmarethreatreis.com.github.mvp.managers.ScreenManager;
import nightmarethreatreis.com.github.mvp.managers.SessionManager;
import nightmarethreatreis.com.github.mvp.screens.MVCController;
import nightmarethreatreis.com.github.mvp.screens.SpringMVCController;

@SpringMVCController
public class ChangeUsernameController implements MVCController {
	@FXML
	private FlowPane navbar;
	@FXML
	private Label infoLabel;
	@FXML
	private PasswordField oldPasswordField;
	@FXML
	private TextField usernameField;
	
	@Autowired
	private NavbarManager navbarManager;
	@Autowired
	private ScreenManager screenManager;
	@Autowired
	private SessionManager sessionManager;
	@Autowired
	private KorisnikLogic korisnikLogic;
	
	@Override
	public void onShow(OnShowEvent event) {
		if(screenManager.redirectNonLogged("login")) {
			return;
		}
		navbarManager.updateNavbar(navbar);
		resetForm();
		clearMessage();
	}
	
	private void showErrorMessage(String errorMessage) {
		infoLabel.setTextFill(Color.RED);
		infoLabel.setText(" * Greska: " + errorMessage);
	}
	
	private void showSuccessMessage(String message) {
		infoLabel.setTextFill(Color.FORESTGREEN);
		infoLabel.setText(message);
	}
	
	private void clearMessage() {
		infoLabel.setText("");
	}
	
	@FXML
	public void handleUsernameChange() {
		try {
			String username = usernameField.getText().trim(),
					password = oldPasswordField.getText().trim();
			if(!korisnikLogic.doesPasswordMatchKorisniks(sessionManager.getLoggedInKorisnik(), password)) {
				throw new DataValidityException("Pogrešna lozinka");
			}
			korisnikLogic.updateUsername(sessionManager.getKorisnikId(), username);
			resetForm();
			showSuccessMessage("Koričko ime je uspešno promenjeno");
		} catch (DataValidityException e) {
			showErrorMessage(e.getMessage());
		}
	}
	
	@FXML
	public void resetForm() {
		oldPasswordField.setText("");
		usernameField.setText("");
	}
}
