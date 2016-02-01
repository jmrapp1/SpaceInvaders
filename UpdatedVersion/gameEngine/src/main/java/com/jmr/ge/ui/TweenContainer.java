package com.jmr.ge.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.jmr.ge.animation.tween.Tween;

public class TweenContainer {

	private Array<Tween> tweens = new Array<Tween>();
	private Container container;
	
	public TweenContainer(Container container, Tween tween) {
		this.container = container;
		for (UIElement e : container.getAll()) {
			e.setPos(container.getX() + e.getX(), container.getY() + e.getY());
			tweens.add(tween.getInstance());
		}
	}

	public void begin(float destX, float destY, float speed) {
		for (int i = 0; i < container.getAll().size; i++) {
			UIElement element = container.getAll().get(i);
			tweens.get(i).moveTo(element.getX(), element.getY(), destX + element.getX() - container.getX(), destY + element.getY() - container.getY(), speed);
		}
	}
	
	public void update() {
		for (Tween tween : tweens)
			if (!tween.isDone())
				tween.update();

		for (UIElement element : container.getAll())
			element.update();
	}
	
	public boolean isDone() {
		for (Tween tween : tweens)
			if (!tween.isDone())
				return false;
		return true;
	}
	
	public boolean hasBegun() {
		return tweens.get(0).hasBegun();
	}
	
	public void render(SpriteBatch sb) {
		for (UIElement element : container.getAll())
			element.render(sb);
	}
	
}
