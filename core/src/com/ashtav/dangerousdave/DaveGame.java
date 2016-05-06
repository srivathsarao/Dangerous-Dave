package com.ashtav.dangerousdave;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class DaveGame extends Game implements InputProcessor {
	private float lastTime = 0;
	private SpriteBatch batch;
	private Color background;

	private List<TimedFunction> functions;

	public GameKeyboard keyboard;

	public DaveGame() {
		background = Color.valueOf("000000");
		keyboard = new DaveKeyboard((DangerousDave)this);
		functions = new ArrayList<TimedFunction>();
	}

	@Override
	public void create() {
		InputMultiplexer multiplexer = new InputMultiplexer(this);
		Gdx.input.setInputProcessor(multiplexer);
		batch = new SpriteBatch();
		load();
	}

	public void execute(Integer seconds, Runnable runnable) {
		TimedFunction timedFunction = new TimedFunction(seconds, runnable);
		functions.add(timedFunction);
	}

	public void update(Integer seconds) {
		List<TimedFunction> functionsToRemoveList = new ArrayList<TimedFunction>();
		for (TimedFunction function : functions) {
			TimedFunction functionToRemove = function.update(seconds);
			if (functionToRemove != null) {
				functionsToRemoveList.add(functionToRemove);
			}
		}
		if (!functionsToRemoveList.isEmpty()) {
			functions.removeAll(functionsToRemoveList);
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(background.r, background.g, background.b, background.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		if (lastTime == 0) {
			lastTime = Gdx.graphics.getDeltaTime();
		}
		float currentTime = Gdx.graphics.getDeltaTime();
		double seconds = (currentTime - lastTime);
		while(seconds < 1){
			seconds = seconds + Gdx.graphics.getDeltaTime();
		}
		lastTime = currentTime;

		// update game
		if (keyboard != null) {
			keyboard.update(seconds);
		}
		update(seconds);

		// render screen
		render(batch);
		batch.end();
		super.render();
	}

	public abstract void render(SpriteBatch batch);

	public abstract void update(double seconds);

	public abstract void load();

	@Override
	public boolean keyDown(int keyCode) {
		if (keyboard != null) {
			keyboard.pressedKeys.put(keyCode, true);
			if (keyboard.toogledKeys.get(keyCode) != null) {
				keyboard.toogledKeys.put(keyCode, !keyboard.toogledKeys.get(keyCode));
			} else {
				keyboard.toogledKeys.put(keyCode, true);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keyCode) {
		if (keyboard != null) {
			keyboard.pressedKeys.put(keyCode, false);
			return true;
		}
		return false;
	}

	public List<TimedFunction> getFunctions() {
		return functions;
	}
	

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		int keyCode;
		int pos = ((screenX * 100) / Gdx.graphics.getWidth());
		if(pos > 50) {
			keyCode = 22;
		} else {
			keyCode = 21;
		}
		if (keyboard != null) {
			keyboard.pressedKeys.put(keyCode, true);
			if (keyboard.toogledKeys.get(keyCode) != null) {
				keyboard.toogledKeys.put(keyCode, !keyboard.toogledKeys.get(keyCode));
			} else {
				keyboard.toogledKeys.put(keyCode, true);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		int keyCode;
		int pos = ((screenX * 100) / Gdx.graphics.getWidth());
		if(pos > 50) {
			keyCode = 22;
		} else {
			keyCode = 21;
		}
		if (keyboard != null) {
			keyboard.pressedKeys.put(keyCode, false);
			return true;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
