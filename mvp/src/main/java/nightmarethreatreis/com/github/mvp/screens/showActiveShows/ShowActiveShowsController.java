package nightmarethreatreis.com.github.mvp.screens.showActiveShows;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import nightmarethreatreis.com.github.mvp.events.OnShowEvent;
import nightmarethreatreis.com.github.mvp.managers.NavbarManager;
import nightmarethreatreis.com.github.mvp.managers.ScreenManager;
import nightmarethreatreis.com.github.mvp.managers.SessionManager;
import nightmarethreatreis.com.github.mvp.model.Admin;
import nightmarethreatreis.com.github.mvp.model.Predstava;
import nightmarethreatreis.com.github.mvp.repositories.PredstavaRepository;
import nightmarethreatreis.com.github.mvp.screens.MVCController;
import nightmarethreatreis.com.github.mvp.screens.PredstavaPane;
import nightmarethreatreis.com.github.mvp.screens.SpringMVCController;

@SpringMVCController
public class ShowActiveShowsController implements MVCController {
	@FXML
	private FlowPane navbar;
	@FXML
	private VBox predstavaBox;
	@FXML
	private Button addShowButton;
	
	@Autowired
	private NavbarManager navbarManager;
	@Autowired
	private ScreenManager screenManager;
	@Autowired
	private PredstavaRepository predstavaRepo;
	@Autowired
	private SessionManager sessionManager;
	
	@Override
	public void onShow(OnShowEvent event) {
		if(screenManager.redirectNonLogged("login")) {
			return;
		}
		navbarManager.updateNavbar(navbar);
		reload();
		try {
			if(sessionManager.getLoggedInKorisnik() instanceof Admin) {
				addShowButton.setVisible(true);
			}
			else {
				addShowButton.setVisible(false);
			}
		} catch (Exception e) {
			addShowButton.setVisible(false);
		}
	}
	
	private void reload() {
		predstavaBox.getChildren().clear();
		predstavaRepo.getAllPredstavaWithZanrs().forEach(predstava -> {
			predstavaBox.getChildren().add(new PredstavaPane(predstava, event -> {
				openPredstava(predstava);
			}));
		});
	}
	
	private void openPredstava(Predstava predstava) {
		sessionManager.addData("singlePredstavaId", predstava.getId());
		screenManager.activate("showSingleShow");
	}
	
	public void headToAddShow() {
		screenManager.activate("addShow");
	}
	
}
