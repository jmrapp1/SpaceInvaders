package com.jmr.ge.prefs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PrefManager {
	
	private static Preferences prefs = Gdx.app.getPreferences("gameprefs");
	public static ISecurePrefs securePrefs;
	
	
	public static void setPrefsName(String name) {
		prefs = Gdx.app.getPreferences(name);
	}
	
	public static void putPref(String key, Object value) {
		if (value instanceof String)
			prefs.putString(key, String.valueOf(value));
		else if (value instanceof Float)
			prefs.putFloat(key, (Float)value);
		else if (value instanceof Long)
			prefs.putLong(key, (Long)value);
		else if (value instanceof Integer)
			prefs.putInteger(key, (Integer)value);
		else if (value instanceof Boolean)
			prefs.putBoolean(key, (Boolean)value);
		prefs.flush();
	}
	
	public static void putStringPref(String key, String val) {
		prefs.putString(key, val);
		prefs.flush();
	}
	
	public static void putEncodedString(String key, String value) {
		if (securePrefs != null) {
			securePrefs.putString(key, value);
		} else {
			putStringPref(key, value);
		}
	}
	
	public static String getEncodedString(String key) {
		if (securePrefs != null) {
			return securePrefs.getString(key);
		} else {
			return getString(key);
		}
	}
	
	public static boolean stringPrefExists(String key) {
		return prefs.contains(key);
	}
	
	public static String getString(String key) {
		return prefs.getString(key);
	}
	
	public static float getFloat(String key) {
		return prefs.getFloat(key);
	}
	
	public static long getLong(String key) {
		return prefs.getLong(key);
	}
	
	public static int getInteger(String key) {
		return prefs.getInteger(key);
	}
	
	public static boolean getBoolean(String key) {
		return prefs.getBoolean(key);
	}
	
	public static String encrypt(String input) {
		StringBuilder output = new StringBuilder();
		for (int i=0; i< input.length(); i++) {
			char c = input.charAt(i);
			if ((c < 32) || (c > 126)) {
		
				output.append(c);
			} else {
				//shift
				c+=13;
				if (c > 126) {
					//wrap
					c-=((126 - 32) + 1);
				}
				output.append(c);
			}
		}
		return output.toString();
	}

	public static String decrypt(String input) {
		StringBuilder output = new StringBuilder();
		for (int i=0; i< input.length(); i++) {
			char c = input.charAt(i);
			if ((c < 32) || (c > 126)) {
				output.append(c);
			} else 
				c-=13;
			if (c < 32) {
				c+=((126 - 32) + 1);
			}
			output.append(c);
		}
		return output.toString();
	}
	
	public static void setSecurePrefs(ISecurePrefs secure) {
		if (prefs == null) {
			prefs = Gdx.app.getPreferences("gameprefs");
		}
		securePrefs = secure;
	}
	
}
