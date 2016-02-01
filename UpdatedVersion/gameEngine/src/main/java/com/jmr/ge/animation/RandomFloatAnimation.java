package com.jmr.ge.animation;

import java.text.DecimalFormat;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.jmr.ge.ui.UIElement;

public class RandomFloatAnimation implements Animation {

	private final UIElement element;
	private float centerX, centerY, destX, destY;
	private final float speed, randomXMax, randomYMax, minCheck;
	
	public RandomFloatAnimation(UIElement element, float speed) {
		this(element, speed, 35, 35);
	}
	
	public RandomFloatAnimation(UIElement element, float speed, float randomXMax, float randomYMax) {
		this(element, speed, randomXMax, randomYMax, .1f);
	}
	
	public RandomFloatAnimation(UIElement element, float speed, float randomXMax, float randomYMax, float minCheck) {
		this.element = element;
		this.speed = speed;
		this.randomXMax = randomXMax;
		this.randomYMax = randomYMax;
		this.minCheck = minCheck;
		centerX = element.getX();
		centerY = element.getY();
		setNewDest();
	}
	
	@Override
	public void update() {
		if (element.getX() < destX) {
			element.addPos(speed, 0);
		}
		if (element.getX() > destX) {
			element.addPos(-speed, 0);
		}
		if (element.getY() < destY) {
			element.addPos(0, speed);
		}
		if (element.getY() > destY) {
			element.addPos(0, -speed);
		}
		
		if (Math.abs(element.getX() - destX) <= minCheck && Math.abs(element.getY() - destY) <= minCheck)
			setNewDest();
	}
	
	private void setNewDest() {
		DecimalFormat df = new DecimalFormat("#.0");
		
		float rx = MathUtils.random(-randomXMax, randomXMax);
		rx = Float.valueOf(df.format(rx));
		
		float ry = MathUtils.random(-randomYMax, randomYMax);
		ry = Float.valueOf(df.format(ry));

		destX = centerX + rx;
		destY = centerY + ry;
	}
	
}
