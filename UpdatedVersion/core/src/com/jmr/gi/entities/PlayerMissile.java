package com.jmr.gi.entities;

import com.jmr.ge.entity.WorldObject;
import com.jmr.ge.image.Drawable;
import com.jmr.ge.textures.ResourceManager;

/**
 * Created by Jon on 2/1/16.
 */
public class PlayerMissile extends Missile {

	public PlayerMissile(float x, float y) {
		super(ResourceManager.getInstance().getDrawable("missile"), 6, x, y);
	}

	@Override
	public boolean onCollide(WorldObject wo) {
		return false;
	}

}
