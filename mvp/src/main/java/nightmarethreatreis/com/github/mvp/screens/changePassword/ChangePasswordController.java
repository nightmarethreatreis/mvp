package nightmarethreatreis.com.github.mvp.screens.changePassword;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
public class ChangePasswordController implements MVCController {
	@FXML
	private FlowPane navbar;
	@FXML
	private Label infoLabel;
	@FXML
	private PasswordField oldPasswordField;
	@FXML
	private PasswordField newPasswordField;
	@FXML
	private PasswordField newPasswordRepeatedField;
	
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
	public void handlePasswordChange() {
		try {
			String oldPassword = oldPasswordField.getText().trim(),
					newPassword = newPasswordField.getText().trim(),
					newPasswordRepeated = newPasswordRepeatedField.getText().trim();
			if(!newPassword.equals(newPasswordRepeated)) {
				throw new DataValidityException("Unete šifre se ne poklapaju");
			}
			if(!korisnikLogic.doesPasswordMatchKorisniks(sessionManager.getLoggedInKorisnik(), oldPassword)) {
				throw new DataValidityException("Pogrešna lozinka");
			}
			korisnikLogic.updatePassword(sessionManager.getKorisnikId(), newPassword);
			resetForm();
			showSuccessMessage("Lozinka je uspešno promenjena");
		} catch (DataValidityException e) {
			showErrorMessage(e.getMessage());
		}
	}
	
	@FXML
	public void resetForm() {
		oldPasswordField.setText("");
		newPasswordField.setText("");
		newPasswordRepeatedField.setText("");
	}
}
