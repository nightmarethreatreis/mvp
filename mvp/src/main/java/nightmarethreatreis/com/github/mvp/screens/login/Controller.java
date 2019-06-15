package nightmarethreatreis.com.github.mvp.screens.login;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

@Component
public class Controller {
	@FXML
	private Label label;
	
	public void initialize() {
		label.setText("Text 1.0.0");
	}
}
