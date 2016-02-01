package com.jmr.ge.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.jmr.ge.image.Drawable;
import com.jmr.ge.path.Node;
import com.jmr.ge.setting.Settings;
import com.jmr.ge.textures.AnimatedSprite;

public abstract class AnimatedEntityStrip extends AbstractEntity {

	protected final int frames;
	protected AnimatedSprite sprite;
	
	public AnimatedEntityStrip(Drawable drawable, int frames, float speed, Rectangle boundOffset, float x, float y) {
		super(drawable, speed, boundOffset, x, y);
		this.frames = frames;
		sprite = new AnimatedSprite(getAnimation(drawable, frames));
		width = drawable.getWidth() / frames; //Amount of frames is 3
		height = drawable.getHeight(); //4 is the amount of animations (up, left, right, down)
	}
	
	public AnimatedEntityStrip(Drawable drawable, int frames, float speed, float x, float y) {
		super(drawable, speed, x, y);
		this.frames = frames;
		sprite = new AnimatedSprite(getAnimation(drawable, frames));
		width = drawable.getWidth() / frames; //Amount of frames is 3
		height = drawable.getHeight(); //4 is the amount of animations (up, left, right, down)
	}
	
	public AnimatedEntityStrip(Drawable drawable, int frames, float speed) {
		this(drawable, frames, speed, new Rectangle(0, 0, Settings.getTileSize(), Settings.getTileSize()), 0, 0);
	}
	
	public AnimatedEntityStrip(Drawable drawable, int frames, float speed, Rectangle boundOffset) {
		this(drawable, frames, speed, boundOffset, 0, 0);
	}
	
	public AnimatedEntityStrip(Drawable drawable, int frames, float speed, Rectangle boundOffset, float x, float y, Node goalNode) {
		super(drawable, speed, boundOffset, x, y, goalNode);
		this.frames = frames;
		sprite = new AnimatedSprite(getAnimation(drawable, frames));
		width = drawable.getWidth() / frames; //Amount of frames is 3
		height = drawable.getHeight(); //4 is the amount of animations (up, left, right, down)
	}
	
	public AnimatedEntityStrip(Drawable drawable, int frames, float speed, float x, float y, Node goalNode) {
		super(drawable, speed, x, y, goalNode);
		this.frames = frames;
		sprite = new AnimatedSprite(getAnimation(drawable, frames));
		width = drawable.getWidth() / frames; //Amount of frames is 3
		height = drawable.getHeight(); //4 is the amount of animations (up, left, right, down)
	}
	
	@Override
	public void render(SpriteBatch sb) {
		sprite.render(sb, pos.x, pos.y);
	}
	
	protected Animation getAnimation(Drawable drawable, float time, int frames) {
		Array<TextureRegion> tr = new Array<TextureRegion>();
		TextureRegion textureRegion = drawable.getTextureRegion();
		int width = (int) drawable.getWidth() / frames; //Amount of frames is 3
		int height = (int) drawable.getHeight();
		for (int i = 0; i < frames; i++) {
			textureRegion.setRegion(width * i, 0, width, height);
			tr.add(textureRegion); 
			textureRegion = drawable.getTextureRegion();
		}
		return new Animation(time, tr);
	}
	
	protected Animation getAnimation(Drawable drawable, int frames) {
		return getAnimation(drawable, 1/1.5f, frames);
	}
	
}
