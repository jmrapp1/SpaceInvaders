package com.jmr.ge.animation;

import java.text.DecimalFormat;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.jmr.ge.ui.UIElement;

public class FloatAnimation implements Animation {

	private final UIElement element;
	private float centerX, centerY;
	private final float maxSpeed, yDif;
	private float speed, speedInc;
	private boolean reversing;
	private int dir;
	
	public FloatAnimation(UIElement element, float yDif, float maxSpeed) {
		this.element = element;
		this.yDif = yDif;
		this.maxSpeed = maxSpeed;
		centerX = element.getX();
		centerY = element.getY();
	}
	
	@Override
	public void update() {
		if (!reversing) {
			if (dir == 0) {
				if (centerY - element.getY() >= yDif)  { //down
					reversing = true;
					speedInc = maxSpeed / 1500;
				} else {
					if (speed + maxSpeed / 100f <= maxSpeed)
						speed += maxSpeed / 100;
				}
			} else if (dir == 1) {
				if (element.getY() - centerY >= yDif)  { //up
					reversing = true;
					speedInc = maxSpeed / 1500;
				} else {
					if (speed + maxSpeed / 100 <= maxSpeed)
						speed += maxSpeed / 100;
				}
			}
		} else {
			speed -= speedInc;
			speedInc += maxSpeed / 1500f;
			if (speed <= 0f) {
				reversing = false;
				if (dir == 0) 
					dir = 1;
				else
					dir = 0;
			}
		}
		if (dir == 0)
			element.addPos(0, -speed);
		else
			element.addPos(0, speed);
	}

	
	
}
