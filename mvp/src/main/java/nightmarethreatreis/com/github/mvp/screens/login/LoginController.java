package nightmarethreatreis.com.github.mvp.screens.login;

import nightmarethreatreis.com.github.mvp.events.OnShowEvent;
import nightmarethreatreis.com.github.mvp.screens.MVCController;
import nightmarethreatreis.com.github.mvp.screens.SpringMVCController;

@SpringMVCController
public class LoginController implements MVCController {
	@Override
	public void onShow(OnShowEvent event) {
		System.out.println("Zdravo svete!");
	}
}
