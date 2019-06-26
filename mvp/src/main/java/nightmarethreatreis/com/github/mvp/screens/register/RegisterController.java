package nightmarethreatreis.com.github.mvp.screens.register;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import nightmarethreatreis.com.github.mvp.events.OnShowEvent;
import nightmarethreatreis.com.github.mvp.logic.KorisnikDataException;
import nightmarethreatreis.com.github.mvp.logic.KorisnikLogic;
import nightmarethreatreis.com.github.mvp.managers.ScreenManager;
import nightmarethreatreis.com.github.mvp.screens.MVCController;
import nightmarethreatreis.com.github.mvp.screens.SpringMVCController;

@SpringMVCController
public class RegisterController implements MVCController {
	@FXML
	private Label infoLabel;
	@FXML
	private Button registerButton;
	
	@FXML
	private TextField usernameField;
	@FXML
	private TextField passwordField;
	@FXML
	private TextField passwordRetypedField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField imeField;
	@FXML
	private TextField prezimeField;
	
	@Autowired
	private KorisnikLogic korisnikLogic;
	@Autowired
	private ScreenManager screenManager;
	
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
	private void headToLogin() {
		screenManager.activate("login");
	}
	
	private void resetFields() {
		usernameField.setText("");
		passwordField.setText("");
		passwordRetypedField.setText("");
		emailField.setText("");
		imeField.setText("");
		prezimeField.setText("");
	}
	
	public void initialize() {
		registerButton.setOnAction(this::handleRegistration);
	}
	
	@Override
	public void onShow(OnShowEvent event) {
		clearMessage();
		resetFields();
		registerButton.setDisable(false);
	}
	
	public void handleRegistration(Event event) {
		clearMessage();
		String password = passwordField.getText().trim(), 
				passwordRetyped = passwordRetypedField.getText().trim();
		if(!password.equals(passwordRetyped)) {
			showErrorMessage("Unete lozinke se ne poklapaju");
			return;
		}
		String username = usernameField.getText().trim(),
				email = emailField.getText().trim(),
				ime = imeField.getText().trim(),
				prezime = prezimeField.getText().trim();
		try {
			if(korisnikLogic.registerKupac(username, password, ime, prezime, email)) {
				showSuccessMessage("Uspesno ste se registrovali");
				registerButton.setDisable(true);
				resetFields();
			}
			else {
				showErrorMessage("Nepoznata greska, pokusajte kasnije");
			}
		} catch (KorisnikDataException e) {
			showErrorMessage(e.getMessage());
		}
	}
}
