package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.sun.istack.internal.NotNull;
import ru.kvvartet.lndclient.client.states.manager.StateManager;
import ru.kvvartet.lndclient.util.Colors;

public class IntroState extends AbstractState {
    public IntroState(@NotNull StateManager stateManager) {
        super(stateManager);
    }

    @Override
    @NotNull
    public Boolean isBlocking() {
        return false;
    }

    @Override
    public void render(float timeDelta) {
        Gdx.gl.glClearColor(Colors.DARK_GREY_COLOR_PERCENT, Colors.DARK_GREY_COLOR_PERCENT,
                Colors.DARK_GREY_COLOR_PERCENT, Colors.ALPHA_OPAQUE);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.act(timeDelta);
        super.draw();
    }

    @Override
    public void setupStage() {
      // TODO: Intro flexible layout there
    }

    private void disappearOnClick() {
        stateManager.requestStatePop();
        stateManager.requestStatePush(new MainMenuState(stateManager));
    }
}
