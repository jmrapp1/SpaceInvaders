package com.jmr.gi.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jmr.ge.Timer;
import com.jmr.ge.camera.OrthoCamera;
import com.jmr.ge.screen.Screen;
import com.jmr.gi.controller.EntityController;
import com.jmr.gi.controller.RoundController;

/**
 * Created by Jon on 2/1/16.
 */
public class GameScreen implements Screen {

	private OrthoCamera camera;
	private EntityController entityController;
	private RoundController roundController;

	@Override
	public void create() {
		camera = new OrthoCamera();
		camera.resize();

		entityController = new EntityController();
		roundController = new RoundController(1);

		Timer.startGameTime();
	}

	@Override
	public void update() {
		entityController.update();
		roundController.update(entityController);
	}

	@Override
	public void render(SpriteBatch sb) {
		entityController.render(sb);
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
		Timer.stopGameTime();
	}

	@Override
	public void resume() {
		Timer.startGameTime();
	}
}
