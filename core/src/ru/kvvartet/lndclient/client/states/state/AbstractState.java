package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.sun.istack.internal.NotNull;
import ru.kvvartet.lndclient.client.states.manager.StateManager;

public abstract class AbstractState extends Stage implements GameState {
    protected final StateManager stateManager;

    public AbstractState(@NotNull StateManager stateManager) {
        super(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.stateManager = stateManager;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {
        getViewport().update(width, height, true);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @NotNull
    protected float countScale(@NotNull int size, @NotNull Boolean isWidth) {
        final float denominator = isWidth
                ? Integer.valueOf(Gdx.graphics.getWidth()).floatValue()
                : Integer.valueOf(Gdx.graphics.getHeight()).floatValue();
        return Integer.valueOf(size).floatValue() / denominator;
    }
}
