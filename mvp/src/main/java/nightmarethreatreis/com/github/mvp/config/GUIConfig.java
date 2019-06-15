package nightmarethreatreis.com.github.mvp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:guiconfig.properties")
@ConfigurationProperties("gui")
public class GUIConfig {
	private String title;
	private int windowHeight;
	private int windowWidth;
	
	public String getTitle() {
		return title;
	}
	public int getWindowHeight() {
		return windowHeight;
	}
	public int getWindowWidth() {
		return windowWidth;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
	}
	public void setWindowWidth(int windowWidth) {
		this.windowWidth = windowWidth;
	}
}
