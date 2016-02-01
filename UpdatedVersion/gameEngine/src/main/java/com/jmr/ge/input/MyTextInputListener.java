package com.jmr.ge.input;

import com.badlogic.gdx.Input.TextInputListener;

public class MyTextInputListener implements TextInputListener {
	
	private String text;
	private boolean isDone = false;
	
	@Override
	public void input (String text) {
		this.text = text;
		isDone = true;
		System.out.println("Got " + text);
	}

	@Override
	public void canceled () {
		text = "";
		isDone = true;
		System.out.println("Canceled");
	}
	
	public String getText() {
		return text;
	}
	
	public boolean isDone() {
		return isDone;
	}
	
}
