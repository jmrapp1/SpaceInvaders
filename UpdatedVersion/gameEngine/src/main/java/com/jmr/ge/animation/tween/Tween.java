package com.jmr.ge.animation.tween;

import com.badlogic.gdx.math.Vector2;

public interface Tween {

	void moveTo(float x, float y, float destX, float destY, float speed);
	
	void update();
	
	boolean isDone();
	
	boolean hasBegun();
	
	Tween getInstance();
	
}
