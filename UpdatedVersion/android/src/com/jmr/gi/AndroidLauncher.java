package com.jmr.gi;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.jmr.gi.GalacticInvaders;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GalacticInvaders.instantiateSettings();
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new GalacticInvaders(), config);
	}
}
