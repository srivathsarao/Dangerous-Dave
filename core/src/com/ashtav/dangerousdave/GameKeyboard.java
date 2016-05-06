package com.ashtav.dangerousdave;

import java.util.HashMap;
import java.util.Map;

public abstract class GameKeyboard {

	public Map<Integer, Boolean> pressedKeys;
	public Map<Integer, Boolean> toogledKeys;

	public GameKeyboard() {
		pressedKeys = new HashMap<Integer, Boolean>();
		toogledKeys = new HashMap<Integer, Boolean>();
	}

	public abstract void update(double seconds);
}
