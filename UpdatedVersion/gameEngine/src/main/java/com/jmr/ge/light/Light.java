package com.jmr.ge.light;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.jmr.ge.textures.ResourceManager;

public class Light {

	private static final float zSpeed = 15.0f;
	private static final float PI2 = 3.1415926535897932384626433832795f * 2.0f;
	
	private float zAngle, size;
	private final Vector2 pos;
	private boolean flicker;
	
	public Light(float x, float y, float size, boolean flicker) {
		pos = new Vector2(x, y);
		this.flicker = flicker;
		this.size = size;
	}
	
	public void render(SpriteBatch sb) {
		if (flicker) {
			float dt = Gdx.graphics.getRawDeltaTime();
			zAngle += dt * zSpeed;
			while(zAngle > PI2)
				zAngle -= PI2;
		}
		float lightSize = flicker ? (size + 2.25f * (float)Math.sin(zAngle) + 2.2f*MathUtils.random()): size;
		sb.draw(ResourceManager.getInstance().getTexture("light"), pos.x - lightSize*0.5f + 0.5f, pos.y - lightSize*0.5f, lightSize, lightSize);
	}
	
	public Vector2 getPos() {
		return pos;
	}
	
}
