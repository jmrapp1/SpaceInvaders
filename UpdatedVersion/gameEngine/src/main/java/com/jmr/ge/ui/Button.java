package com.jmr.ge.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button extends UIElement {
	
	protected final float width;
	protected final float height;
	
	public Button(float x, float y, float width, float height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}
	
	public boolean isPressed(float xClick, float yClick) {
		return new Rectangle(x, y, width, height).overlaps(new Rectangle(xClick, yClick, 1, 1));
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(SpriteBatch sb) {
		
	}
	
}
