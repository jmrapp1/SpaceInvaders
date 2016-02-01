package com.jmr.ge.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jmr.ge.setting.Settings;

public class WorldObject {

	protected final Vector2 pos, desired, last;
	protected float width, height;
	protected Rectangle boundOffset;
	private boolean edited;
	
	public WorldObject(float x, float y, float width, float height) {
		this.pos = new Vector2(x, y);
		this.last = new Vector2(x, y);
		this.desired = new Vector2(x, y);
		this.width = width;
		this.height = height;
		boundOffset = new Rectangle(0, 0, Settings.getTileSize(), Settings.getTileSize());
	}
	
	public WorldObject(float x, float y, Rectangle boundOffset) {
		this.pos = new Vector2(x, y);
		this.last = new Vector2(x, y);
		this.desired = new Vector2(x, y);
		this.boundOffset = boundOffset;
		this.width = boundOffset.width;
		this.height = boundOffset.height;
		edited = true;
	}
	
	public WorldObject(Rectangle rect) {
		this.pos = new Vector2(rect.x, rect.y);
		this.last = new Vector2(rect.x, rect.y);
		this.desired = new Vector2(rect.x, rect.y);
		this.width = rect.width;
		this.height = rect.height;
		boundOffset = new Rectangle(0, 0, width, height);
	}
	
	public void setPos(float x, float y) {
		desired.x = x;
		desired.y = y;
		pos.y = y;
		pos.y = y;
	}
	
	public void addPos(float x, float y) {
		desired.x += x;
		desired.y += y;
	}
	
	public Vector2 getPos() {
		return pos;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public Rectangle getBounds() {
		if (!edited)
			return new Rectangle(pos.x + boundOffset.x, pos.y + boundOffset.y, width, height);
		else
			return new Rectangle(pos.x + boundOffset.x, pos.y + boundOffset.y, boundOffset.width, boundOffset.height);
	}
	
}
