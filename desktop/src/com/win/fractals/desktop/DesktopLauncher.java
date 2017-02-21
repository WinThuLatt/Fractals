package com.win.fractals.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.win.fractals.Fractals;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Fractals.WIDTH;
		config.height = Fractals.HEIGHT;
		new LwjglApplication(new Fractals(), config);
	}
}
