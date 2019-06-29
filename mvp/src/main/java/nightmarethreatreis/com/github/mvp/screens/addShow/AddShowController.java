package nightmarethreatreis.com.github.mvp.screens.addShow;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import nightmarethreatreis.com.github.mvp.events.OnShowEvent;
import nightmarethreatreis.com.github.mvp.logic.DataValidityException;
import nightmarethreatreis.com.github.mvp.logic.PredstavaLogic;
import nightmarethreatreis.com.github.mvp.managers.NavbarManager;
import nightmarethreatreis.com.github.mvp.managers.ScreenManager;
import nightmarethreatreis.com.github.mvp.model.Glumac;
import nightmarethreatreis.com.github.mvp.model.Predstava;
import nightmarethreatreis.com.github.mvp.model.Reziser;
import nightmarethreatreis.com.github.mvp.model.Uloga;
import nightmarethreatreis.com.github.mvp.model.Zanr;
import nightmarethreatreis.com.github.mvp.repositories.DramskiRadnikRepository;
import nightmarethreatreis.com.github.mvp.repositories.PredstavaRepository;
import nightmarethreatreis.com.github.mvp.repositories.ZanrRepository;
import nightmarethreatreis.com.github.mvp.screens.MVCController;
import nightmarethreatreis.com.github.mvp.screens.SimplifiedStringConverter;
import nightmarethreatreis.com.github.mvp.screens.SpringMVCController;
import nightmarethreatreis.com.github.mvp.utils.GeneralUtils;



@SpringMVCController
public class AddShowController implements MVCController {
	
	class UlogaSelectionData {
		public Uloga uloga = new Uloga();
		public List<Glumac> preostaliGlumci;
		public UlogaSelectionData(String nazivUloge, List<Glumac> sviGlumci) {
			uloga.setNaziv(nazivUloge);
			uloga.setGlumci(new ArrayList<>(sviGlumci.size()));
			preostaliGlumci = new ArrayList<>(sviGlumci);
		}
	}
	
	@FXML
	private FlowPane navbar;
	@FXML
	private TextField nazivPredstaveField;
	@FXML
	private TextField trajanjePredstaveFIeld;
	@FXML
	private ComboBox<Zanr> zanrComboBox;
	@FXML
	private ComboBox<Reziser> reziserComboBox;
	@FXML
	private ComboBox<UlogaSelectionData> ulogaComboBox;
	@FXML
	private ComboBox<Glumac> glumacComboBox;
	@FXML
	private Label infoLabel;
	@FXML
	private TextField ulogaField;
	@FXML
	private FlowPane zanroviPane;
	@FXML
	private FlowPane reziseriPane;
	@FXML
	private FlowPane ulogaPane;
	@FXML
	private TextArea opisArea;
	
	@Autowired
	private NavbarManager navbarManager;
	@Autowired
	private ScreenManager screenManager;
	@Autowired
	private DramskiRadnikRepository dramskiRadnikRepository;
	@Autowired
	private ZanrRepository zanrRepository;
	@Autowired
	private PredstavaLogic predstavaLogic;
	
	private List<UlogaSelectionData> podaciOUlogama;
	private List<Glumac> sviGlumci;
	private List<Zanr> sviZanrovi;
	private List<Reziser> sviReziseri;
	private List<Zanr> odabraniZanrovi;
	private List<Reziser> odabraniReziseri;
	
	private void resetPodaci() {
		podaciOUlogama = new ArrayList<>();
		sviGlumci = dramskiRadnikRepository.getAllGlumac();
		sviReziseri = dramskiRadnikRepository.getAllReziser();
		sviZanrovi = zanrRepository.findAll();
		odabraniZanrovi = new ArrayList<>(sviZanrovi.size());
		odabraniReziseri = new ArrayList<>(sviReziseri.size());
	}
	
	public void initialize() {
		zanrComboBox.setConverter(new SimplifiedStringConverter<Zanr>() {
			@Override
			public String toString(Zanr object) {
				return object.getNaziv();
			}
		});
		reziserComboBox.setConverter(new SimplifiedStringConverter<Reziser>() {
			@Override
			public String toString(Reziser object) {
				return String.format("%s %s", object.getIme(), object.getPrezime());
			}
		});
		ulogaComboBox.setConverter(new SimplifiedStringConverter<AddShowController.UlogaSelectionData>() {
			@Override
			public String toString(UlogaSelectionData object) {
				return object.uloga.getNaziv();
			}
		});
		glumacComboBox.setConverter(new SimplifiedStringConverter<Glumac>() {
			@Override
			public String toString(Glumac arg0) {
				return arg0.getIme() + " " + arg0.getPrezime();
			}
		});
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
		clearMessage();
		resetPodaci();
		nazivPredstaveField.setText("");
		trajanjePredstaveFIeld.setText("");
		opisArea.setText("");
		updateZanrComboBox();
		updateReziserComboBox();
		updateGlumacComboBox();
	}
	
	@Override
	public void onShow(OnShowEvent event) {
		/*if(screenManager.redirectNonLogged("login")) {
			return;
		}*/
		navbarManager.updateNavbar(navbar);
		resetForm();
	}
	
	private Background makeBackground(Paint color) {
		return new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
	}
	
	private void addChildToPane(String labelText, FlowPane pane, EventHandler<ActionEvent> handler) {
		Button dugme = new Button("Obriši");
		Label label = new Label(labelText);
		label.setAlignment(Pos.CENTER);
		label.setFont(Font.font(dugme.getPrefHeight()));
		label.setMaxWidth(150);
		label.setPrefWidth(150);
		HBox hbox = new HBox(label, dugme);
		hbox.setBackground(makeBackground(Color.ALICEBLUE));
		hbox.setAlignment(Pos.CENTER);
		pane.getChildren().add(hbox);
		dugme.setOnAction(handler);
	}
	
	private void updateZanrComboBox() {
		zanrComboBox.setValue(null);
		zanrComboBox.setItems(FXCollections.observableArrayList(GeneralUtils.firstListWithoutTheOther(sviZanrovi, odabraniZanrovi)));
		zanroviPane.getChildren().clear();
		odabraniZanrovi.forEach(zanr -> {
			addChildToPane(zanr.getNaziv(), zanroviPane, event -> removeZanr(zanr));
		});
	}
	
	private void updateReziserComboBox() {
		reziserComboBox.setValue(null);
		reziserComboBox.setItems(FXCollections.observableArrayList(GeneralUtils.firstListWithoutTheOther(sviReziseri, odabraniReziseri)));	
		reziseriPane.getChildren().clear();
		odabraniReziseri.forEach(reziser -> {
			addChildToPane(reziser.getIme() + " " + reziser.getPrezime(), reziseriPane, event -> removeReziser(reziser));
		});
	}
	
	private void updateGlumacComboBox() {
		ulogaChange();
		updateUlogaPane();
	}
	
	private void updateUlogaComboBox() {
		ulogaComboBox.setValue(null);
		ulogaComboBox.setItems(FXCollections.observableArrayList(podaciOUlogama));
		updateGlumacComboBox();
	}
	
	private void updateUlogaPane() {
		ulogaPane.getChildren().clear();
		podaciOUlogama.forEach(podaci -> {
			addChildToPane("Uloga " + podaci.uloga.getNaziv(), ulogaPane, event -> removePodaci(podaci));
			for(Glumac glumac: podaci.uloga.getGlumci()) {
				addChildToPane(String.format("%s %s (%s)", glumac.getIme(), glumac.getPrezime(), podaci.uloga.getNaziv()),
						ulogaPane,
						event -> removeGlumacFromUloga(podaci, glumac));
			}
		});
	}
	
	private void removeGlumacFromUloga(UlogaSelectionData podaci, Glumac glumac) {
		podaci.uloga.getGlumci().remove(glumac);
		podaci.preostaliGlumci.add(glumac);
		updateGlumacComboBox();
	}

	private void removePodaci(UlogaSelectionData ulogaPodaci) {
		podaciOUlogama.remove(ulogaPodaci);
		updateUlogaComboBox();
	}
	
	private void removeZanr(Zanr zanr) {
		odabraniZanrovi.remove(zanr);
		updateZanrComboBox();
	}
	
	private void removeReziser(Reziser reziser) {
		odabraniReziseri.remove(reziser);
		updateReziserComboBox();
	}
	
	@FXML
	public void dodajUlogu() {
		String nazivUloge = ulogaField.getText().trim();
		try {
			if(nazivUloge.length() == 0) {
				throw new DataValidityException("Naziv uloge ne sme biti prazan da biste je dodali");
			}
			ulogaField.setText("");
			podaciOUlogama.add(new UlogaSelectionData(nazivUloge, sviGlumci));
			updateUlogaComboBox();
			clearMessage();
		} catch (DataValidityException e) {
			showErrorMessage(e.getMessage());
		}
	}
	
	@FXML
	public void dodajZanr() {
		Zanr zanr = zanrComboBox.getValue();
		try {
			if(zanr == null) {
				throw new DataValidityException("Morate odabrati žanr da biste ga dodali");
			}
			odabraniZanrovi.add(zanr);
			updateZanrComboBox();
			clearMessage();
		} catch (DataValidityException e) {
			showErrorMessage(e.getMessage());
		}
	}
	
	@FXML
	public void dodajRezisera() {
		Reziser reziser = reziserComboBox.getValue();
		try {
			if(reziser == null) {
				throw new DataValidityException("Morate odabrati režisera da biste ga dodali");
			}
			odabraniReziseri.add(reziser);
			updateReziserComboBox();
			clearMessage();
		} catch (DataValidityException e) {
			showErrorMessage(e.getMessage());
		}
	}
	
	@FXML
	public void ulogaChange() {
		UlogaSelectionData ulogaData = ulogaComboBox.getValue();
		glumacComboBox.setValue(null);
		if(ulogaData == null) {
			glumacComboBox.setItems(FXCollections.observableArrayList());
			return;
		}
		glumacComboBox.setItems(FXCollections.observableArrayList(ulogaData.preostaliGlumci));
	}
	
	@FXML
	public void dodajGlumcaUlozi() {
		UlogaSelectionData ulogaData = ulogaComboBox.getValue();
		Glumac glumac = glumacComboBox.getValue();
		try {
			if(ulogaData == null) {
				throw new DataValidityException("Morate odabrati ulogu");
			}
			if(glumac == null) {
				throw new DataValidityException("Morate odabrati glumca");
			}
			ulogaData.preostaliGlumci.remove(glumac);
			ulogaData.uloga.getGlumci().add(glumac);
			glumacComboBox.setValue(null);
			updateGlumacComboBox();
			clearMessage();
		} catch (DataValidityException e) {
			showErrorMessage(e.getMessage());
		}
	}
	
	@FXML
	public void handleAddition() {
		String nazivPredstave = nazivPredstaveField.getText().trim(),
				trajanjeStr = trajanjePredstaveFIeld.getText().trim(),
				opisPredstave = opisArea.getText();
		try {
			int trajanje = 0;
			try {
				trajanje = Integer.parseInt(trajanjeStr);
			}
			catch(NumberFormatException e1) {
				throw new DataValidityException("Trajanje predstave mora biti ceo broj");
			}
			boolean uspesnost = predstavaLogic.addPredstava(nazivPredstave, trajanje, opisPredstave, odabraniZanrovi, odabraniReziseri, 
					podaciOUlogama.stream().map(podaci -> podaci.uloga).collect(Collectors.toList()));
			if(!uspesnost) {
				throw new DataValidityException("Doslo je do nepoznate greske");
			}
			resetForm();
			showSuccessMessage("Predstava je uspesno sacuvana");
		} catch (DataValidityException e) {
			showErrorMessage(e.getMessage());
		}
	}
}
