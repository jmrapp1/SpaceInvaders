package com.jmr.gi.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.jmr.ge.entity.AbstractEntity;
import com.jmr.ge.setting.Settings;

/**
 * Created by Jon on 2/1/16.
 */
public class EntityController {

	private Array<AbstractEntity> entities;


	public EntityController() {
		entities = new Array<AbstractEntity>();
	}

	public void update() {
		for (AbstractEntity entity : entities) {
			entity.update();
			System.out.println("Entity: " + entity + " " + entity.getPos());
		}
	}

	public void render(SpriteBatch sb) {
		for (AbstractEntity entity : entities) {
			if (isOnScreen(entity)) {
				entity.render(sb);
			}
		}
	}

	public boolean isOnScreen(AbstractEntity entity) {
		return entity.getPos().x > -entity.getWidth() && entity.getPos().x < Settings.getWidth() && entity.getPos().y > -entity.getHeight() && entity.getPos().y < Settings.getHeight();
	}

	public void addEntity(AbstractEntity entity) {
		entities.add(entity);
	}

	public Array<AbstractEntity> getEntities() {
		return entities;
	}

}
