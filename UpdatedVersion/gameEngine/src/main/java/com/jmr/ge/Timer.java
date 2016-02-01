package com.jmr.ge;

import com.badlogic.gdx.Gdx;


public class Timer {

	private static Timer instance = new Timer();
	private static float timeElapsed, gameTime;
	private static boolean startGameTime;
	
	private Timer() {
	}
	
	public void update() {
		timeElapsed += Gdx.graphics.getDeltaTime();
		if (startGameTime) {
			gameTime += Gdx.graphics.getDeltaTime();
		}
	}
	
	public static float getTimeElapsed() {
		return timeElapsed;
	}
	
	public static float getGameTimeElapsed() {
		return gameTime;
	}
	
	public static void startGameTime() {
		startGameTime = true;
	}
	
	public static void stopGameTime() {
		startGameTime = false;
	}
	
	public static void resetGameTime() {
		gameTime = 0;
		startGameTime = false;
	}

	public static Timer getInstance() {
		return instance;
	}
	
}
