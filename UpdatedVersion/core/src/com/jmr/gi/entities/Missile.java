package com.jmr.gi.entities;

import com.jmr.ge.entity.AbstractEntity;
import com.jmr.ge.entity.WorldObject;
import com.jmr.ge.image.Drawable;

/**
 * Created by Jon on 2/1/16.
 */
public abstract class Missile extends AbstractEntity {

	public Missile(Drawable drawable, float speed, float x, float y) {
		super(drawable, speed, x, y);
	}

}
