package com.jmr.ge.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Container extends UIElement {

	private Array<UIElement> elements = new Array<UIElement>();
	
	public Container(float x, float y) {
		super(x, y);
	}

	public Container() {
		super(0, 0);
	}
	
	public void add(UIElement element) {
		elements.add(element);
	}
	
	public Array<UIElement> getAll() {
		return elements;
	}

	@Override
	public void update() {
		for (UIElement element : elements)
			element.update();
	}
	
	@Override
	public void setPos(float x, float y) {
		for (UIElement element : elements)
			element.setPos(x + element.getX(), y + element.getY());
	}

	@Override
	public void render(SpriteBatch sb) {
		for (UIElement element : elements)
			element.render(sb);
	}
	
}
