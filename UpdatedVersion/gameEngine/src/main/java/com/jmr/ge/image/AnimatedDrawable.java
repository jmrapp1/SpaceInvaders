package com.jmr.ge.image;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.jmr.ge.entity.AbstractEntity.Direction;
import com.jmr.ge.textures.AnimatedSprite;

public class AnimatedDrawable implements Drawable {

	private final AnimatedSprite sprite;
	private final Texture texture;
	private Direction lastDir, dir;
	private long lastMove;
	private float width, height;
	
	public AnimatedDrawable(Texture texture) {
		this.texture = texture;
		if (texture != null) {
			width = texture.getWidth() / 3; //Amount of frames is 3
			height = texture.getHeight() / 4; //4 is the amount of animations (up, left, right, down)
		}
		sprite = new AnimatedSprite(getStillAnimation());
		setDesiredDimensions();
	}
	
	@Override
	public void update() {
		if (System.currentTimeMillis() - lastMove > 100)
			setStillAnimation();
	}

	@Override
	public void render(SpriteBatch sb, Vector2 pos) {
		sprite.render(sb, pos.x, pos.y);
	}

	@Override
	public void render(SpriteBatch sb, float x, float y) {
		sprite.render(sb, x, y);
	}
	
	public void setAnimation() {
		lastMove = System.currentTimeMillis();
		Animation anim = getAnimation();
		if (anim != null)
			sprite.setAnimation(anim);
	}
	
	public void setDirection(Direction dir) {
		this.dir = dir;
		lastMove = System.currentTimeMillis();
		setAnimation();
	}
	
	public Animation getAnimation() {
		if (dir != lastDir) {

			Array<TextureRegion> tr = new Array<TextureRegion>();
			TextureRegion textureRegion = new TextureRegion(texture);
			int width = texture.getWidth() / 3; //Amount of frames is 3
			int height = texture.getHeight() / 4; //4 is the amount of animations (up, left, right, down)
			int multHeight = dir.getValue() * height;
			
			textureRegion.setRegion(0, multHeight, width, height);
			tr.add(textureRegion); 
			
			textureRegion = new TextureRegion(texture);
			
			textureRegion.setRegion(width, multHeight, width, height);
			tr.add(textureRegion); 
			
			textureRegion = new TextureRegion(texture);
			
			textureRegion.setRegion(width * 2, multHeight, width, height);
			tr.add(textureRegion); 
			
			return new Animation(1/3f, tr);
		}
		if (sprite != null)
			return sprite.getAnimation();
		return null;
	}
	
	public void setStillAnimation() {
		Animation anim = getStillAnimation();
		if (anim != null)
			sprite.setAnimation(anim);
	}
	
	public void setDesiredDimensions(float w, float h) {
		sprite.setDesiredDimensions(w, h);
	}
	
	public void setDesiredDimensions() {
		sprite.setDesiredDimensions(width, height);
	}
	
	public Animation getStillAnimation() {
		if (dir != lastDir) {
			Array<TextureRegion> tr = new Array<TextureRegion>();
			TextureRegion textureRegion = new TextureRegion(texture);
			int width = texture.getWidth() / 3; //Amount of frames is 3
			int height = texture.getHeight() / 4; //4 is the amount of animations (up, left, right, down)
			
			textureRegion.setRegion(0, height * dir.getValue(), width, height);
			tr.add(textureRegion);
			
			return new Animation(1/3f, tr);
		}
		if (sprite != null)
			return sprite.getAnimation();
		return null;
	}

	@Override
	public TextureRegion getTextureRegion() {
		return new TextureRegion(texture, 0, 0, (int) width, (int) height);
	}

	@Override
	public Drawable getInstance() {
		return new AnimatedDrawable(texture);
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public Object getRaw() {
		return texture;
	}

}
