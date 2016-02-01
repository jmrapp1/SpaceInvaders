package com.jmr.ge.setting;

import java.util.HashMap;

public class Settings {

	private static final HashMap<String, Object> settings = new HashMap<String, Object>();
	
	public static void addSetting(String id, Object o) {
		settings.put(id, o);
	}
	
	public static Object getSetting(String id) {
		return settings.get(id);
	}
	
	public static void setSetting(String id, Object o) {
		if (settings.containsKey(id)) {
			settings.remove(id);
			settings.put(id, o);
		}
	}
	
	public static int getWidth() {
		return (Integer)getSetting("width");
	}
	
	public static int getHeight() {
		return (Integer)getSetting("height");
	}
	
	public static int getTileSize() {
		return (Integer)getSetting("tilesize");
	}
	
}
