package ru.kvvartet.lndclient;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.kvvartet.lndclient.assets.manager.ClientAssetManagerImpl;
import ru.kvvartet.lndclient.client.states.manager.StateManager;
import ru.kvvartet.lndclient.client.states.manager.StateStackManager;
import ru.kvvartet.lndclient.client.states.state.LoadingScreen;
import ru.kvvartet.lndclient.preferences.AbstractPreferences;
import ru.kvvartet.lndclient.preferences.AndroidPreferences;
import ru.kvvartet.lndclient.preferences.DesktopPreferences;

public class LandsAndDungeonsClient extends Game {
    private SpriteBatch batch;
    private StateManager stateManager;

    @Override
    public void create() {
        Gdx.app.log("platform", Gdx.app.getType().name());
        configure();
        batch = new SpriteBatch();
        ClientAssetManagerImpl.getInstance().initialize(new AssetManager());
        stateManager = new StateStackManager(this);
        pushInitialState();
    }

    @Override
    public void render() {
        stateManager.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        batch.dispose();
        stateManager.requestStateClear();
        stateManager.update(Gdx.graphics.getDeltaTime());
        ClientAssetManagerImpl.getInstance().dispose();
    }

    private void pushInitialState() {
        stateManager.requestStatePush(new LoadingScreen(stateManager));
    }

    private void configure() {
        Gdx.graphics.setTitle(AbstractPreferences.APP_NAME);
        if (Gdx.app.getType() == ApplicationType.Android) {
            configAndroid();
        }
        if (Gdx.app.getType() == ApplicationType.Desktop) {
            configDesktop();
        }
    }

    private void configDesktop() {
        final DesktopPreferences prefs = DesktopPreferences.getInstance();
        if (prefs.isFullScreen()) {
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        } else {
            Gdx.graphics.setWindowedMode(prefs.getScreenWidth(), prefs.getScreenHeight());
            Gdx.graphics.setResizable(prefs.isResizable());
            Gdx.graphics.setUndecorated(prefs.isUndecorated());
        }
        Gdx.graphics.setVSync(prefs.isVsyncEnabled());
    }

    private void configAndroid() {
        AndroidPreferences.getInstance().load();
    }
}
