package nightmarethreatreis.com.github.mvp.screens.externalComponents;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class NavbarLink extends Label {
	
	public NavbarLink(String text, EventHandler<? super MouseEvent> eventHandler) {
		setText(text);
		setOnMouseClicked(eventHandler);
	}
}
