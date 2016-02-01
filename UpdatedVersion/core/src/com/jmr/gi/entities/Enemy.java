package com.jmr.gi.entities;

import com.jmr.ge.entity.AbstractEntity;
import com.jmr.ge.entity.WorldObject;
import com.jmr.ge.image.Drawable;

/**
 * Created by Jon on 2/1/16.
 */
public abstract class Enemy extends AbstractEntity {

	public Enemy(Drawable drawable, float speed, float x, float y) {
		super(drawable, speed, x, y);
	}

	@Override
	public void update() {
		move(0, -speed);
		super.update();
	}

	@Override
	public boolean onCollide(WorldObject wo) {
		return false;
	}

}