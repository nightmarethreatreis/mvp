package nightmarethreatreis.com.github.mvp.screens.addShow;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import nightmarethreatreis.com.github.mvp.events.OnShowEvent;
import nightmarethreatreis.com.github.mvp.managers.NavbarManager;
import nightmarethreatreis.com.github.mvp.managers.ScreenManager;
import nightmarethreatreis.com.github.mvp.screens.MVCController;
import nightmarethreatreis.com.github.mvp.screens.SpringMVCController;
import tornadofx.control.DateTimePicker;

@SpringMVCController
public class AddShowController implements MVCController {
	@FXML
	private FlowPane navbar;
	@FXML
	private GridPane gridPane;
	private DateTimePicker dateTimePicker = null;
	
	@Autowired
	private NavbarManager navbarManager;
	@Autowired
	private ScreenManager screenManager;
	
	@Override
	public void onShow(OnShowEvent event) {
		if(screenManager.redirectNonLogged("login")) {
			return;
		}
		navbarManager.updateNavbar(navbar);
	}
	
	public void initialize() {
		dateTimePicker = new DateTimePicker();
		dateTimePicker.setMaxWidth(Double.MAX_VALUE);
		dateTimePicker.setMaxHeight(Double.MAX_VALUE);
		gridPane.add(dateTimePicker, 2, 1);
	}
}
