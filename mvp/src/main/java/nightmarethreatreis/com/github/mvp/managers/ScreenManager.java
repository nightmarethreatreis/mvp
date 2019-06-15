package nightmarethreatreis.com.github.mvp.managers;

import org.springframework.stereotype.Component;

import javafx.scene.Scene;

@Component
public class ScreenManager {
	private Scene mainScene;

	public void setMainScene(Scene mainScene) {
		this.mainScene = mainScene;
	}
}
