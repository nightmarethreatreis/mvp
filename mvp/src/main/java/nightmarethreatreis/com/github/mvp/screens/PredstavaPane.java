package nightmarethreatreis.com.github.mvp.screens;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import nightmarethreatreis.com.github.mvp.model.Predstava;

public class PredstavaPane extends VBox {
	private Predstava predstava;
	private EventHandler<Event> handler;
	
	public PredstavaPane(Predstava predstava, EventHandler<Event> handler) {
		super();
		this.predstava = predstava;
		this.handler = handler;
		initialize();
		setPadding(new Insets(5));
		setStyle("-fx-background-color: red;");
		setStyle("-fx-cursor: hand;");
	}
	
	private void initialize() {
		String zanrText = "Zanrovi: " + predstava.zanroviAsString();
		Text nazivLabel = new Text(predstava.getNaziv());
		nazivLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		nazivLabel.setOnMouseClicked(handler);
		Text zanrLabel = new Text(zanrText);
		Text opisLabel = new Text(predstava.getOpis());
		
		if(predstava.getOpis().length() > 0) {
			getChildren().addAll(nazivLabel, opisLabel, zanrLabel);
		} else {
			getChildren().addAll(nazivLabel, zanrLabel);
		}
	}
}
