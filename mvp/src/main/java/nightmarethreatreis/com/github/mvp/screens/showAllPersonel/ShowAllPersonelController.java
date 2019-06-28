package nightmarethreatreis.com.github.mvp.screens.showAllPersonel;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import nightmarethreatreis.com.github.mvp.events.OnShowEvent;
import nightmarethreatreis.com.github.mvp.managers.NavbarManager;
import nightmarethreatreis.com.github.mvp.managers.ScreenManager;
import nightmarethreatreis.com.github.mvp.model.Admin;
import nightmarethreatreis.com.github.mvp.model.DramskiRadnik;
import nightmarethreatreis.com.github.mvp.repositories.DramskiRadnikRepository;
import nightmarethreatreis.com.github.mvp.screens.MVCController;
import nightmarethreatreis.com.github.mvp.screens.SpringMVCController;

@SpringMVCController
public class ShowAllPersonelController implements MVCController {
	@FXML
	private FlowPane navbar;
	@FXML
	private TableView<DramskiRadnik> tabela;
	
	@Autowired
	private NavbarManager navbarManager;
	@Autowired
	private ScreenManager screenManager;
	@Autowired
	private DramskiRadnikRepository dramskiRadnikRepo;
	
	@Override
	public void onShow(OnShowEvent event) {
		if(screenManager.redirectUnauthorized(Admin.class, "home")) {
			return;
		}
		navbarManager.updateNavbar(navbar);
		loadContent();
	}
	
	@SuppressWarnings("unchecked")
	private void prepareTable() {
		TableColumn<DramskiRadnik, String> imeColumn = new TableColumn<>("IME");
		imeColumn.setCellValueFactory(new PropertyValueFactory<DramskiRadnik, String>("ime"));
		TableColumn<DramskiRadnik, String> prezimeColumn = new TableColumn<>("PREZIME");
		prezimeColumn.setCellValueFactory(new PropertyValueFactory<DramskiRadnik, String>("prezime"));
		TableColumn<DramskiRadnik, String> zanimanjeColumn = new TableColumn<>("ZANIMANJE");
		zanimanjeColumn.setCellValueFactory(new PropertyValueFactory<DramskiRadnik, String>("zanimanje"));
		TableColumn<DramskiRadnik, String> jmbgColumn = new TableColumn<>("JMBG");
		jmbgColumn.setCellValueFactory(new PropertyValueFactory<DramskiRadnik, String>("jmbg"));
		TableColumn<DramskiRadnik, Date> datumRodjenjaColumn = new TableColumn<>("DATUM RODJENJA");
		datumRodjenjaColumn.setCellValueFactory(new PropertyValueFactory<DramskiRadnik, Date>("datumRodjenja"));
		
		tabela.getColumns().addAll(imeColumn, prezimeColumn, zanimanjeColumn, jmbgColumn, datumRodjenjaColumn);
	}
	
	public void initialize() {
		prepareTable();
	}
	
	private void loadContent() {
		tabela.setItems(FXCollections.observableArrayList(dramskiRadnikRepo.getAllDramskiRadnik()));
	}
	
	@FXML
	public void redirectToAddPersonel() {
		screenManager.activate("addPersonel");
	}
}
