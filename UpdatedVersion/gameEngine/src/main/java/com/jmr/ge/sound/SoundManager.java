package com.jmr.ge.sound;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

	private static SoundManager instance = new SoundManager();
	private final HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	
	private SoundManager() {
	}
	
	public void loadSound(String id, String file) {
		sounds.put(id, Gdx.audio.newSound(Gdx.files.internal(file)));
	}
	
	public Sound getSound(String id) {
		return (Sound) sounds.get(id);
	}
	
	public void playSound(String id) {
		Sound sound = getSound(id);
		if (sound != null) {
			sound.play();
		}
	}
	
	public void playSound(String id, float volume) {
		Sound sound = getSound(id);
		if (sound != null) {
			long songId = sound.play();
			sound.setVolume(songId, volume);
		}
	}
	
	public Sound loopSound(String id) {
		Sound sound = getSound(id);
		if (sound != null) {
			sound.loop();
		}
		return sound;
	}
	
	public Sound loopSound(String id, float volume) {
		Sound sound = getSound(id);
		if (sound != null) {
			long songId = sound.loop();
			sound.setVolume(songId, volume);
		}
		return sound;
	}
	
	public static SoundManager getInstance() {
		return instance;
	}
	
}
