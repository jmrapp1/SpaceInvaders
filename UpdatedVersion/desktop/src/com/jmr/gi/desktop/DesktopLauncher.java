package com.jmr.gi.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jmr.ge.setting.Settings;
import com.jmr.gi.GalacticInvaders;

public class DesktopLauncher {
	public static void main (String[] arg) {
		GalacticInvaders.instantiateSettings();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Settings.getWidth();
		config.height = Settings.getHeight();
		new LwjglApplication(new GalacticInvaders(), config);
	}
}
