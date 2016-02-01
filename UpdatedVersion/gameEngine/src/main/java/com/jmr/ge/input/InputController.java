package com.jmr.ge.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.jmr.ge.camera.OrthoCamera;

public class InputController implements GestureListener {

	private static Array<MouseListener> listeners = new Array<MouseListener>();
	private static InputController instance;
	private final InputMultiplexer processors = new InputMultiplexer();
	
	public InputController() {
		if (instance == null)
			instance = this;
		processors.addProcessor(new GestureDetector(this));
		Gdx.input.setInputProcessor(processors);
	}
	
	public static void addListener(MouseListener listener) {
		listeners.add(listener);
	}
	
	public void checkInput(OrthoCamera cam) { 
		if (Gdx.input.isTouched()) {
			int button = getButtonPressed();
			Vector2 pos = cam.unprojectCoordinates(Gdx.input.getX(), Gdx.input.getY());
			for (MouseListener l : listeners)
				if(l.onMousePressed(cam, pos.x, pos.y, button))
					break;
		}
	}
	
	private int getButtonPressed() {
		if (Gdx.input.isButtonPressed(Buttons.LEFT))
			return Buttons.LEFT;
		else if (Gdx.input.isButtonPressed(Buttons.MIDDLE))
			return Buttons.MIDDLE;
		else if (Gdx.input.isButtonPressed(Buttons.RIGHT))
			return Buttons.RIGHT;
		return -1;
	}
	
	public void clearListeners() {
		listeners.clear();
	}
	
	public static InputController getInstance() {
		if (instance == null)
			new InputController();
		return instance;
	}
	
	public void addInputProcessor(InputProcessor ip) {
		processors.addProcessor(ip);
		Gdx.input.setInputProcessor(processors);
	}

	public void removeInputProcessor(InputProcessor ip) {
		processors.removeProcessor(ip);
		Gdx.input.setInputProcessor(processors);
	}
	
	public void clearProcessors() {
		processors.clear();
		processors.addProcessor(new GestureDetector(this));
		Gdx.input.setInputProcessor(processors);
	}

	@Override
	public boolean fling(float arg0, float arg1, int arg2) {
		return false;
	}

	@Override
	public boolean longPress(float arg0, float arg1) {
		return false;
	}

	@Override
	public boolean pan(float arg0, float arg1, float arg2, float arg3) {
		return false;
	}

	@Override
	public boolean panStop(float arg0, float arg1, int arg2, int arg3) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 arg0, Vector2 arg1, Vector2 arg2, Vector2 arg3) {
		return false;
	}

	@Override
	public boolean tap(float arg0, float arg1, int arg2, int button) {
		for (MouseListener l : listeners)
			if(l.onMouseClicked(Gdx.input.getX(), Gdx.input.getY(), button))
				break;
		return false;
	}

	@Override
	public boolean touchDown(float arg0, float arg1, int arg2, int arg3) {
		return false;
	}

	@Override
	public boolean zoom(float arg0, float arg1) {
		return false;
	}

}
