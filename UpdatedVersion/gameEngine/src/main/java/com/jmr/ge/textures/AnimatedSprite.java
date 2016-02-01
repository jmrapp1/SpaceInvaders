package com.jmr.ge.textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AnimatedSprite {

	private Animation anim;
	private float time, desiredWidth, desiredHeight;
	
	public AnimatedSprite(Animation anim) {
		this.anim = anim;
		if (anim != null)
			anim.setPlayMode(PlayMode.LOOP);
	}
	
	public AnimatedSprite(Animation anim, PlayMode playMode) {
		this.anim = anim;
		if (anim != null)
			anim.setPlayMode(playMode);
	}
	
	public void render(SpriteBatch sb, float x, float y) {
		if (anim != null) {
			sb.draw(anim.getKeyFrame(time += Math.max(1/30f, Gdx.graphics.getDeltaTime())), x, y, desiredWidth, desiredHeight); 
		}
	}
	
	public void setDesiredDimensions(float w, float h) {
		this.desiredWidth = w;
		this.desiredHeight = h;
	}
	
	public void setAnimation(Animation anim) {
		this.anim = anim;
		anim.setPlayMode(PlayMode.LOOP);
	}
	
	public void setAnimation(Animation anim, PlayMode playMode) {
		this.anim = anim;
		anim.setPlayMode(playMode);
	}
	
	public Animation getAnimation() {
		return anim;
	}
	
}
