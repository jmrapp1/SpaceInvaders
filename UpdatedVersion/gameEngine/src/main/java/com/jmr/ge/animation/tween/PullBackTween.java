package com.jmr.ge.animation.tween;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class PullBackTween extends AbstractTween {

	private boolean pullingBack = false, comingBack = false, done = false;
	private float pullBackAmount = 50, beginningSpeed, slowDownSpeed = 50f;
	private int dir;
	
	public PullBackTween() {
	}
			
	public PullBackTween(float slowDownSpeed) {
		this.slowDownSpeed = slowDownSpeed;
	}
	
	@Override
	public void moveTo(float x, float y, float destX, float destY, float speed) {
		super.moveTo(x, y, destX, destY, speed);
		beginningSpeed = speed;
		if (x < destX) { //moving right
			dir = 1;
		} else if (x > destX) { //moving left
			dir = 2;
		} else if (y < destY) { //moving up
			dir = 3;
		} else if (y > destY) { //moving down
			dir = 4;
		}
	}
	
	public void setPullBackAmount(float pullBackAmount) {
		this.pullBackAmount = pullBackAmount;
	}
	
	public void setSlowDownSpeed(float speed) {
		slowDownSpeed = speed;
	}
	
	@Override
	public void update() {
		if (!isDone() && hasBegun()) {
			if (dir == 1) {
				if (x < destX && !pullingBack && !comingBack) { //moving to right
					x += speed * Gdx.graphics.getDeltaTime();
				} else if (!pullingBack && !comingBack && x >= destX) { //past the dest
					pullingBack = true;
				} else if (pullingBack && x >= destX) { //pullingback and past dest
					if (x - destX >= pullBackAmount) { //past the pullbackamount
						comingBack = true;
						pullingBack = false;
					} else { //keep moving right
						speed -= slowDownSpeed;
						if (speed <= 300)
							speed = 300;
						x += speed * Gdx.graphics.getDeltaTime();
					}
				} else if (comingBack) {
					if (x >= destX) {
						x -= beginningSpeed * Gdx.graphics.getDeltaTime();
					} else if (x <= destX) {
						x = destX;
						done = true;
					}
				}
			} else if (dir == 2) { //moving left
				if (x > destX && !pullingBack && !comingBack) { //if moving left
					x -= speed * Gdx.graphics.getDeltaTime();
				} else if (!pullingBack && !comingBack && x <= destX) { //past the dest
					pullingBack = true;
				} else if (pullingBack && x <= destX) { //pullingback and past dest
					if (destX - x >= pullBackAmount) { //past the pullbackamount
						comingBack = true;
						pullingBack = false;
					} else { //keep moving left
						speed -= slowDownSpeed;
						if (speed <= 300)
							speed = 300;
						x -= speed * Gdx.graphics.getDeltaTime();
					}
				} else if (comingBack) {
					if (x <= destX) { //if it is to the right
						x += beginningSpeed * Gdx.graphics.getDeltaTime();
					} else if (x >= destX) { //if past dest
						x = destX;
						done = true;
					}
				}
			} else if (dir == 3) { //moving up
				if (y < destY && !pullingBack && !comingBack) { //if under it
					y += speed * Gdx.graphics.getDeltaTime();
				} else if (!pullingBack && !comingBack  && y >= destY) { //if past it
					pullingBack = true;
				} else if (pullingBack && y >= destY) { //pullingback and past dest
					if (y - destY >= pullBackAmount) { //past the pullbackamount
						comingBack = true;
						pullingBack = false;
					} else { //keep moving right
						speed -= slowDownSpeed;
						if (speed <= 300)
							speed = 300;
						y += speed * Gdx.graphics.getDeltaTime();
					}
				} else if (comingBack) {
					if (y >= destY) {
						y -= beginningSpeed * Gdx.graphics.getDeltaTime();
					} else if (y <= destY) {
						y = destY;
						done = true;
					}
				}
			} else if (dir == 4) { //moving down
				if (y > destY && !pullingBack && !comingBack) { //if above
					y -= speed * Gdx.graphics.getDeltaTime();
				} else if (!pullingBack && !comingBack && y <= destY) { //if past it
					pullingBack = true;
				} else if (pullingBack && y <= destY) { //pullingback and past dest
					if (destY - y >= pullBackAmount) { //past the pullbackamount
						comingBack = true;
						pullingBack = false;
					} else { //keep moving left
						speed -= slowDownSpeed;
						if (speed <= 300)
							speed = 300;
						y -= speed * Gdx.graphics.getDeltaTime();
					}
				} else if (comingBack) {
					if (y <= destY) { //if it is to the right
						y += beginningSpeed * Gdx.graphics.getDeltaTime();
					} else if (y >= destY) { //if past dest
						y = destY;
						done = true;
					}
				}
			}
		}
	}

	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public Tween getInstance() {
		return new PullBackTween(slowDownSpeed);
	}
	
}
