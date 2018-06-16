package ru.kvvartet.lndclient.logic.components.entity.graphics;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public abstract class GameEntityComponent extends GraphicsComponent {
    protected final Group widget = new Group();
    protected final Map<Integer, Actor> actors = new HashMap<>();

    public GameEntityComponent(@NotNull TextureAtlas atlas) {
        super();
    }

    public @NotNull Group getWidget() {
        return widget;
    }

    @Override
    public void dispose() {}
}
