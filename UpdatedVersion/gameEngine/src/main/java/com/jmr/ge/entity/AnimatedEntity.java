package com.jmr.ge.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.jmr.ge.image.Drawable;
import com.jmr.ge.path.Node;
import com.jmr.ge.setting.Settings;
import com.jmr.ge.textures.AnimatedSprite;


public abstract class AnimatedEntity extends AbstractEntity {

	protected AnimatedSprite sprite;
	protected Direction lastDir;
	protected long lastMove;
	
	public AnimatedEntity(Drawable drawable, float speed, Rectangle boundOffset, float x, float y) {
		super(drawable, speed, boundOffset, x, y);
		sprite = new AnimatedSprite(getStillAnimation());
		sprite.setDesiredDimensions(drawable.getWidth(), drawable.getHeight());
		width = drawable.getWidth() / 3; //Amount of frames is 3
		height = drawable.getHeight() / 4; //4 is the amount of animations (up, left, right, down)
		sprite.setDesiredDimensions(width, height);
	}
	
	public AnimatedEntity(Drawable drawable, float speed, float x, float y) {
		super(drawable, speed, x, y);
		sprite = new AnimatedSprite(getStillAnimation());
		width = drawable.getWidth() / 3; //Amount of frames is 3
		height = drawable.getHeight() / 4; //4 is the amount of animations (up, left, right, down)
		sprite.setDesiredDimensions(width, height);
	}
	
	public AnimatedEntity(Drawable drawable, float speed) {
		this(drawable, speed, new Rectangle(0, 0, Settings.getTileSize(), Settings.getTileSize()), 0, 0);
	}
	
	public AnimatedEntity(Drawable drawable, float speed, Rectangle boundOffset) {
		this(drawable, speed, boundOffset, 0, 0);
	}
	
	public AnimatedEntity(Drawable drawable, float speed, Rectangle boundOffset, float x, float y, Node goalNode) {
		super(drawable, speed, boundOffset, x, y, goalNode);
		sprite = new AnimatedSprite(getStillAnimation());
		width = drawable.getWidth() / 3; //Amount of frames is 3
		height = drawable.getHeight() / 4; //4 is the amount of animations (up, left, right, down)
		sprite.setDesiredDimensions(width, height);
	}	
	
	public AnimatedEntity(Drawable drawable, float speed, float x, float y, Node goalNode) {
		super(drawable, speed, x, y, goalNode);
		sprite = new AnimatedSprite(getStillAnimation());
		width = drawable.getWidth() / 3; //Amount of frames is 3
		height = drawable.getHeight() / 4; //4 is the amount of animations (up, left, right, down)
		sprite.setDesiredDimensions(width, height);
	}

	public void setDesiredDimensions(float w, float h) {
		sprite.setDesiredDimensions(w, h);
	}
	
	public void setDesiredDimensions() {
		sprite.setDesiredDimensions(width, height);
	}
	
	public void setAnimation() {
		Animation anim = getAnimation();
		if (anim != null)
			sprite.setAnimation(anim);
	}
	
	@Override
	public void update() {
		super.update();
		if (System.currentTimeMillis() - lastMove > 100)
			setStillAnimation();
	}
	
	@Override
	public void render(SpriteBatch sb) {
		sprite.render(sb, pos.x, pos.y);
	}
	
	public Animation getAnimation() {
		if (dir != lastDir) {

			Array<TextureRegion> tr = new Array<TextureRegion>();
			TextureRegion textureRegion = drawable.getTextureRegion();
			int width = (int) drawable.getWidth() / 3; //Amount of frames is 3
			int height = (int) drawable.getHeight() / 4; //4 is the amount of animations (up, left, right, down)
			int multHeight = dir.getValue() * height;
			
			textureRegion.setRegion(0, multHeight, width, height);
			tr.add(textureRegion); 
			
			textureRegion = drawable.getTextureRegion();
			
			textureRegion.setRegion(width, multHeight, width, height);
			tr.add(textureRegion); 
			
			textureRegion = drawable.getTextureRegion();
			
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
	
	public Animation getStillAnimation() {
		if (dir != lastDir) {
			Array<TextureRegion> tr = new Array<TextureRegion>();
			TextureRegion textureRegion = drawable.getTextureRegion();
			int width = (int) drawable.getWidth() / 3; //Amount of frames is 3
			int height = (int) drawable.getHeight() / 4; //4 is the amount of animations (up, left, right, down)
			
			textureRegion.setRegion(0, height * dir.getValue(), width, height);
			tr.add(textureRegion);
			return new Animation(1/3f, tr);
		}
		if (sprite != null)
			return sprite.getAnimation();
		return null;
	}
	
	@Override
	public void move(float multX, float multY) {
		super.move(multX, multY);
		if (Math.abs(multX) > Math.abs(multY)) { //moving horizontally
			if (multX < 0) //moving left
				setDirection(Direction.LEFT);
			else //moving left
				setDirection(Direction.RIGHT);
		} else { //moving vertically
			if (multY < 0) //moving down
				setDirection(Direction.DOWN);
			else //moving up
				setDirection(Direction.UP);
		}
	}

	@Override
	public void moveLeft() {
		super.moveLeft();
		lastMove = System.currentTimeMillis();
		setAnimation();
	}

	@Override
	public void moveRight() {
		super.moveRight();
		lastMove = System.currentTimeMillis();
		setAnimation();
	}

	@Override
	public void moveUp() {
		super.moveUp();
		lastMove = System.currentTimeMillis();
		setAnimation();
	}
	
	@Override
	public void moveDown() {
		super.moveDown();
		lastMove = System.currentTimeMillis();
		setAnimation();
	}
	
	@Override
	public void setDirection(Direction dir) {
		super.setDirection(dir);
		lastMove = System.currentTimeMillis();
		setAnimation();
	}
	
}
