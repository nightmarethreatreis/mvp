package nightmarethreatreis.com.github.mvp.screens;

import nightmarethreatreis.com.github.mvp.events.OnShowEvent;

public interface MVCController {
	default void onShow(OnShowEvent event) {
	}
}
