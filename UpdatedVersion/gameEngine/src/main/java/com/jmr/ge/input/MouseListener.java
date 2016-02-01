package com.jmr.ge.input;

import com.jmr.ge.camera.OrthoCamera;

public interface MouseListener {

	boolean onMousePressed(OrthoCamera cam, float x, float y, int button);
	
	boolean onMouseClicked(float x, float y, int button);
	
}
