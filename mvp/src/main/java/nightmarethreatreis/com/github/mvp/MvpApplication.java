package nightmarethreatreis.com.github.mvp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;
import nightmarethreatreis.com.github.mvp.managers.ScreenManager;

@SpringBootApplication
public class MvpApplication extends Application {

	private ConfigurableApplicationContext context;
	private ScreenManager screenManager;
	private DbInitializer dbInitializer;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void loadBeans() {
		screenManager = context.getBean(ScreenManager.class);
		dbInitializer = context.getBean(DbInitializer.class);
	}
	
	@Override
	public void init() throws Exception {
		context = SpringApplication.run(MvpApplication.class);
		loadBeans();
		dbInitializer.checkForInitialization();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		screenManager.setPrimaryStage(primaryStage, true);
		screenManager.activate("login");
		primaryStage.show();
	}
	
	@Override
	public void stop() throws Exception {
		context.close();
	}

}
