package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.client.states.manager.StateManager;
import ru.kvvartet.lndclient.util.Colors;

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

    protected void clearScreen() {
        Gdx.gl.glClearColor(Colors.DARK_GREY_COLOR_PERCENT, Colors.DARK_GREY_COLOR_PERCENT,
                Colors.DARK_GREY_COLOR_PERCENT, Colors.ALPHA_OPAQUE);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    protected float countScale(int size, @NotNull Boolean isWidth) {
        final float denominator = isWidth
                ? Integer.valueOf(Gdx.graphics.getWidth()).floatValue()
                : Integer.valueOf(Gdx.graphics.getHeight()).floatValue();
        return Integer.valueOf(size).floatValue() / denominator;
    }
}
