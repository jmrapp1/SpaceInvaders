package com.jmr.ge.queue;

import com.badlogic.gdx.utils.Array;

public class QueueManager {

	private static QueueManager instance = new QueueManager();
	
	private Array<QueuedEvent> events = new Array<QueuedEvent>();
	
	private QueueManager() {
	}
	
	public void addEvent(QueuedEvent event) {
		events.add(event);
	}
	
	public void callEvent(String name) {
		for (int i = 0; i < events.size; i++) {
			QueuedEvent ev = events.get(i);
			if (ev.getName().equalsIgnoreCase(name)) {
				ev.performEvent();
				events.removeValue(ev, false);
			}
		}
	}
	
	public static QueueManager getInstance() {
		return instance;
	}
	
}
