package com.jmr.ge;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jmr.ge.input.AndroidDialogues;
import com.jmr.ge.screen.ScreenManager;
import com.jmr.ge.setting.Settings;
import com.jmr.ge.textures.ResourceManager;

public class Game implements ApplicationListener {

	public static AndroidDialogues androidAlertBox;
	protected SpriteBatch sb;
	protected boolean paused;
	protected long lastBack;
	
	@Override
	public void create() {
		sb = new SpriteBatch();
		sb.enableBlending();
		instantiateSettings();
		Gdx.input.setCatchBackKey(true); //Stops from exiting when back button pressed
	}

	@Override
	public void render() {	
		if (!paused) {
			Timer.getInstance().update();
			
			if (ScreenManager.getCurrent() != null)
				ScreenManager.getCurrent().update();
		}
		if (Gdx.input.isKeyPressed(Keys.BACK) && System.currentTimeMillis() - lastBack > 300) {
			if (ScreenManager.getCurrent() != null) {
				ScreenManager.getCurrent().onBackPressed();
			}
			lastBack = System.currentTimeMillis();
		}
		
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor( .07450980392f, .07450980392f, .07450980392f, 1 );

		if (ScreenManager.getCurrent() != null)
			ScreenManager.getCurrent().render(sb);
	}

	@Override
	public void resize(int width, int height) {
		if (ScreenManager.getCurrent() != null)
			ScreenManager.getCurrent().resize(width, height);
	}

	@Override
	public void pause() {
		paused = true;
		if (ScreenManager.getCurrent() != null)
			ScreenManager.getCurrent().pause();
	}

	@Override
	public void resume() {
		paused = false;
		if (ScreenManager.getCurrent() != null)
			ScreenManager.getCurrent().resume();
	}

	@Override
	public void dispose() {
		sb.dispose();
		ResourceManager.getInstance().dispose(); //havent tested, may cause crashing on close
	}
	
	public static void createAndroidAlertBox(AndroidDialogues aab) {
		androidAlertBox = aab;
	}
	
	public static void instantiateSettings() {
		Settings.addSetting("width", 800);
		Settings.addSetting("height", 480);
		Settings.addSetting("tilesize", 50);
	}

}
