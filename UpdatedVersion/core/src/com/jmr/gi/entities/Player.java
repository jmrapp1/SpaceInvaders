package com.jmr.gi.entities;

import com.jmr.ge.entity.AbstractEntity;
import com.jmr.ge.entity.WorldObject;
import com.jmr.ge.textures.ResourceManager;

/**
 * Created by Jon on 2/1/16.
 */
public class Player extends AbstractEntity {

	public Player(float x, float y) {
		super(ResourceManager.getInstance().getDrawable("player"), 5, x, y);
	}

	@Override
	public boolean onCollide(WorldObject wo) {
		return false;
	}

}
