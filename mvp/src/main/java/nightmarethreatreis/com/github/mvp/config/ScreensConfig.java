package nightmarethreatreis.com.github.mvp.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:guiconfig.properties")
@ConfigurationProperties("views")
public class ScreensConfig {
	private String viewsDirectoryPath;
	
	private Map<String, String> views = new HashMap<>();

	public String getViewsDirectoryPath() {
		return viewsDirectoryPath;
	}
	public void setViewsDirectoryPath(String viewsDirectoryPath) {
		this.viewsDirectoryPath = viewsDirectoryPath;
	}
	public Map<String, String> getViews() {
		return views;
	}
	public void setViews(Map<String, String> views) {
		this.views = views;
	}
	
	public String getView(String viewName) {
		String viewPath = views.get(viewName);
		if(viewPath == null) {
			throw new NoSuchViewExceptions("Pogled \"" + viewName + "\" ne postoji");
		}
		File viewFile = new File(viewsDirectoryPath, viewPath);
		return viewFile.getPath().replace('\\', '/');
	}
}
