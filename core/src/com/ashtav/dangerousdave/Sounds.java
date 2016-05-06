package com.ashtav.dangerousdave;

import com.ashtav.dangerousdave.framework.GameSound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {

	private static Sound Death = Gdx.audio.newSound(Gdx.files.internal("sounds/death.mp3"));
	private static Sound Explosion = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.mp3"));
	private static Sound Falling = Gdx.audio.newSound(Gdx.files.internal("sounds/falling.mp3"));
	private static Sound Jump = Gdx.audio.newSound(Gdx.files.internal("sounds/jump.mp3"));
	private static Sound Pickup = Gdx.audio.newSound(Gdx.files.internal("sounds/pickup.mp3"));
	private static Sound Trophy = Gdx.audio.newSound(Gdx.files.internal("sounds/trophy.mp3"));
	private static Sound Walking = Gdx.audio.newSound(Gdx.files.internal("sounds/walking.mp3"));
	private static Sound ToogleJetpack = Gdx.audio.newSound(Gdx.files.internal("sounds/toogleJetpack.mp3"));
	private static Sound Jetpack = Gdx.audio.newSound(Gdx.files.internal("sounds/jetpack.mp3"));

	public static GameSound death = new GameSound(Death);
	public static GameSound explosion = new GameSound(Explosion);
	public static GameSound falling = new GameSound(Falling);
	public static GameSound jump = new GameSound(Jump);
	public static GameSound pickup = new GameSound(Pickup);
	public static GameSound trophy = new GameSound(Trophy);
	public static GameSound walking = new GameSound(Walking);
	public static GameSound toogleJetpack = new GameSound(ToogleJetpack);
	public static GameSound jetpack = new GameSound(Jetpack);
}
