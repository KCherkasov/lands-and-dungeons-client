package ru.kvvartet.lndclient;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.kvvartet.lndclient.client.states.manager.StateManager;
import ru.kvvartet.lndclient.client.states.manager.StateStackManager;
import ru.kvvartet.lndclient.client.states.state.IntroState;

public class LandsAndDungeonsClient extends Game {
	private SpriteBatch batch;
	private StateManager stateManager;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		stateManager = new StateStackManager(this);
		pushInitialState();
	}

	@Override
	public void render() {
		stateManager.update(0.0f);
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		stateManager.requestStateClear();
		stateManager.update(0.0f);
	}

	private void pushInitialState() {
	    stateManager.requestStatePush(new IntroState(stateManager));
    }
}
