package nightmarethreatreis.com.github.mvp.screens.reservation;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import nightmarethreatreis.com.github.mvp.events.OnShowEvent;
import nightmarethreatreis.com.github.mvp.managers.NavbarManager;
import nightmarethreatreis.com.github.mvp.managers.ScreenManager;
import nightmarethreatreis.com.github.mvp.screens.MVCController;
import nightmarethreatreis.com.github.mvp.screens.SpringMVCController;

@SpringMVCController
public class ReservationController implements MVCController {
	@FXML
	private FlowPane navbar;
	
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
}
