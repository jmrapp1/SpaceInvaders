package com.jmr.gi.entities;

import com.jmr.ge.image.Drawable;
import com.jmr.ge.textures.ResourceManager;

/**
 * Created by Jon on 2/1/16.
 */
public class BasicEnemyShip extends Enemy {

	public BasicEnemyShip(float x, float y) {
		super(ResourceManager.getInstance().getDrawable("enemy"), 3, x, y);
	}

}
