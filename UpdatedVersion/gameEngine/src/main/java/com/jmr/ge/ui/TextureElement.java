package com.jmr.ge.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextureElement extends UIElement {

	private final Texture texture;
	
	public TextureElement(Texture texture, float x, float y) {
		super(x, y);
		this.texture = texture;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, x, y);
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	
}
