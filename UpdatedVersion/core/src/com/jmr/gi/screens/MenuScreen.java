package com.jmr.gi.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jmr.ge.camera.OrthoCamera;
import com.jmr.ge.screen.Screen;

/**
 * Created by Jon on 2/1/16.
 */
public class MenuScreen implements Screen {

	private OrthoCamera camera;

	@Override
	public void create() {
		camera = new OrthoCamera();
		camera.resize();
	}

	@Override
	public void update() {

	}

	@Override
	public void render(SpriteBatch sb) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void resize(int width, int height) {
		camera.resize();
	}

	@Override
	public void onBackPressed() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}
}
