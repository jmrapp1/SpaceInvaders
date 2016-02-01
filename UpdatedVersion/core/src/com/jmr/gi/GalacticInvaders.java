package com.jmr.gi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jmr.ge.Game;
import com.jmr.ge.screen.ScreenManager;
import com.jmr.ge.setting.Settings;
import com.jmr.ge.textures.ResourceManager;
import com.jmr.gi.screens.GameScreen;
import com.jmr.gi.screens.MenuScreen;

public class GalacticInvaders extends Game {

	@Override
	public void create() {
		sb = new SpriteBatch(150);
		sb.enableBlending();
		instantiateSettings();
		Gdx.input.setCatchBackKey(true); //Stops from exiting when back button pressed

		instantiateSettings();

		ResourceManager.getInstance().loadTexturedDrawable("player", "player.png");
		ResourceManager.getInstance().loadTexturedDrawable("enemy", "enemy.png");
		ResourceManager.getInstance().loadTexturedDrawable("missile", "missile.png");

		ScreenManager.setScreen(new GameScreen());
	}

	public static void instantiateSettings() {
		Settings.addSetting("width", 480);
		Settings.addSetting("height", 800);
		Settings.addSetting("tilesize", 25);
	}

}
