package com.jmr.ge.animation.tween;

import com.badlogic.gdx.math.Vector2;

public abstract class AbstractTween implements Tween {

	protected float x, y, destX, destY;
	protected float speed;
	private boolean begun = false;
	
	@Override
	public void moveTo(float x, float y, float destX, float destY, float speed) {
		this.x = x;
		this.y = y;
		this.destX = destX;
		this.destY = destY;
		this.speed = speed;
		begun = true;
	}
	
	@Override
	public boolean hasBegun() {
		return begun;
	}

}
