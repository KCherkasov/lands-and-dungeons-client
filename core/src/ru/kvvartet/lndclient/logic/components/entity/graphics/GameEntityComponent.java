package ru.kvvartet.lndclient.logic.components.entity.graphics;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.makers.TooltipMaker;

import java.util.HashMap;
import java.util.Map;

public abstract class GameEntityComponent extends GraphicsComponent {
    protected final Group widget = new Group();
    protected final Map<Integer, Actor> actors = new HashMap<>();
    protected TooltipMaker maker;

    public GameEntityComponent(@NotNull TextureAtlas atlas) {
        super();
    }

    public @NotNull Group getWidget() {
        return widget;
    }

    @Override
    public void dispose() {}

    protected Table makeTooltip() {
        return maker.makeTooltip();
    }

    protected abstract TooltipMaker initTooltipMaker();
}
