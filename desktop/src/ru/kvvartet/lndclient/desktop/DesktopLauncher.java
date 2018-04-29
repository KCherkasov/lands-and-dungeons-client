package ru.kvvartet.lndclient.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.kvvartet.lndclient.LandsAndDungeonsClient;

public class DesktopLauncher {
	public static void main (String[] arg) {
		final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.useHDPI = true;
		new LwjglApplication(new LandsAndDungeonsClient(), config);
	}
}
