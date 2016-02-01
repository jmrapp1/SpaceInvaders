package com.jmr.ge.animation.tween;

import com.badlogic.gdx.math.Vector2;

public class TweenRunner {

	private final Tween tween;
	
	public TweenRunner(Tween tween) {
		this.tween = tween;
	}
	
	public void begin(float x, float y, float destX, float destY, float speed) {
		tween.moveTo(x, y, destX, destY, speed);
	}
	
	public void update() {
		if (!tween.isDone())
			tween.update();
	}
	
	public boolean isDone() {
		return tween.isDone();
	}
	
	public boolean hasBegun() {
		return tween.hasBegun();
	}
	
}
