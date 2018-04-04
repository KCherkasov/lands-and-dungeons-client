package ru.kvvartet.lndclient.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.kvvartet.lndclient.LandsAndDungeonsClient;
import ru.kvvartet.lndclient.util.DesktopDefaults;

public class DesktopLauncher {
	public static void main (String[] arg) {
		final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = DesktopDefaults.DEFAULT_WINDOW_HEIGHT;
		config.width = DesktopDefaults.DEFAULT_WINDOW_WIDTH;
		new LwjglApplication(new LandsAndDungeonsClient(), config);
	}
}
