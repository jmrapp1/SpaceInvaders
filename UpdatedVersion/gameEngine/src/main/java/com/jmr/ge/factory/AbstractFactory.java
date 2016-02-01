package com.jmr.ge.factory;

import com.badlogic.gdx.utils.Array;

public abstract class AbstractFactory<T> {

	protected final Array<T> cache = new Array<T>();
	
	public abstract T get(float x, float y, float speed);
	
	public void addToCache(T t) {
		cache.add(t);
	}
	
	public abstract String getName();
	
}
