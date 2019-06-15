package nightmarethreatreis.com.github.mvp.managers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import nightmarethreatreis.com.github.mvp.config.GUIConfig;
import nightmarethreatreis.com.github.mvp.config.NoSuchViewExceptions;
import nightmarethreatreis.com.github.mvp.config.ScreensConfig;
import nightmarethreatreis.com.github.mvp.events.OnShowEvent;
import nightmarethreatreis.com.github.mvp.screens.MVCController;

@Component
@Scope("singleton")
public class ScreenManager {
	
	@Autowired
	private ScreensConfig screensConfig;
	@Autowired
	private GUIConfig guiConfig;
	@Autowired
	private ConfigurableApplicationContext context;
	
	private Stage primaryStage;
	
	private Scene mainScene;
	private Map<String, Parent> views = new HashMap<>();
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public void setPrimaryStage(Stage primaryStage) {
		setPrimaryStage(primaryStage, false);
	}
	public void setPrimaryStage(Stage primaryStage, boolean prepare) {
		this.primaryStage = primaryStage;
		if(prepare) {
			preparePrimaryStage();
		}
	}
	
	private void preparePrimaryStage() {
		mainScene = new Scene(defaultView(), guiConfig.getWindowWidth(), guiConfig.getWindowHeight());
		primaryStage.setTitle(guiConfig.getTitle());
		primaryStage.setScene(mainScene);
	}
	
	private Parent loadFXML(String fxmlFilePath) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
		loader.setControllerFactory(context::getBean);
		try {
			Parent pane = loader.load();
			MVCController controller = 
					loader.<MVCController>getController();
			pane.addEventHandler(OnShowEvent.SHOW_EVENT, controller::onShow);
			return pane;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoSuchViewExceptions("Pogled " + fxmlFilePath + " se ne moze ucitati");
		}
	}
	
	private Parent getView(String viewName) {
		Parent view = views.get(viewName);
		if(view == null) {
			String viewPath = screensConfig.getView(viewName);
			view = (Parent)loadFXML(viewPath);
			views.put(viewName, view);
		}
		return view;
	}
	
	private Parent defaultView() {
		return new Label("Loading...");
	}
	
	public void activateDefaultView() {
		mainScene.setRoot(defaultView());
	}
	
	public void activate(String viewName) {
		Parent root = getView(viewName);
		mainScene.setRoot(root);
		root.fireEvent(new OnShowEvent());
	}
}
