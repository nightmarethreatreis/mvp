package nightmarethreatreis.com.github.mvp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import nightmarethreatreis.com.github.mvp.managers.ScreenManager;

@SpringBootApplication
public class MvpApplication extends Application {

	private ConfigurableApplicationContext context;
	private ScreenManager screenManager;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void loadBeans() {
		screenManager = context.getBean(ScreenManager.class);
	}
	
	@Override
	public void init() throws Exception {
		context = SpringApplication.run(MvpApplication.class);
		
		loadBeans();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(new Label("Hello World!"));
		screenManager.setMainScene(scene);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
	public void stop() throws Exception {
		context.close();
	}

}
