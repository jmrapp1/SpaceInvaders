package com.jmr.ge.prefs;

public interface ISecurePrefs {

	void putString(String key, String value);
	
	String getString(String key);
	
}
