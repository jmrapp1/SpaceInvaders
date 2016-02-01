package com.jmr.ge.queue;

public class QueuedEvent {

	private final QueuedAction action;
	private final String name;
	
	public QueuedEvent(String name, QueuedAction action) {
		this.name = name;
		this.action = action;
	}
	
	public void performEvent() {
		action.performAction();
	}
	
	public String getName() {
		return name;
	}
	
}
