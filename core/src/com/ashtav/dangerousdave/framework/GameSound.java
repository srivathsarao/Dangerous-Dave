package com.ashtav.dangerousdave.framework;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.audio.Sound;

public class GameSound {

	private Boolean looping = false;
	private Sound sound;
	private static List<Sound> channel = new ArrayList<Sound>();

	public GameSound(Sound sound) {
		this.sound = sound;
		channel.add(sound);
	}

	public void play() {
		for (Sound sounds : channel) {
			sounds.stop();
		}
		sound.play();
	}

	public void loop() {
		if (looping == false) {
			looping = true;
			sound.loop();
		}
	}

	public void stop() {
		looping = false;
		for (Sound sounds : channel) {
			sounds.stop();
		}
	}
}
