package nightmarethreatreis.com.github.mvp.screens.canvas;

import org.springframework.stereotype.Component;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import nightmarethreatreis.com.github.mvp.screens.MVCController;

class Seat extends Rectangle {

	public Seat(double x, double y, double width, double height, Paint fill) {
		super(x, y, width, height);
		setOnMouseClicked(this::handleClick);
		this.fill = fill;
		setFill(fill);
		setCursor(Cursor.HAND);
		setStroke(Color.BLACK);
		setStrokeWidth(1);
	}
	
	private boolean active = false;
	private Paint fill;
	
	private synchronized void handleClick(Event event) {
		if(active) {
			active = false;
			setFill(fill);
		}
		else {
			active = true;
			setFill(Color.ALICEBLUE);
		}
	}
	
}

@Component
public class CanvasController implements MVCController {
	@FXML
	private Pane canvas;
	@FXML
	private ScrollPane canvasPane;
	
	public void initialize() {
		drawShapes(10, 10, 20, 20, 5, 5, Color.CRIMSON);
		canvasPane.widthProperty().addListener(width -> {
			canvas.relocate(100, 100);
		});
	}
	
	public void drawShapes(int rows, int columns, int w, int h, int wGap, int hGap, Paint fill) {
		
		canvas.getChildren().clear();
		canvas.setMinWidth(wGap + columns * (wGap + w));
		canvas.setMinHeight(hGap + rows * (hGap + h));
		canvas.setStyle("-fx-background-color: #ffffff");
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				Seat seat = new Seat(wGap + j * (wGap + w), hGap + i * (hGap + h), w, h, fill);
				canvas.getChildren().add(seat);
			}
		}
	}
}
