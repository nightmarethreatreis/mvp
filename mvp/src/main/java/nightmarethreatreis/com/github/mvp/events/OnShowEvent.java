package nightmarethreatreis.com.github.mvp.events;

import javafx.event.Event;
import javafx.event.EventType;

public class OnShowEvent extends Event {

	private static final long serialVersionUID = -119655647073475242L;
	public static final EventType<OnShowEvent> SHOW_EVENT = new EventType<OnShowEvent>(ANY); 
	
	public OnShowEvent() {
		super(SHOW_EVENT);
	}

}
