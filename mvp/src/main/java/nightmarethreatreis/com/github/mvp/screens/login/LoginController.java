package nightmarethreatreis.com.github.mvp.screens.login;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import nightmarethreatreis.com.github.mvp.events.OnShowEvent;
import nightmarethreatreis.com.github.mvp.logic.KorisnikLogic;
import nightmarethreatreis.com.github.mvp.logic.KorisnikLoginException;
import nightmarethreatreis.com.github.mvp.managers.ScreenManager;
import nightmarethreatreis.com.github.mvp.managers.SessionManager;
import nightmarethreatreis.com.github.mvp.screens.MVCController;
import nightmarethreatreis.com.github.mvp.screens.SpringMVCController;

@SpringMVCController
public class LoginController implements MVCController {
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button loginButton;
	@FXML
	private Label infoLabel;
	
	@Autowired
	private ScreenManager screenManager;
	@Autowired
	private SessionManager sessionManager;
	
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
	
	private void resetFields() {
		usernameField.setText("");
		passwordField.setText("");
	}
	
	@FXML
	private void headToRegistration() {
		screenManager.activate("register");
	}
	
	public void initialize() {
		loginButton.setOnAction(this::handleLogin);
	}
	
	@Override
	public void onShow(OnShowEvent event) {
		clearMessage();
		resetFields();
	}
	
	public void handleLogin(Event event) {
		clearMessage();
		String username = usernameField.getText().trim(),
				password = passwordField.getText().trim();
		try {
			sessionManager.login(username, password);
			showSuccessMessage("Uspesno ste ulogovani :)");
			loginButton.setDisable(true);
		} catch (KorisnikLoginException e) {
			showErrorMessage(e.getMessage());
		}
	}
	
}
