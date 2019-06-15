package nightmarethreatreis.com.github.mvp.screens.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import nightmarethreatreis.com.github.mvp.events.OnShowEvent;
import nightmarethreatreis.com.github.mvp.managers.ScreenManager;
import nightmarethreatreis.com.github.mvp.screens.MVCController;

@Component
public class Controller implements MVCController {
	@FXML
	private Label label;
	@FXML
	private Button button;
	@Autowired
	private ScreenManager screenManager;

	public void initialize() {
		label.setText("Text 1.0.0");
		button.setOnAction(e -> {
			screenManager.activateDefaultView();
		});
	}
	
	@Override
	public void onShow(OnShowEvent event) {
		System.out.println("Prikazujem se");
	}
	
}
