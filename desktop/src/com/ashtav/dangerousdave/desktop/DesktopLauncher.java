package com.ashtav.dangerousdave.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ashtav.dangerousdave.DangerousDave;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.width = 320;
//		config.height = 200;
		config.width = 640;
		config.height = 480;
		new LwjglApplication(new DangerousDave(), config);
	}
}
