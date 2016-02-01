package com.jmr.gi.controller;

import com.badlogic.gdx.math.MathUtils;
import com.jmr.ge.Timer;
import com.jmr.ge.setting.Settings;
import com.jmr.gi.entities.BasicEnemyShip;

/**
 * Created by Jon on 2/1/16.
 */
public class RoundController {

	public static final int ENEMIES_INC_PER_ROUND = 3;
	private static final float SPAWN_TIME_BASE = 2.5f, SPAWN_TIME_DIF_RATE = 0.025f;
	private int currentRound;
	private int totalKills, totalEnemies, enemiesSpawned;

	private float lastSpawnTime;

	public RoundController(int round) {
		this.currentRound = round;
		totalEnemies = round * ENEMIES_INC_PER_ROUND;
	}

	public void update(EntityController entityController) {
		if (Timer.getGameTimeElapsed() - lastSpawnTime >= SPAWN_TIME_BASE - (currentRound * SPAWN_TIME_DIF_RATE)) {
			if (enemiesSpawned < totalEnemies) {
				entityController.addEntity(new BasicEnemyShip(MathUtils.random(0, Settings.getWidth() - 50), MathUtils.random(Settings.getHeight(), Settings.getHeight() * 2)));
				enemiesSpawned++;
				lastSpawnTime = Timer.getGameTimeElapsed();
				System.out.println("Added entity");
			}
		}
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public void addKill() {
		totalKills++;
	}

	public boolean isGameOver() {
		return totalKills >= totalEnemies;
	}

	public void nextRound() {
		currentRound++;
		totalKills = 0;
		totalEnemies = currentRound * ENEMIES_INC_PER_ROUND;
	}

}
