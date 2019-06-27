package nightmarethreatreis.com.github.mvp.screens.addShow;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import nightmarethreatreis.com.github.mvp.screens.MVCController;
import nightmarethreatreis.com.github.mvp.screens.SpringMVCController;
import tornadofx.control.DateTimePicker;

@SpringMVCController
public class AddShowController implements MVCController {
	@FXML
	private GridPane gridPane;
	private DateTimePicker dateTimePicker = null;
	
	public void initialize() {
		dateTimePicker = new DateTimePicker();
		dateTimePicker.setMaxWidth(Double.MAX_VALUE);
		dateTimePicker.setMaxHeight(Double.MAX_VALUE);
		gridPane.add(dateTimePicker, 2, 1);
	}
}
