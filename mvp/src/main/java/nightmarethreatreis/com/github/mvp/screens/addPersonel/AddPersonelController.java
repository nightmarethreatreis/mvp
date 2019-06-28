package nightmarethreatreis.com.github.mvp.screens.addPersonel;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import nightmarethreatreis.com.github.mvp.events.OnShowEvent;
import nightmarethreatreis.com.github.mvp.logic.DataValidityException;
import nightmarethreatreis.com.github.mvp.logic.DramskiRadnikLogic;
import nightmarethreatreis.com.github.mvp.managers.NavbarManager;
import nightmarethreatreis.com.github.mvp.managers.ScreenManager;
import nightmarethreatreis.com.github.mvp.model.Admin;
import nightmarethreatreis.com.github.mvp.model.DramskoZanimanje;
import nightmarethreatreis.com.github.mvp.screens.MVCController;
import nightmarethreatreis.com.github.mvp.screens.SpringMVCController;
import nightmarethreatreis.com.github.mvp.utils.DateUtils;

@SpringMVCController
public class AddPersonelController implements MVCController {
	@FXML
	private FlowPane navbar;
	@FXML
	private TextField imeField;
	@FXML
	private TextField prezimeField;
	@FXML
	private DatePicker datumRodjenjaField;
	@FXML
	private TextField jmbgField;
	@FXML
	private ComboBox<DramskoZanimanje> zanimanjeField;
	@FXML
	private Label infoLabel;
	
	@Autowired
	private NavbarManager navbarManager;
	@Autowired
	private ScreenManager screenManager;
	@Autowired
	private DramskiRadnikLogic dramskiRadnikLogic;
	
	@Override
	public void onShow(OnShowEvent event) {
		/*if(screenManager.redirectUnauthorized(Admin.class, "home")) {
			return;
		}*/
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
	public void resetForm() {
		zanimanjeField.setValue(null);
		imeField.setText("");
		prezimeField.setText("");
		jmbgField.setText("");
		datumRodjenjaField.setValue(null);
	}
	
	private void prepareZanimanjeField() {
		zanimanjeField.setConverter(new StringConverter<DramskoZanimanje>() {
			
			@Override
			public String toString(DramskoZanimanje object) {
				return object.getNaziv();
			}
			
			@Override
			public DramskoZanimanje fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		zanimanjeField.setItems(FXCollections.observableArrayList(DramskoZanimanje.values()));
	}
	
	public void initialize() {
		prepareZanimanjeField();
	}
	
	@FXML
	public void handleAddition() {
		try {
			String ime = imeField.getText().trim(),
					prezime = prezimeField.getText().trim(),
					jmbg = jmbgField.getText().trim();
			LocalDate datumRodjenjaLocal = datumRodjenjaField.getValue();
			DramskoZanimanje zanimanje = zanimanjeField.getValue();
			if(datumRodjenjaLocal == null) {
				throw new DataValidityException("Datum rodjenja se mora izabrati");
			}
			if(zanimanje == null) {
				throw new DataValidityException("Zanimanje se mora izabrati");
			}
			dramskiRadnikLogic.addDramskiRadnik(
					ime, prezime, jmbg, DateUtils.localDateToDate(datumRodjenjaLocal), 
					zanimanje.getNaziv());
			resetForm();
			showSuccessMessage("Uspesno ste dodali " + zanimanje.getNaziv());
		}
		catch(DataValidityException e) {
			showErrorMessage(e.getMessage());
		}
	}
	
	@FXML
	public void redirectToShowAllPersonel() {
		screenManager.activate("showAllPersonel");
	}
}
