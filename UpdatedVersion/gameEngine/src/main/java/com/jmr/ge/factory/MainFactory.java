package com.jmr.ge.factory;

import com.badlogic.gdx.utils.Array;

public class MainFactory {

	private static final MainFactory instance = new MainFactory();
	private final Array<AbstractFactory> factories = new Array<AbstractFactory>();
	
	private MainFactory() {
	}
	
	public AbstractFactory getFactory(String name) {
		for (AbstractFactory af : factories)
			if (af.getName().equalsIgnoreCase(name))
				return af;
		return null;
	}
	
	public static MainFactory getInstance() {
		return instance;
	}
	
	public void addFactory(AbstractFactory factory) {
		factories.add(factory);
	}
	
}
