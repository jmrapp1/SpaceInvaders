package com.jmr.ge.animation.tween;

import com.badlogic.gdx.Gdx;

public class MoveTween extends AbstractTween {

	@Override
	public void update() {
		if (hasBegun()) {
			if (x < destX)
				x += speed * Gdx.graphics.getDeltaTime();
			else if (x > destX)
				x -= speed * Gdx.graphics.getDeltaTime();
			if (y < destY)
				y += speed * Gdx.graphics.getDeltaTime();
			else if (y > destY)
				y -= speed * Gdx.graphics.getDeltaTime();
		}
	}

	@Override
	public boolean isDone() {
		return Math.abs(x - destX) <= 10 && Math.abs(y - destY) <= 10;
	}

	@Override
	public Tween getInstance() {
		return new MoveTween();
	}
	
}
