package nightmarethreatreis.com.github.mvp.screens.showSingleShow;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import nightmarethreatreis.com.github.mvp.events.OnShowEvent;
import nightmarethreatreis.com.github.mvp.managers.NavbarManager;
import nightmarethreatreis.com.github.mvp.managers.ScreenManager;
import nightmarethreatreis.com.github.mvp.managers.SessionManager;
import nightmarethreatreis.com.github.mvp.model.Predstava;
import nightmarethreatreis.com.github.mvp.repositories.PredstavaRepository;
import nightmarethreatreis.com.github.mvp.screens.MVCController;
import nightmarethreatreis.com.github.mvp.screens.SpringMVCController;

@SpringMVCController
public class ShowSingleShowController implements MVCController {
	@FXML
	private FlowPane navbar;
	@FXML
	private Text naslovText;
	@FXML
	private Text zanroviText;
	@FXML
	private Text opisText;
	@FXML
	private Text reziseriText;
	@FXML
	private Text ulogeText;
	@FXML
	private Text trajanjeText;
	
	
	@Autowired
	private NavbarManager navbarManager;
	@Autowired
	private ScreenManager screenManager;
	@Autowired
	private SessionManager sessionManager;
	@Autowired
	private PredstavaRepository predstavaRepo;
	
	private Predstava currentPredstava;
	
	@Override
	public void onShow(OnShowEvent event) {
		if(screenManager.redirectNonLogged("login")) {
			return;
		}
		navbarManager.updateNavbar(navbar);
		loadPredstava();
		setUI();
	}
	
	private void loadPredstava() {
		try {
			Long id = (Long)sessionManager.removeData("singlePredstavaId");
			if(id == null || (currentPredstava = predstavaRepo.fetchFullById(id)) == null) {
				screenManager.activate("showActiveShows");
				return;
			}
		} catch (Exception e) {
			screenManager.activate("showActiveShows");
			e.printStackTrace();
		}
	}
	
	private void setUI() {
		naslovText.setText(currentPredstava.getNaziv());
		String zanrText = "Zanrovi: " + currentPredstava.zanroviAsString();
		String reziserText = "Reziseri: " + currentPredstava.reziseriAsString();
		String ulogaText = "Uloge: " + currentPredstava.ulogeAsString();
		trajanjeText.setText(String.format("Trajanje: %d min.", currentPredstava.getTrajanje()));
		opisText.setText(currentPredstava.getOpis());
		zanroviText.setText(zanrText);
		reziseriText.setText(reziserText);
		ulogeText.setText(ulogaText);
	}
	
	public void headToShowAllShows() {
		screenManager.activate("showActiveShows");
	}
}
